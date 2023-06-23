package com.example.hou.service;

import com.example.hou.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hou.entity.Flight;
import com.example.hou.entity.Temp_hot_destination;

import java.util.List;
import java.util.Map;


public interface BookService {

    public String bookService(Book book);
    public List<Flight> historyService(Book book);

    public String refundService(Book book);


    public List<Temp_hot_destination> hotbook();

}
