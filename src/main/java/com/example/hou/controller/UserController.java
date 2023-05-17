package com.example.hou.controller;


import com.example.hou.entity.User;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/*
测试接口

{
    "ID":"157347196610093469",
    "password":"123"
}




 */




@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        String msg = userService.loginService(user);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }


    @RequestMapping("/register")
    public Result register(@RequestBody User user) {
        String msg = userService.registerService(user);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }




}

