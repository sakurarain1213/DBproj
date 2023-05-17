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
@TableName(value = "user")
public  class User {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键 这里为了简便即身份证
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String name;

    private String sex;

    private Integer age;

    private String phonenum;

    private String password;

    /**
     * 管理员权限
     */
    private Integer access;
}
