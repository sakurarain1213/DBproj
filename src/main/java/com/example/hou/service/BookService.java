package com.example.hou.service;

import com.example.hou.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hou.entity.Flight;

import java.util.List;


public interface BookService {

    public String bookService(Book book);
    public List<Flight> historyService(Book book);

    public String refundService(Book book);
}
