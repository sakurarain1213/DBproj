package com.anyic;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/*
需要用到的函数：

分析文本
public void GetString_analyse2(String data)

获取高频词
public List<String> Get_gaopinci(Integer number)

所有的句子存在List<Sentence> Sens当中，Sentence结构见类Sentence
 */
public class Wenbenchuli {


    //Sentence 类
    public class Sentence{
        List<String> words;//存储句子的词
        List<String> time;//表示Sentence的时间。大小为3，time.get(0)表示hour，time.get(1)表示min，time.get(2)表示sec
        boolean iswuru;//表示这句话是不是侮辱,true表示有侮辱，flase表示没侮辱
        boolean isguli;//true表示是鼓励，false表示不是
        boolean istiwen;//同上，表示提问
        int wuru_pos;//表示侮辱词的位置，在words中的下标


        String duixiang;//对象，表示这句话的对象，"0"表示对象不是学生，其它表示对象名
        String duixiang2;//2号对象，表示提问句中的对象
        Sentence(){
            words=new ArrayList<>();
            iswuru=false;
            duixiang="0";
            duixiang2="0";
            isguli=false;
            istiwen=false;
            time=new ArrayList<>();
            time.add("0");
            time.add("0");
            time.add("0");
        }

        //获取sentence的年月日时分秒
        public String Get_Sentence_time(){
            return nianyueri+" "+time.get(0)+":"+time.get(1)+":"+time.get(2);
        }
    }
    List<Sentence> Sens;//以句子为单位的list
    private static String basePath="C:\\Users\\w1625\\Desktop\\hou_fin\\src\\main\\java\\com\\anyic";
    public int wuru_Count;//侮辱次数
    public int guli_Count;//鼓励次数
    public int wenda_Count;//提问次数
    public float yusu;//语速
    public int timelength;//文本时长                             *****
    public String nianyueri;//表示文本年月日
    public JiebaSegmenter segmenter;//jieba类
    public Set<String> stop_words;//停用词
    public List<String> result;//存储分词结果
    public static Set<String> wuru;//侮辱词库
    public static Set<String> guli;//鼓励词库
    public static Set<String> wenda;//问答词库
    public static Set<String> duixiang;//对象词库
    public Map<String, Integer> cipin;//统计词频
    public Wenbenchuli(){
        result=new ArrayList<>();
        Sens=new ArrayList<>();
        wuru=new HashSet<>();
        guli=new HashSet<>();
        wenda=new HashSet<>();
        cipin=new LinkedHashMap<>();
        stop_words=new HashSet<>();
        Sens=new ArrayList<>();
        segmenter=new JiebaSegmenter();
        wuru_Count=0;
        guli_Count=0;
        wenda_Count=0;
        yusu=0;
        timelength=0;
    }

    //将Map按照value值排序，用于词频统计
    private  <K extends Comparable,V extends Comparable> Map<K, V> sortMapByValues(Map<K, V> map){
        //需要用LinkedHashMap排序
        HashMap<K, V> finalMap = new LinkedHashMap<K, V>();
        //取出map键值对Entry<K,V>，然后按照值排序，最后组成一个新的列表集合
        List<Map.Entry<K, V>> list = map.entrySet()
                .stream()
                //sorted((p2,p1)   表示由大到小排序   ||  sorted((p1,p2)   表示由小到大排序
                .sorted((p2,p1)->p1.getValue().compareTo(p2.getValue()))
                .collect(Collectors.toList());
        //遍历集合，将排好序的键值对Entry<K,V>放入新的map并返回。
        list.forEach(ele->finalMap.put(ele.getKey(), ele.getValue()));
        return finalMap;
    }

    //将文本内容读取到Set中，用于加载词库
    private Set<String> readWordFile(String filepath){
        Set<String> wordSet=null;
        File file=new File(filepath);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
            if (file.isFile() && file.exists()) {
                wordSet = new HashSet<String>();
                BufferedReader br = new BufferedReader(read);
                String txt;
                while ((txt = br.readLine()) != null) {
                    wordSet.add(txt);
                }
                br.close();
            }
            read.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return wordSet;
    }

