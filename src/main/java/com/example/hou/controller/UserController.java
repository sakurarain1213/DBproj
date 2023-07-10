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
登录
localhost:8080/user/login
{
    "sfz":"157347196610093469",
    "password":"123"
}

注册
localhost:8080/user/register
{
    "sfz":"157347196610093469",
    "password":"123"
}

更新
localhost:8080/user/update
{
    "sfz":"123456789012345677",
    "password":"123Q!1234",
    "phonenum":"15757106037",
    "name":"张三"
}

获得用户信息
localhost:8080/user/get
{
    "sfz":"123456789012345677"
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

    @RequestMapping("/update")
    public Result update(@RequestBody User user) {
        String msg = userService.updateService(user);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("用户信息更新成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @RequestMapping("/get")
    public Result getUser(@RequestBody User user) {
        User u = userService.getService(user);
        if (u!=null) {
            return ResultUtil.success(u);
        } else {
            return ResultUtil.error("获取失败");
        }
    }

}

