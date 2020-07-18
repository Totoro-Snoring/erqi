package com.aaa.cloud.status;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
* @Author: zcl
* @ClassName: LoginStatus
* @Description:
 *              登录枚举类，
 *              alibaba里喊做 "魔法值"，解决项目中的 "硬编码"
* @Date: 2020/7/8 15:26
*/


public enum LoginStatus {

    LOGIN_SUCCESS("200","登录成功"),
    LOGIN_FAILED("400","登录失败，系统异常"),
    USER_EXIST("201", "用户已经存在"),
    USER_NOT_EXIST("401", "用户不存在"),
    PASSWORD_WRONG("402", "密码错误"),
    LOGOUT_WRONG("405", "用户退出异常"),
    SYSTEM_EXCEPTION("406", "系统异常");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    LoginStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }
}
