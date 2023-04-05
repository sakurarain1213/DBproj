package com.example.hou.result;

/**
 * @program: testhou
 * @description:
 * @author: 作者
 * @create: 2023-04-04 22:49
 */

public enum ResultCode {
    // 自定义枚举内容
    SUCCESS(200, "Success"),

    ERROR(-100, "Error");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
