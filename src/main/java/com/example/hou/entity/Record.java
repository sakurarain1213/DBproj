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
 {
    "username":"6",
    "startTime":"2023-04-10 23:59:50",
    "endTime":"2023-04-10 23:59:59",
    "txtFile":"傻逼sdhabi"
}
    注意 一定要按照时分秒的格式 不能少

}

查询
{
    "username":"6",
    "startTime":"2023-04-06 23:59:59",
    "endTime":"2023-04-09 23:59:59"
}

 */

@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "record")
//优化版本后  record表以句子为单位记录
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
    @TableField("lesson_id")
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
    @TableField("mp3_file")
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
    //一个字段存储Java对象序列化后的字段
    //因为不能直接用字符串，所以用成了字节数组（Java）(但是不方便数据单独运算)
    //@TableField(value="score",typeHandler = FastjsonTypeHandler.class)
    private String score;//备注  json在实体类里还是用string  但是需要注解  现在尝试直接存一个对象

/*
*     Fastjson 是阿里巴巴提供的一个Java语言编写的高性能功能完善的 JSON 库，是目前Java语言中最快的 JSON 库，可以实现 Java 对象和 JSON 字符串的相互转换。
      序列化： 将 集合数据转换为 json 数据；
     反序列化：将  json 数据转换为 Java 对象。
      java中如需返回JSON数据需要第三方jar包 ：例：  ①:jsonlib   ②: jackson  ③: fastjson(阿里巴巴)  ④:gson(谷歌)
mysql可以存储Java对象。
1、将Java对象中的每一个字段都存入表中。
优点：每个字段都可以被检索
缺点：针对每个需要存储的对象，都要创建数据表。一个类对应一张表
2、将Java对象序列化之后，存入Blob字段或者Text。
优点：可以存储超大的对象，并且没有大小限制
缺点：Blob数据类型的缺点，检索上存在问题
3、保存为JSON字符串，使用VARCHAR数据类型存储
优点：可以存储完整的对象，反序列化也很方便。
缺点：不方便检索内容，需要提前估计JSON字符串大小。
场景：适合不需要检索，只用查询和存储展示。
这里选择直接开多一点的列
*
* */
    /**
     * 是否侮辱
     */
    @TableField("iswuru")
    private Integer iswuru;

    /**
     * 具体
     */
    private String wuru;

    /**
     * 是否鼓励
     */
    @TableField("isguli")
    private Integer isguli;

    /**
     * 具体
     */

    private String guli;

    /**
     * 是否提问
     */
    @TableField("istiwen")
    private Integer istiwen;

    /**
     * 具体
     */
    private String tiwen;

    /**
     * 语速   无
     */
   // private Float yusu;

    /**
     * 换成ps  备注
     */
    private String ps;

}
