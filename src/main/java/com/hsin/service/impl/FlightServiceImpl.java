package com.hsin.service.impl;

import com.example.hou.entity.Flight;
import com.hsin.mapper.FlightMapper;
import com.hsin.service.FlightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FlightServiceImpl extends ServiceImpl<FlightMapper, Flight> implements FlightService {

}
