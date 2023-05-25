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

机票查询
localhost:8080/flight/get
{
    "depAirport":"香港",
    "arrAirport":"北京"
}




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
        if (l.size()>0) {
            return ResultUtil.success(l);
        }
        else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }
    }

}

