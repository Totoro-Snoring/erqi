package com.aaa.cloud.controller;

import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.User;
import com.aaa.cloud.service.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zcl
 * @ClassName: UserController
 * @Description:
 *         consumer
 * @Date: 2020/7/17 10:44
**/
@RestController
@Api(value = "用户" ,tags = "用户操作接口")
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author: zcl
     * @Methodname: selectAllUserListByPageInfo
     * @Description:
     *         分页查询用户
     * @Date: 2020/7/17 10:41
     * @Param: [pageNo, pageSize]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectAllUserListByPageInfo")
    ResultData selectAllUserListByPageInfo(Integer pageNo,Integer pageSize){
        return iProjectService.selectAllUserListByPageInfo(pageNo,pageSize);
    }
    /**
     * @Author: zcl
     * @Methodname: selectUserByPrimaryKey
     * @Description:
     *         根据主键查询用户
     * @Date: 2020/7/17 15:22
     * @Param: [user]
     * @Return: com.aaa.cloud.model.User
     **/
    @PostMapping("/selectUserByPrimaryKey")
    public ResultData selectUserByPrimaryKey(User user){
        return iProjectService.selectUserByPrimaryKey(user);
    }

    /**
     * @Author: zcl
     * @Methodname: fuzzyUserByName
     * @Description:
     *         mohu
     * @Date: 2020/7/17 15:55
     * @Param: [userName]
     * @Return: java.util.List<com.aaa.cloud.model.User>
     **/
    @PostMapping("/fuzzyUserByName")
    public ResultData fuzzyUserByName(String userName){
        return iProjectService.fuzzyUserByName(userName);
    }

    /**
     * @Author: zcl
     * @Methodname: addUser
     * @Description:
     *         添加用户
     * @Date: 2020/7/17 11:02
     * @Param: [user]
     * @Return: java.lang.Integer
     **/
    @PostMapping("/addUser")
    public ResultData addUser(User user){
        return iProjectService.addUser(user);
    }

    /**
     * @Author: zcl
     * @Methodname: delUserByPrimaryKey
     * @Description:
     *         根绝主键列删除  主键列可能是id ,pid,uuid
     * @Date: 2020/7/17 11:23
     * @Param: [user]
     * @Return: java.lang.Integer
     **/
    @DeleteMapping("/delUserByPrimaryKey")
    public ResultData delUserByPrimaryKey( User user){
        return iProjectService.delUserByPrimaryKey(user);
    }

    /**
     * @Author: zcl
     * @Methodname: delUserByPrimaryKey
     * @Description:
     *         根据多条件删除
     * @Date: 2020/7/17 11:23
     * @Param: [user]
     * @Return: java.lang.Integer
     **/
    @DeleteMapping("/delUser")
    public ResultData delUser( User user){
        return iProjectService.delUser(user);
    }

    /**
     * @Author: zcl
     * @Methodname: updateUserByPrimaryKey
     * @Description:
     *         根据主键修改用户信息
     * @Date: 2020/7/17 14:48
     * @Param: [user]
     * @Return: java.lang.Integer
     **/
    @PutMapping("/updateUserByPrimaryKey")
    public ResultData updateUserByPrimaryKey(User user){
        return iProjectService.updateUserByPrimaryKey(user);
    }

    /**
     * @Author: zcl
     * @Methodname: updateUserByPrimaryKey
     * @Description:
     *         根据主键批量修改用户信息
     * @Date: 2020/7/17 14:48
     * @Param: [user]
     * @Return: java.lang.Integer
     **/
    @PutMapping("/batchupdateUserByPrimaryKey")
    public ResultData batchupdateUserByPrimaryKey(User user,Integer[] ids){
        return iProjectService.batchupdateUserByPrimaryKey(user, ids);
    }

}
