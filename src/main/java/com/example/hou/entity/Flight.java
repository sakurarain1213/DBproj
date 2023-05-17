package com.example.hou.entity;

import java.math.BigDecimal;
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
@TableName(value = "flight")
public  class Flight  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String airline;

    @TableId(value = "dep_airport")
    private String depAirport;

    @TableId(value = "arr_airport")
    private String arrAirport;

    @TableId(value = "dep_time")
    private Date depTime;

    @TableId(value = "arr_time")
    private Date arrTime;

    private BigDecimal price;

    @TableId(value = "available_seats")
    private Integer availableSeats;


}
