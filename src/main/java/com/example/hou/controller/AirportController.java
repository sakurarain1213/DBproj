package com.example.hou.controller;


import com.example.hou.entity.Airport;
import com.example.hou.entity.Book;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.AirportService;
import com.example.hou.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


//只是用于测试
/*

{
"id":12

}
 */



@Slf4j
@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @RequestMapping("/search")
    public Result any(@RequestBody Airport a) {
        String msg = airportService.searchService(a);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("找到机场");
        } else {
            return ResultUtil.error(msg);
        }
    }

}

