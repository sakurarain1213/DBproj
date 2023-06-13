package com.example.hou.service.impl;

import java.util.Random;

/**
随机生成数字类  用于订票表  或者可以不用  保证首位不为0
 */
public class JavaRandom {

    public static String randomNum(){
        Random random=new Random();
        String str="";
        for (int i = 0; i <12; i++) {
            if(i==0){
                //首位不能为0且数字取值区间为 [1,9]
                str+=(random.nextInt(9)+1);
            }else{
                //其余位的数字的取值区间为 [0,9]
                str+=random.nextInt(10);
            }
        }
        return str;

    }
    /*
    public static void main(String[] args) {
        Random random=new Random();
        String str="";
        for (int i = 0; i <12; i++) {
            if(i==0){
                //首位不能为0且数字取值区间为 [1,9]
                str+=(random.nextInt(9)+1);
            }else{
                //其余位的数字的取值区间为 [0,9]
                str+=random.nextInt(10);
            }
        }
        System.out.println(str);
    }*/

}
