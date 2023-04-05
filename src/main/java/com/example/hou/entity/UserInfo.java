package com.example.hou.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
//@Data 就是lombok 的注解 自动生成了set get
@Data

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_info")  //@TableName 对应你的数据库表名
public class UserInfo {
   /*public UserInfo(Integer id, String username, String password){
        this.username = username;
        this.id = id;
        this.password = password;

    }*/
    @TableId(value = "id",type = IdType.AUTO)  //@TableId 说明这条数据自增长也是对应数据库自增长的
    private Integer id;
    @TableField("username")//这里和数据库表对应
    private String username;
    @TableField("password")
    private String password;
    //private LocalDateTime createTime;
}
