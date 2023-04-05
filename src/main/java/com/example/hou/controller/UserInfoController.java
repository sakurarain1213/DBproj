package com.example.hou.controller;

import com.example.hou.entity.UserInfo;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings({"all"}) //控制台输出过滤掉警告信息
@RestController//控制层标志，等价于@Controller+@ResponseBody
@Slf4j//lombok用于日志输出
@RequestMapping("/userInfo")//这里是浏览器8080后的地址 也就是对外接口地址

public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    //添加@ResponseBody注解将返回的数据自动转化为json, @RequestBody将接收的数据转化为json
    @RequestMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        String msg = userInfoService.loginService(username, password);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
    /*加@RequestParam注解：url必须带有参数。
    也就是说直接输入localhost:8080/userInfo/login 会报错，
    不会执行方法。只能输入localhost:8080/userInfo/login?userId=xxx 才能执行相应的方法*/

    @RequestMapping("/register")
    public Result login(@RequestBody UserInfo userInfo) {
        String msg = userInfoService.registerService(userInfo);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }





/*
    @Autowired
    private UserInfoService userInfoService;

    //增加
    @RequestMapping(value="/add",method = RequestMethod.POST)//增加了method选项
    public UserInfo add(@RequestBody UserInfo userInfo) {
        userInfo.setCreateTime(LocalDateTime.now());
        return userInfoService.save(userInfo);
    }

    //删除
    @RequestMapping("delete/{id}")
    public int delete(@PathVariable Integer id) {
        return userInfoService.deleteById(id);
    }

    //修改
    @RequestMapping("/update")
    public int update(@RequestBody UserInfo userInfo) {
        return userInfoService.Update(userInfo);
    }

    //查询
    @RequestMapping("/get/{id}")
    public UserInfo get(@PathVariable Integer id) {
        return userInfoService.getUserInfo(id);
    }

    //查询全部
    @RequestMapping("/list")
    public List<UserInfo> list() {
        return userInfoService.selectAll();
    }
*/
}
