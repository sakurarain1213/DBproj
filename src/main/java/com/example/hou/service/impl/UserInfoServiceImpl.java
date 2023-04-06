package com.example.hou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hou.entity.UserInfo;
import com.example.hou.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hou.service.UserInfoService;
/**
 * @program: testhou
 * @description:
 * @author: 作者
 * @create: 2023-04-05 14:10
 */


@Service

public class UserInfoServiceImpl /* extends ServiceImpl<UserInfoMapper, UserInfo> */implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
/*    也不用手搓
    public UserInfo getUserInfo(int id) {
        return userInfoMapper.getUserInfo(id);
    }

    public int deleteById(int id) {
        return userInfoMapper.deleteById(id);
    }

    public int Update(UserInfo userInfo) {
        return userInfoMapper.update(userInfo);
    }

    public UserInfo save(UserInfo userInfo) {
        int save = userInfoMapper.save(userInfo);
        return userInfo;
    }

    public List<UserInfo> selectAll() {
        return UserInfoMapper.selectAll();
    }
*/


    //尝试将两个string改成一个整体的对象
    /*
    public String loginService(String un, String pw) {
        UserInfo userInfo = userInfoMapper.searchByUsername(un);
        if (userInfo != null) {
            if (pw.equals(userInfo.getPassword())) {
                return "SUCCESS";
            } else {
                return "密码错误";
            }
        }
        return "此用户不存在";
    }
*/
    @Override
    public String loginService(UserInfo userInfo) {

        UserInfo userE = userInfoMapper.searchByUsername(userInfo.getUsername());
        if (userE != null) {
            if (userE.getPassword().equals(userInfo.getPassword())) {
                return "SUCCESS";
            } else {
                return "密码错误";
            }
        }
        return "此用户不存在";
    }
/*
用string的登录版本    作为参考
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
    public String registerService(UserInfo userInfo) {
        UserInfo userE = userInfoMapper.searchByUsername(userInfo.getUsername());
        if (userE == null) {
            if ("".equals(userInfo.getPassword())) {
                return "请输入密码";
            } else if ("".equals(userInfo.getUsername())) {
                return "请输入用户昵称";
            } else {
                userInfoMapper.insert(userInfo);
                return "SUCCESS";
            }
        }
        return "用户已存在";
    }



}
