package com.example.hou.service;

import com.example.hou.entity.Flight;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsin
 * @since 2023-05-17
 */
public interface FlightService {

    public String searchService(Flight flight);
    public List<Flight> getFlightService(Flight flight);

    public List<Flight> lowprice();

}
