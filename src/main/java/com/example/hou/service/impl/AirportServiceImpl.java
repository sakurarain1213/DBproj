package com.example.hou.service.impl;

import com.example.hou.entity.Airport;
import com.example.hou.entity.Flight;
import com.example.hou.entity.User;
import com.example.hou.mapper.AirportMapper;
import com.example.hou.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsin
 * @since 2023-05-17
 */
@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportMapper airportMapper;//不要忘记注入


    @Override
    public String searchService(Airport airport) {

        Airport a = airportMapper.airportSearchID(airport.getId());
        if (a != null) {
                return "SUCCESS";
        }
        return "机场不存在";
    }



}
