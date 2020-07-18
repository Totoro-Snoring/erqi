package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.LoginLog;
import com.aaa.cloud.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zcl
 * @ClassName: LoginLogController
 * @Description:
 *         新增日志
 * @Date: 2020/7/15 17:52
**/
@RestController
public class LoginLogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;

    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }


    /**
     * @Author: zcl
     * @Methodname: addLoginLog
     * @Description:
     *         保存日志
     * @Date: 2020/7/15 17:57
     * @Param: [loginLog]
     * @Return: java.lang.Integer
     **/
    @RequestMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog){
        return loginLogService.add(loginLog);
    }
}
