package com.example.hou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName(value = "airport")
public class Airport {

    private static final long serialVersionUID = 1L;
    /**
     * 机场主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String name;

    private String city;

    private String code;
    private String terminal;
    private String country;
}
