package com.example.hou.service;


import com.example.hou.entity.UserInfo;



public interface UserInfoService {

    public String loginService(String uact, String upwd);

    public String registerService(UserInfo user);

}
