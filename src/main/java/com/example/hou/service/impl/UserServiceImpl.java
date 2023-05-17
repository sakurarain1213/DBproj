package com.example.hou.service.impl;

import com.example.hou.entity.User;
import com.example.hou.mapper.UserMapper;
import com.example.hou.service.UserService;
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
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;



    @Override
    public String loginService(User user) {

        User userE = userMapper.searchBySfz(user.getSfz());
        if (userE != null) {
            if (userE.getPassword().equals(user.getPassword())) {
                return "SUCCESS";
            } else {
                return "密码错误";
            }
        }
        return "此用户不存在";
    }

    /*
    用string版本    作为参考
        @Override
        public String loginService(String username,String password) {
            UserInfo userInfo = userInfoMapper.searchByUsername(username);
            if (userInfo != null) {
                if (password.equals(userInfo.getPassword())) {
                    return "SUCCESS";
                } else {
                    return "密码错误";
                }
            }
            return "此用户不存在";
        }
     */
    @Override
    public String registerService(User user) {
        User userE = userMapper.searchBySfz(user.getSfz());
        if (userE == null) {
            if ("".equals(user.getPassword())) {
                return "请输入密码";
            } else if ("".equals(user.getSfz())) {
                return "请输入身份证";
            } else {
                userMapper.insert(user);
                return "SUCCESS";
            }
        }
        return "用户已存在";
    }


}
