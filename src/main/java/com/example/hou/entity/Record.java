package com.example.hou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/*

上传格式

{
    "username":"6",
    "txtFile":"中文测试english test",
    "endTime":"2023-04-09 12:12:12"
    注意 一定要按照时分秒的格式 不能少
}

 */

@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "record")
public class Record {

    private static final long serialVersionUID = 1L;

    /**
     * 记录主键
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 课程主键
     */
    private Integer lessonId;

    /*
    操作简化 只让人和记录直接关联  不要通过课程表了
     */
   private String username;//!!!!!!!!!!!degub


    /**
     * 文本内容（不考虑存路径 直接存文件本身）
     */
    @TableField("txt_file")
    private String txtFile;//!!!!!!!!!!!!!!一定要有注解！@！！！！！
    //注意传json时字段名还是要为txtFile！！！！！！！！！！！！！！！！！！！！！！！！！

    /**
     * 语音内容
     */
    private Blob mp3File;

    /**
     * 开始时间
     */
    //这种注解 不支持时间戳的解析!!!
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    // 前端需要传这种格式的字符串
   //@DateTimeFormat是前端往后段传的时候使用，加在实体类中，然后controller中直接使用这个实体类接收参数。当前端传固定格式的字符串的时候会转换成date
    //@JsonFormat是后端往前端传输的时候使用。
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;

    /**
     * 评分  因为要细化 所以存成json
     */
    private String score;//备注  json在实体类里还是用string


}
