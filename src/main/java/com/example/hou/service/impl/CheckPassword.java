package com.example.hou.service.impl;


public class CheckPassword {
    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //小写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //大写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    public static boolean checkPasswordRule(String password,String username){

        //密码为空及长度大于8位小于20位判断
        if (password == null || password.length() <8 || password.length()>20) return false;

        int i = 0;

        if (password.matches(CheckPassword.REG_NUMBER)) i++;
        if (password.matches(CheckPassword.REG_LOWERCASE))i++;
        if (password.matches(CheckPassword.REG_UPPERCASE)) i++;
        if (password.matches(CheckPassword.REG_SYMBOL)) i++;

        boolean contains = password.contains(username);

        if (i  < 3 || contains)  return false;

        return true;
    }

    public static void main(String[] args) {
        boolean flag = checkPasswordRule("wenb1su4232!A", "wenbsu");
        System.out.println(flag);
    }
}

