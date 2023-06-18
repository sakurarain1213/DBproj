package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hou.entity.Airport;
import com.example.hou.entity.Flight;

import com.example.hou.mapper.AirportMapper;
import com.example.hou.mapper.FlightMapper;

import com.example.hou.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hibernate.criterion.Restrictions.like;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsin
 * @since 2023-05-17
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightMapper flightMapper;
    @Autowired
    AirportMapper airportMapper;

    @Override
    public String searchService(Flight flight) {

        Flight E = flightMapper.searchByID(flight.getId());
        if (E != null) {
            return "SUCCESS";
        }
        return "航班不存在";
    }

    @Override
    public List<Flight> getFlightService(Flight flight) {
/***
 * 重大debug
 * 使用mybatis-plus时，当且仅当java类中带下划线的属性会返回null    映射问题
 * **/
        //debug  防止list直接传回null  l写在一开始比较好
        String city1 = flight.getDepAirport();
        String city2 = flight.getArrAirport();
        //Date time=flight.getDepTime();
        Date time1 = flight.getDepTime();
        // 其它属性都是没有也行
        String name = flight.getAirline();//航空公司

        Date time2 = flight.getArrTime();
        float price = flight.getPrice();
        Integer seat = flight.getAvailableSeats();
        String service = flight.getModel();


       if (city1 == null || city2 == null) {
            return null;
        }
       //如果不传时间 默认用当前时间
       if(time1==null)
       {
        Date now=new Date();//东八区相差8小时debug
           time1=new Date();
        time1.setTime(now.getTime()+8*3600000);
       }

        //尝试用wrapper 实现SQL的等于 介于 大 小  筛选 合并 查询   尤其要实现外键关联的多表联查
        //先根据模糊机场名找到代码   注意一个城市有两个机场的可能

        QueryWrapper<Airport> qw1 = new QueryWrapper<>();//注意这里是机场表
        qw1
                .select("airport.id")
                .like("airport.name", city1);
        List<Airport> l1 = airportMapper.selectList(qw1);
        QueryWrapper<Airport> qw2 = new QueryWrapper<>();//注意这里是机场表
        qw2
                .select("airport.id")
                .like("airport.name", city2);
        List<Airport> l2 = airportMapper.selectList(qw2);

        //先看看l1  l2 的结构是什么
        //然后经典遍历list

        QueryWrapper<Flight> q = new QueryWrapper<>();//注意回到机票表

        //准备时间筛选的工作
        //按照出发时间筛选
        //Date date = new Date();  这个是当前时间
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String format = sdf.format(date);
        //注意时间范围 是当时时间 到24小时之后
        Date beginDate = new Date();
        Date finishDate = new Date();
        long begintime=time1.getTime()-8*3600000;//同理 神秘的东八区相差8小时的debug
        long finishtime=time1.getTime()+24*3600000-8*3600000;
        beginDate.setTime(begintime);
        finishDate.setTime(finishtime);

        //debug   格式化成string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s1=sdf.format(beginDate);
        String s2=sdf.format(finishDate);

        for (int i = 0; i <l1.size(); i++) {
            for (int j = 0; j <l2.size(); j++) {
                //然后进行已知地点的机票查询
                q.or()// 重大debug  内外层循环是或的关系 不是而且的关系   而且时间筛选要写在内重循环里
                        .eq("depAirport", l1.get(i).getId())
                        .eq("arrAirport", l2.get(j).getId())
                        .gt("availableSeats", 0);// availableSeats > 0
                        //优化到先显示有票航班
                        //再写时间查询
                //System.out.println("!!!!!!!1"+s1+s2);
                //debug   Date类型的列用格式化的String来筛选
                        q.ge("depTime",s1)
                        .le("depTime",s2);
            }
        }
        //然后可以查询其它信息 再返回   也可以按照特定规则排序（尝试实现一下  加传入一个string即可）

        //默认排序
        q.orderByAsc("depTime");

        //有价格列再筛选 数值对象   float的空不是null  而是0.0  注意
        if(price!=0.0)  q.le("price",price);

        //测试传入任意值
        List <Flight> l = flightMapper.selectList(q);
        return l;
    }

}
