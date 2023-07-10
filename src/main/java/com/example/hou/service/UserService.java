package com.example.hou.service;

import com.example.hou.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsin
 * @since 2023-05-17
 */
public interface UserService {

    public String loginService(User user);

    public String registerService(User user);

    public String updateService(User user);

    public User getService(User user);

}
