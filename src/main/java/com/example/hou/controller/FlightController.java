package com.example.hou.controller;


import com.example.hou.entity.Flight;
import com.example.hou.entity.User;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.FlightService;
import com.example.hou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
查询localhost:8080/flight/search
{
    "id":"4CV36R0G"
}

因为mapper 没有写id的SQL 现在不可用！！！



机票查询
localhost:8080/flight/get     depTime出发时间如果不选择就默认实际的当天时间
价格写了就限定最高价   机场可以精确到名称和机场代码
{
    "depAirport":"上海浦东",
    "arrAirport":"西安",
    "depTime":"2022-12-31 00:00:00",
    "price":1640
}


低价机票查询
localhost:8080/flight/lowprice
不需要传参


 */
@Slf4j
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @RequestMapping("/search")
    public Result search(@RequestBody Flight flight) {
        String msg = flightService.searchService(flight);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("查询成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @RequestMapping("/get")
    public Result getflight(@RequestBody Flight flight) {
        List<Flight> l = flightService.getFlightService(flight);
        if (l != null) {
            //相当于重新打开了ResultUtil的封装  自定义返回消息也在返回类的属性位置编辑
            Result r = new Result();
            r.setCode(200);
            r.setMsg("成功查询到记录数量：" + l.size());
            r.setData(l);
            return r;
        } else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }
    }


    @RequestMapping("/lowprice")
    public Result lowprice() {
        List<Flight> l = flightService.lowprice();
        if (l != null) {
            Result r = new Result();
            r.setCode(200);
            r.setMsg("成功查询到低价机票数量：" + l.size());
            r.setData(l);
            return r;
        } else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }

    }

}