    //加载各个字典
    private void Load_dicts(String filename){
        Path path= Paths.get(basePath,"\\dicts\\",filename);
        WordDictionary.getInstance().loadUserDict(path);
        wuru=readWordFile("src/main/java/com/anyic/dicts/wuru.txt");
        guli=readWordFile("src/main/java/com/anyic/dicts/guli.txt");
        wenda=readWordFile("src/main/java/com/anyic/dicts/wenda.txt");
        stop_words=readWordFile("src/main/java/com/anyic/dicts/stop_words.txt");
        duixiang=readWordFile("src/main/java/com/anyic/dicts/duixiang.txt");
    }

    //输出String中含有多少文字
    private float GetWord_count(String data){
        float ans=0;
        char[] array=data.toCharArray();
        for(char c :array){
            if((c >= 0x4e00)&&(c <= 0x9fbb)){
                ans++;
            }
        }
        return ans;
    }

    //获得语速
    private void Get_yusu(String data,float time){
        yusu=GetWord_count(data);
        yusu=(yusu/time)*60;
    }
    //仅用于测试的函数
    public void show_result(){
        System.out.println(result);
        System.out.printf("语速是%f每分钟\n",yusu);
        System.out.printf("wuru_Count=%d\n",wuru_Count);
        System.out.printf("guli_Count=%d\n",guli_Count);
        System.out.printf("wenda_Count=%d\n",wenda_Count);
        cipin.forEach((key,value)->{
            System.out.println(key);
            System.out.println(value);
        });
    }
//仅做测试分句作用
    public void show_Sens(){
        for(Sentence s : Sens){
            System.out.println(s.words);
            System.out.println(s.time);
            if(s.istiwen){
                System.out.println("是提问句");

            }else if(s.iswuru){
                System.out.println("是侮辱句");
            }else if(s.isguli){
                System.out.println("是鼓励句");
            }
        }
        System.out.printf("语速是%f每分钟\n",yusu);
        System.out.printf("wuru_Count=%d\n",wuru_Count);
        System.out.printf("guli_Count=%d\n",guli_Count);
        System.out.printf("wenda_Count=%d\n",wenda_Count);
    }


