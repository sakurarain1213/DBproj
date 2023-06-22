package com.example.hou.controller;


import com.example.hou.entity.Book;
import com.example.hou.entity.Flight;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.BookService;
import com.example.hou.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
//购票
localhost:8080/book/add
{
    "userId":"110230195104089642",
    "flightId":"97MNR08V",
    "seatnum":"123"
}

//查自己的购票记录
localhost:8080/book/history
{
    "userId":"110230195104089642",
}

//退自己买的票
localhost:8080/book/delete
{
    "userId":"110230195104089642",
    "flightId":"97MNR08V"
}


 */




@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
   // @Autowired
   // FlightService flightService;

    @RequestMapping("/add")
    public Result bookadd(@RequestBody Book book) {
        String msg = bookService.bookService(book);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("订票成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @RequestMapping("/history")
    public Result historybook(@RequestBody Book book) {
        List<Flight> l = bookService.historyService(book);
        if (l.size()>0) {
            //相当于重新打开了ResultUtil的封装  自定义返回消息也在返回类的属性位置编辑
            Result r=new Result();
            r.setCode(200);
            r.setMsg("成功查询到记录数量："+l.size());
            r.setData(l);
            return r;
        }
        else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }
    }

    @RequestMapping("/delete")
    public Result bookdelete(@RequestBody Book book) {
        String msg = bookService.refundService(book);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("退票成功");
        } else {
            return ResultUtil.error(msg);
        }
    }


}

