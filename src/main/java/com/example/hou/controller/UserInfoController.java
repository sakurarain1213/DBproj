package com.example.hou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.hou.entity.UserInfo;
import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;
import com.example.hou.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings({"all"}) //控制台输出过滤掉警告信息
@RestController//控制层标志，等价于@Controller+@ResponseBody
@Slf4j//lombok用于日志输出
@RequestMapping("/userInfo")//这里是浏览器8080后的地址 也就是对外接口地址

public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    //添加@ResponseBody注解将返回的数据自动转化为json, @RequestBody将接收的数据转化为json
    @RequestMapping("/login")
//@ResponseBody//重点debug区域  即只能在params不能在body传递json的问题  这一行无所谓
    //下面把requestbody删除 则至少用params可以 这个符合简单教程
   public Result login(@RequestBody UserInfo userInfo) {
       String msg = userInfoService.loginService(userInfo);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
    /*
    @ResponseBody
    public Result login( String username, String password, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUsername, username).eq(UserInfo::getPassword, password);
        UserInfo user = userInfoService.getOne(queryWrapper);

        if(user == null){
            //登录失败
            return ResultUtil.error("用户名或密码错误");
        }
        //登录成功
        session.setAttribute("loginUser", user);

        //如果需要记住密码，则添加cookie
        /*
        if("true".equals(isRemPwd)){
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(7*24*60*60);         //cookie保存7天
            cookie.setPath(request.getContextPath()+"/");    //设置cookie存在的路径
            response.addCookie(cookie);
        }else{
            //如果不需要记住密码，则将以前创建的cookie删掉
            Cookie cookie = new Cookie("username", null);
            cookie.setMaxAge(0);   //删除cookie
            cookie.setPath(request.getContextPath()+"/");    //设置cookie存在的路径
            response.addCookie(cookie);
        }

        return ResultUtil.success("登录成功");

    }
*/
    /*加@RequestParam注解：url必须带有参数。
    也就是说直接输入localhost:8080/userInfo/login 会报错，
    不会执行方法。只能输入localhost:8080/userInfo/login?username=xxx&password=XXX 才能执行相应的方法*/
    //添加@ResponseBody注解将返回的数据自动转化为json, @RequestBody将接收的数据转化为json
//重点debug  这边是可以用json传递的
    @RequestMapping("/register")
    public Result register(@RequestBody UserInfo userInfo) {
        String msg = userInfoService.registerService(userInfo);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }
      /*
    @RequestMapping("/register")
    @ResponseBody
    public Result register(UserInfo userInfo){
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(UserInfo::getUsername, userInfo.getUsername());
        UserInfo registerUser = userInfoService.getOne(queryWrapper);

        if(registerUser != null){
            return ResultUtil.error("用户名已存在");
        }
        userInfoService.save(userInfo);
        return ResultUtil.success(userInfo);
    }*/





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
