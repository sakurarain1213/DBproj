package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.hou.entity.*;
import com.example.hou.mapper.AirportMapper;
import com.example.hou.mapper.BookMapper;
import com.example.hou.mapper.FlightMapper;
import com.example.hou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.hou.service.impl.CheckPassword.checkPasswordRule;
import static com.example.hou.service.impl.IdCardNumberUtils.getAgeFromIdCard;
import static com.example.hou.service.impl.IdCardNumberUtils.isIdCard;

/**
 *  服务实现类
 *
 * @author hsin
 * @since 2023-05-17
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    FlightMapper flightMapper;

    @Autowired
    AirportMapper airportMapper;

    @Override
    public String bookService(Book book) {

        if ("".equals(book.getUserId())) {
            return "需要用户信息";
        } else if ("".equals(book.getFlightId())) {
            return "请选择航班";
        } //else if (book.getDate() == null) {
           // return "请选择日期";
        //}
        else if ((book.getSeatnum() == null)) {
            return "请选择座位号";
        } else {


            //经典方法有二  传入对象既作为参数，也作为查询结果接受体  或者新建对象作为结果
            //如何判断已经订票 要用flight和user 两个ID同时确定 存在即重复订
            String u=book.getUserId();
            String f=book.getFlightId();
            //Book b = bookMapper.searchByOrdernum(book.getOrdernum());
            // mapper只能搜索主键
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("userId", u)
                    .eq("flightId", f);
            List<Book> B = bookMapper.selectList(queryWrapper);
            if(B.size()>0)  return "不得重复购票";


            /*
                先查询是否有座位
                多表联查再写表  航班座位-1   并且购票表加一条记录(加num列和此刻的timestamp)
            */
            //以下有问题    因为没有xml文件和对应sql   乐
            Flight F = flightMapper.searchByID(f);
            //这边有一个debug
            //ystem.out.println("中断测试！！！！！！！！！！！！！！！！！！！！！！！！！！");
            if(F==null) return "航班号无效";
            Integer seat=F.getAvailableSeats();
                if(seat==0)  return "该航班机票售罄";
            //对应航班座位-1  不要忘记update
            Flight temp = new Flight();
                temp.setAvailableSeats(seat-1);
                temp.setPrice(F.getPrice()); //尝试朴素debug

            //查询  条件构造器
            //重点debug  订票之后 价格为0的问题
            // 进行Update操作时 UpdateWrapper会将未指定的字段更新为0！！！！ 见上一行朴素debug
                UpdateWrapper<Flight> filghtUpdateWrapper = new UpdateWrapper<>();
                                filghtUpdateWrapper.eq("ID",f);
                flightMapper.update(temp, filghtUpdateWrapper);

            //当前时间 精确到秒
            Date date = new Date();
            //注意这个时间是格林尼治标准时间 东八区+8小时
                    long time=date.getTime()+8*3600000;
                    date.setTime(time);
            book.setDate(date);

            //随机一个12位 ordernum   自己手搓的函数
            book.setOrdernum(JavaRandom.randomNum());

            /*最后增加购票纪录 */
            bookMapper.insert(book);

            return "SUCCESS";

            }

    }




    @Override
    public List<Flight> historyService(Book book) {

        if ("".equals(book.getUserId()))  return null;

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("userId", book.getUserId());
            List<Book> B = bookMapper.selectList(queryWrapper);
           // return B;
            //debug  返回 book每一条的 flight ID



        QueryWrapper<Flight> f = new QueryWrapper<>();
        List<Flight> l;
        for (int i = 0; i <B.size(); i++) {
           f
                   .or()
                   .eq("ID",B.get(i).getFlightId());
        }
        l= flightMapper.selectList(f);
            //可以增加时间条件查询   再说





        //前端优化debug  把flight出发到达地的数字换成airport表的具体机场名字
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
            Flight temp=l.get(i);
            String depname=temp.getDepAirport();//是数字
            String arrname=temp.getArrAirport();//是数字

            //拿数字找
            Airport depA=airportMapper.selectById(depname);//这个是默认存在方法 返回一个对象
            Airport arrA=airportMapper.selectById(arrname);

            //System.out.println(depA); System.out.println(arrA); 然后拼接成字符串即可
            //String重复利用罢了
            depname=depA.getName()+depA.getCode();
            arrname=arrA.getName()+arrA.getCode();

            temp.setDepAirport(depname);temp.setArrAirport(arrname);
            l.set(i,temp);
        }

            return l;

    }


    //退票
    @Override
    public String refundService(Book book) {
        if ("".equals(book.getUserId())) {
            return "需要用户信息";
        } else if ("".equals(book.getFlightId())) {
            return "请选择航班";
        }
        //else if ("".equals(book.getOrdernum())) {
        //return "需要订单号";   这个num似乎没用
        //}
        else {

            String u=book.getUserId();
            String f=book.getFlightId();
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("userId", u)
                    .eq("flightId", f);
            List<Book> B = bookMapper.selectList(queryWrapper);
            if(B.size()==0)  return "没有购票记录 无法退票";


            /*
                多表联查再 航班座位要重新+1
            */
            Flight F = flightMapper.searchByID(f);
            if(F==null) return "航班号无效";
            Integer seat=F.getAvailableSeats();
            Flight temp = new Flight();
            temp.setAvailableSeats(seat+1);
            UpdateWrapper<Flight> filghtUpdateWrapper = new UpdateWrapper<>();
            filghtUpdateWrapper.eq("ID",f);
            flightMapper.update(temp, filghtUpdateWrapper);

            /*购票book表记录删除 （最好是加退票日志而不是直接删除  即逻辑删除）*/
            //DeleteWrapper<Book> wrapper = new DeleteWrapper<>(); 报错  delete条件构造器不存在
            //并且id无效  因此delete by id方法无效  因此尝试map类型的删除
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", u);
            map.put("flightId", f);
            int result = bookMapper.deleteByMap(map);
            if(result==1) return "SUCCESS";
            else return "出错 涉及多条记录删除 请检查";
        }


    }


    @Override
    public List<Temp_hot_destination> hotbook(){

        //购票纪录中的   热门目的地查询
        //返回的是一个符合类型  地点+次数 相当于改变了表的结构 返回复杂耦合内容
        //涉及订票表关联到机票表    groupby
        //关于Mybatis plus 使用QueryWrapper,group by 分组 + having ,自定义查询字段使用方法
        /*   逻辑如下

        SELECT flight.arrAirport AS destination, COUNT(*) AS count
            FROM book b
            JOIN flight f ON b.flightId = f.ID
        GROUP BY f.destination
         ORDER BY count DESC;   先在navicat测试SQL 通过才行！！！
        正确SQL如下
       SELECT arrAirport AS destination, COUNT(*) AS count
            FROM book b JOIN flight f ON b.flightId = f.ID
        GROUP BY arrAirport
         ORDER BY count DESC;
        （ 最后  limit  10  可以选出前十大）
               q
                .select("flight.destination as destination , count(*) as count")
                .join("flight f on book.flightID = f.ID")
                .groupBy("f.destination")
                .orderByDesc("count");

          但是Mybatis Plus 封装的 mapper 不支持 join，
          如果需要支持就必须自己去实现。但是对于大部分的业务场景来说，
          都需要多表 join，要不然就没必要采用关系型数据库了。
          用插件<dependency><groupId>com.github.yulichang</groupId><artifactId>mybatis-plus-join</artifactId><version>1.2.4</version></dependency>
          或者直接SQL  只需要在xml文件里写好sql  resulttype写成list中的每个元素即可    如下

          尝试性能优化   map本身就是一个很大的映射集合  外面不用加list
         yml 的配置文件里的url要加  & allowMultiQueries=true   才能用sql
        */
        List<Temp_hot_destination> d = bookMapper.hotDestination();

        //这里选十大热门目的地  转名称
        for(int i=0;i<d.size();i++) {
            Temp_hot_destination temp=d.get(i);
            String arrname=temp.getDestination();//是数字
            //拿数字找
            Airport arrA=airportMapper.selectById(arrname);

            arrname=arrA.getCity()+arrA.getCode();//换成城市名
            temp.setDestination(arrname);
            d.set(i,temp);
        }

        return  d;

    }







}