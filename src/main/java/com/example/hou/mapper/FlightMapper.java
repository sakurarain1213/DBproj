package com.example.hou.mapper;

import com.example.hou.entity.Flight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FlightMapper extends BaseMapper<Flight> {

    Flight searchByID (String the_flight_id);
}
