package com.example.hou.mapper;

import com.example.hou.entity.Airport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
//别忘记注解  不多赘述

@Repository
@Mapper
public interface AirportMapper extends BaseMapper<Airport> {

    //这里可以写一些简单的自动生成函数 给service用  一般用主键找   但是一定要在xml  （这个项目在resource下）写！！！
    Airport searchByID (String the_id_if_Airport);


}
