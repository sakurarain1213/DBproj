package com.example.hou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hsin
 * @since 2023-04-08
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lesson")
public class Lesson  {

    private static final long serialVersionUID = 1L;

    /**
     * 课程表主键
     */
    @TableId(value = "lesson_id", type = IdType.AUTO)
    private Integer lessonId;

    /**
     * 教师表关联主键
     */
    private Integer userId;

    /**
     * 教师名字
     */
    private String username;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 年级班级
     */
    private String grade_class;

    /**
     * 开始时间（不用时间戳因为有2038问题）
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


}
