package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.User;
import com.aaa.cloud.service.LoginService;
import com.aaa.cloud.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.cloud.status.LoginStatus.*;

/**
 * @Author: zcl
 * @ClassName: LoginController
 * @Description:
 *              登录操作
 * @Date: 2020/7/15 16:28
 **/
@RestController
public class LoginController extends CommonController<User> {
    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }

    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user) {
        
        TokenVo tokenVo = loginService.doLogin(user);
        if (tokenVo.getIfSucess()){
            return super.loginSuccess(tokenVo.getToken());
        }else if (tokenVo.getType() == 1){
            return super.loginFailed(USER_NOT_EXIST.getMsg());
        }else if (tokenVo.getType() == 2){
            return super.loginFailed(PASSWORD_WRONG.getMsg());
        }else {
            return super.loginFailed(SYSTEM_EXCEPTION.getMsg());
        }
    }
}