    //将data内容以句号为分界分句，并且标记句子的对象、问答、鼓励、时间等属性。
    private void Fenju(List<String> data){
        int i=0;int tem=0;
        int pos=0;//指句子中的第几个词
        List<String> temptime=new ArrayList<>();
        Sens.add(new Sentence());
        temptime.add("0");
        temptime.add("0");
        temptime.add("0");
        for(String s : data){
            if(Objects.equals(s, " ")|| Objects.equals(s, "\n")){continue;}

            if(tem<=2){
                temptime.set(tem,s);
                tem++;
                continue;
            }
            if(Objects.equals(s, "st")){
                tem=0;
                continue;
            }
            //遇到。！？句子结束，对句子成分进行分析，获得其属性
            if(Objects.equals(s, "。")|| s.equals("！")||s.equals("？")){
                pos=0;
                Sens.get(i).words.add(s);
                Sens.add(new Sentence());
                Sens.get(i).time.set(0,temptime.get(0));
                Sens.get(i).time.set(1,temptime.get(1));
                Sens.get(i).time.set(2,temptime.get(2));
                if(Sens.get(i).duixiang=="0"){

                    if(Sens.get(i).istiwen){
                        wenda_Count++;
                        Sens.get(i).iswuru=false;
                        Sens.get(i).isguli=false;
                    } else if(Sens.get(i).isguli){
                        guli_Count++;
                        Sens.get(i).istiwen=false;
                        Sens.get(i).iswuru=false;

                    }else{
                        Sens.get(i).iswuru=false;
                    }
                }else{
                    if(Sens.get(i).istiwen){
                        if(Sens.get(i).duixiang2!="0"){
                            if(Sens.get(i).iswuru){
                                wuru_Count++;
                                Sens.get(i).istiwen=false;
                                Sens.get(i).isguli=false;
                            }
                        }else{
                            wenda_Count++;
                            Sens.get(i).isguli=false;
                            Sens.get(i).iswuru=false;
                        }
                    }else{
                        if(Sens.get(i).iswuru){
                            wuru_Count++;
                            Sens.get(i).isguli=false;
                            Sens.get(i).istiwen=false;

                        }else{
                            if(Sens.get(i).isguli){
                                guli_Count++;
                            }
                        }
                    }
                }
                i++;
            }
            //提取每个词，加入到句子当中，并判断其对句子成分的影响
            else {
                //高频词统计
                if(!stop_words.contains(s)) {
                    if (!cipin.containsKey(s)) {
                        cipin.put(s, 1);
                    } else {
                        cipin.put(s, cipin.get(s) + 1);
                    }
                }

                Sens.get(i).words.add(s);
                if(duixiang.contains(s)){
                    if(Objects.equals(Sens.get(i).duixiang, "0")||Sens.get(i).duixiang=="你"){
                        Sens.get(i).duixiang=s;
                    } else if (Sens.get(i).istiwen&&Sens.get(i).duixiang!="0"&&Sens.get(i).duixiang!="你") {
                        Sens.get(i).duixiang2=s;
                    }

                } else if(wenda.contains(s)){
                    Sens.get(i).istiwen=true;
                }else if(wuru.contains(s)){
                    Sens.get(i).iswuru=true;
                    Sens.get(i).wuru_pos=pos;
                }else if(guli.contains(s)){
                    Sens.get(i).isguli=true;
                }
                pos++;
            }
        }
        //收尾工作

        int hou_sec=3600*(Integer.parseInt(temptime.get(0))-Integer.parseInt(Sens.get(0).time.get(0)));
        int min_sec=60*(  Integer.parseInt(temptime.get(1))-Integer.parseInt(Sens.get(0).time.get(1)));
        int sec=          Integer.parseInt(temptime.get(2))-Integer.parseInt(Sens.get(0).time.get(2));
        timelength=hou_sec+min_sec+sec;//计算文本时长。最终的时间会存在temptime中
        Sens.remove(Sens.size()-1);//删去无用的最后一个sentence
        cipin=sortMapByValues(cipin);//高频词排序
    }

    //对文本进行分析，获得各项指标
    public void GetString_analyse(String data,float time){
        Load_dicts("dict.txt");
        Get_yusu(data,time);

        result=segmenter.sentenceProcess(data);

        result = result.stream().map(o -> o.trim()).filter(o -> !stop_words.contains(o)).collect(Collectors.toList());
        for(String s : result){
            if(stop_words.contains(s)){
                continue;
            }
            if(!cipin.containsKey(s)){
                cipin.put(s,1);
            }
            else{
                cipin.put(s,cipin.get(s)+1);
            }

            if(wuru.contains(s)){
                wuru_Count++;
            }
            else if(guli.contains(s)){
                guli_Count++;
            }
            else if(wenda.contains(s)){
                wenda_Count++;
            }
        }
        cipin=sortMapByValues(cipin);


    }
    public void GetString_analyse2(String data){
        Load_dicts("dict.txt");
        LocalDate date = LocalDate.now(); // get the current date
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        nianyueri=date.format(formatter);
        result=segmenter.sentenceProcess(data);
        Fenju(result);
        Get_yusu(data,timelength);


    }

    //获得前number个高频词的list
    public List<String> Get_gaopinci(Integer number){
        List<String> ans=new ArrayList<>();
        Iterator<String> itr = cipin.keySet().iterator();
        while (itr.hasNext() && number > 0) {
            ans.add(itr.next());
            number--;
        }
        return ans;
    }
    //获得全部句子
    public List<Sentence> Get_AllSentences(){
        return Sens;
    }
}
