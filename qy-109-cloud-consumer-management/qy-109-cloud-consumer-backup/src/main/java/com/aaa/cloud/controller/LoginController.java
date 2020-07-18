package com.aaa.cloud.controller;

import com.aaa.cloud.annotation.LoginAnnotation;
import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.User;
import com.aaa.cloud.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zcl
 * @ClassName: LoginController
 * @Description:
 *         登录
 * @Date: 2020/7/15 15:56
**/
@RestController
public class LoginController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author: zcl
     * @ClassName: LoginController
     * @Description:
     *         登陆操作
     * @Date: 2020/7/15 15:59
    **/
    @PostMapping("/doLogin")
    @LoginAnnotation(opeationType = "登录操作",opeationName = "管理员登录")
    public ResultData doLogin(User user){
        return iProjectService.doLogin(user);
    }
}
