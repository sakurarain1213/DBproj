package com.example.hou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "book")
public  class Book {

    private static final long serialVersionUID = 1L;

    private String ordernum;

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;

    @TableId(value = "flight_id")
    private String flightId;

    private Date date;

    private Integer seatnum;

    private String service;


}