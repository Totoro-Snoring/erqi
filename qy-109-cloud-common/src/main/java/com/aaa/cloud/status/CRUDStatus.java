package com.aaa.cloud.status;

/**
* @Author: zcl
* @ClassName: LoginStatus
* @Description:
 *              登录枚举类，
 *              alibaba里喊做 "魔法值"，解决项目中的 "硬编码"
* @Date: 2020/7/8 15:26
*/


public enum CRUDStatus {

    ADD_SUCCESS("200","添加成功"),
    ADD_FAILED("400","添加失败"),
    DELETE_SUCCESS("201","删除成功"),
    DELETE_FAILED("401","删除失败"),
    UPDATE_SUCCESS("203","修改成功"),
    UPDATE_FAILED("402","修改失败"),
    SELECT_SUCCESS("204","查询成功"),
    SELECT_FAILED("405","暂无数据"),
    SUCCESS("1", "操作成功"),
    FAILED("2", "操作失败");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CRUDStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }
}
