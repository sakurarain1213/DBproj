package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.hou.entity.Book;
import com.example.hou.entity.Flight;
import com.example.hou.entity.User;
import com.example.hou.mapper.AirportMapper;
import com.example.hou.mapper.BookMapper;
import com.example.hou.mapper.FlightMapper;
import com.example.hou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
            //查询  条件构造器
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
    public List<Book> historyService(Book book) {

        if ("".equals(book.getUserId()))  return null;

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("userId", book.getUserId());
            List<Book> B = bookMapper.selectList(queryWrapper);
            return B;
            //可以增加时间条件查询   再说


    }


}