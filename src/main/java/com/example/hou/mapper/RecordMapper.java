package com.example.hou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hou.entity.ExampleRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsin
 * @since 2023-04-08
 */
@Repository//注解
@Mapper
public interface RecordMapper extends BaseMapper<ExampleRecord> {

}
