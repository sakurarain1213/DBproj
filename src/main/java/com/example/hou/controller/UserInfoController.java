package com.example.hou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;

import com.example.hou.result.Result;
import com.example.hou.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//public class UserInfoController {
  //  @Autowired
   // UserInfoService userInfoService;
    //添加@ResponseBody注解将返回的数据自动转化为json, @RequestBody将接收的数据转化为json
  //  @RequestMapping("/login")
//@ResponseBody//重点debug区域  即只能在params不能在body传递json的问题  这一行无所谓
    //下面把requestbody删除 则至少用params可以 这个符合简单教程
  // public Result login(@RequestBody UserInfo userInfo) {
   //    String msg = userInfoService.loginService(userInfo);
    //    if (("SUCCESS").equals(msg)) {
   //         return ResultUtil.success("登录成功");
   //     } else {
   //         return ResultUtil.error(msg);
   //     }
   // }
    /*   登录用string的版本  作为参考 最好全用对象
      public Result login(/*@RequestBody UserInfo userInfo String username,/*@RequestParam String password) {
        String msg = userInfoService.loginService(username,password);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("登录成功");
        } else {
            return ResultUtil.error(msg);
        }
    } */
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
/*
    @RequestMapping("/register")
    public Result register(@RequestBody UserInfo userInfo) {
        String msg = userInfoService.registerService(userInfo);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("注册成功");
        } else {
            return ResultUtil.error(msg);
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody UserInfo userInfo) {
        String msg = userInfoService.updateService(userInfo);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("修改成功");}
        else{ return ResultUtil.error(msg);}
    }*/
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












/*通用样例

@RestController
@Slf4j
@RequestMapping("/lesson")
public class LessonController {

}









@RestController
@Slf4j
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;//不要忘记注入

    @RequestMapping("/add")
    public Result recordAdd(@RequestBody ExampleRecord exampleRecord) {
        String msg = recordService.recordAddService(exampleRecord);
        if (("SUCCESS").equals(msg)) {
            return ResultUtil.success("语音文本上传成功");
        } else {
            return ResultUtil.error(msg);
        }
    }


    @RequestMapping("/get")
    //因为返回的是一个list  所以消息需要根据新的格式自定义
    public Result recordGet(@RequestBody ExampleRecord exampleRecord) {
        List<ExampleRecord> l = recordService.recordGetService(exampleRecord);
        if (l.size()>0) {
            return ResultUtil.success(l);//强大的result类可以自定义返回类型
        }
        else {
            return ResultUtil.error("缺少查询条件或查询结果为空");
        }
    }
















 */











