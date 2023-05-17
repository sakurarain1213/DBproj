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

}
