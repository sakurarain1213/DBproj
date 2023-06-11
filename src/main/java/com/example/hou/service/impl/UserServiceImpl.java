package com.example.hou.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.hou.entity.User;
import com.example.hou.mapper.UserMapper;
import com.example.hou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

import static com.example.hou.service.impl.CheckPassword.checkPasswordRule;
import static com.example.hou.service.impl.IdCardNumberUtils.*;



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
            if (userE.getPassword().equals(encrypt3ToMD5(user.getPassword()))) {
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
        //优化：本质上要后端检测密码强度 MD5加密存储（前端可不做）    本项目检测身份证并自动生成年龄性别信息
        User userE = userMapper.searchBySfz(user.getSfz());
        //debug 建表时候的问题问题很大 id就是id  而且一定需要勾选自动递增  那么身份证就完全不适合当id主键
        if (userE == null) {
            if ("".equals(user.getSfz())) {
                return "请输入身份证";
            }
            else if (!isIdCard(user.getSfz())) {
                return "身份证不合法";
            }
            else if ("".equals(user.getPassword())) {
                return "请输入密码";
            }
            else if (!checkPasswordRule(user.getPassword(), "this_proj_usermane_not_used")) {
                return "密码强度低：长度8-20 且包含大小写字母数字和符号";
            }
            else{

                /*
                 MD5加密之方法  springboot自带MD5加密  输出
                 16进制加密字符串
                 */
                //MD5加密   只能单向
                user.setPassword(encrypt3ToMD5(user.getPassword()));

                user.setAge(getAgeFromIdCard(user.getSfz()));
                user.setName(user.getName());
                int sex = Integer.parseInt(user.getSfz().substring(16, 17)) % 2;
                user.setSex(sex % 2 == 0 ? "女" : "男");

                //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~"+user.getSfz());
                    userMapper.insert(user);
                    return "SUCCESS";
                }
            }
        return "该身份证对应用户已存在";
    }

    public static String encrypt3ToMD5(String str) {
        String md5 = " ";
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }
    @Override
    public String updateService(User user) {
        //User userDB = userMapper.searchBySfz(user.getSfz());
        //一定要传入身份证作为ID
        User temp = new User();
        //修改内容  可以部分为空 可以是密码   因为没有接入公安系统 所以也可以是姓名
        temp.setPassword(encrypt3ToMD5(user.getPassword()));
        temp.setPhonenum(user.getPhonenum());
        temp.setName(user.getName());
        //temp.setAge(user.getAge()); 年龄是自动算的
        //查询  条件构造器
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("sfz", user.getSfz());



        int flag = userMapper.update(temp, userUpdateWrapper);
        if (flag == 1) {
            return "SUCCESS";
        } else {
            return "用户不存在";
        }


  }



}
