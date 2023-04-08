package com.anyic;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import java.io.File;
import java.nio.file.Paths;
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

public class Wenbenchuli {
    private static String basePath="C:\\Users\\w1625\\desktop\\hou_fin\\src";
    public int wuru_Count;//侮辱次数
    public int guli_Count;//鼓励次数
    public int wenda_Count;//提问次数

    public float yusu;//语速
    public JiebaSegmenter segmenter;//jieba类
    public Set<String> stop_words;//停用词
    public List<String> result;//存储分词结果
    public static Set<String> wuru;//侮辱词库
    public static Set<String> guli;//鼓励词库
    public static Set<String> wenda;//问答词库
    public Map<String, Integer> cipin;//统计词频
    Wenbenchuli(){
        result=new ArrayList<>();
        wuru=new HashSet<>();
        guli=new HashSet<>();
        wenda=new HashSet<>();
        cipin=new LinkedHashMap<>();
        stop_words=new HashSet<>();
        segmenter=new JiebaSegmenter();
        wuru_Count=0;
        guli_Count=0;
        wenda_Count=0;
        yusu=0;
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

    private void Load_dicts(String filename){
        Path path= Paths.get(basePath,"\\main\\java\\com\\anyic\\dicts",filename);
        WordDictionary.getInstance().loadUserDict(path);
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
    //对文本进行分析，获得各项指标
    public void GetString_analyse(String data,float time){
        Load_dicts("dict.txt");
        yusu=GetWord_count(data);
        yusu=(yusu/time)*60;

        wuru=readWordFile("src/main/java/com/anyic/dicts/wuru.txt");
        guli=readWordFile("src/main/java/com/anyic/dicts/guli.txt");
        wenda=readWordFile("src/main/java/com/anyic/dicts/wenda.txt");
        stop_words=readWordFile("src/main/java/com/anyic/dicts/stop_words.txt");

        result=segmenter.sentenceProcess(data);
        result = result.stream().map(o -> o.trim()).filter(o -> !stop_words.contains(o)).collect(Collectors.toList());
        for(String s : result){
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
    public List<String> Get_gaopinci(Integer number){
        List<String> ans=new ArrayList<>();
        Iterator<String> itr = cipin.keySet().iterator();
        while (itr.hasNext() && number > 0) {
            ans.add(itr.next());
            number--;
        }
        return ans;
    }
}
