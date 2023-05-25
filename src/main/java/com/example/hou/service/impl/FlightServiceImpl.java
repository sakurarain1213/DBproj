package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hou.entity.Airport;
import com.example.hou.entity.Flight;

import com.example.hou.mapper.AirportMapper;
import com.example.hou.mapper.FlightMapper;

import com.example.hou.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 *
 * **/
        String city1 = flight.getDepAirport();
        String city2 = flight.getArrAirport();

        // 其它属性都是没有也行
        String name = flight.getAirline();//航空公司
        Date time1 = flight.getDepTime();
        Date time2 = flight.getArrTime();
        float price = flight.getPrice();
        Integer seat = flight.getAvailableSeats();
        String service = flight.getModel();


       if (city1 == null || city2 == null) {
            return null;
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

        for (int i = 0; i <l1.size(); i++) {
            for (int j = 0; j <l2.size(); j++) {
                //然后进行已知地点的机票查询
                q
                        .eq("depAirport", l1.get(i).getId())
                        .eq("arrAirport", l2.get(j).getId());
            }
        }

        //然后可以查询其它信息 再返回

        List<Flight> l = flightMapper.selectList(q);
        return l;
    }

}
