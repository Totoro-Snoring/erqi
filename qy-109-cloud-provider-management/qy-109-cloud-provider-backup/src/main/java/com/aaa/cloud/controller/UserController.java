package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.User;
import com.aaa.cloud.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: UserController
 * @Description:
 *         provider
 * @Date: 2020/7/17 10:35
**/
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;

    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }

    /**
     * @Author: zcl
     * @Methodname: selectAllUserListByPageInfo
     * @Description:
     *         查询用户信息
     * @Date: 2020/7/17 10:39
     * @Param: [pageNo, pageSize]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectAllUserListByPageInfo")
    public ResultData selectAllUserListByPageInfo(
                                                    @RequestBody User user,
                                                    @RequestParam("pageNo") Integer pageNo,
                                                   @RequestParam("pageSize") Integer pageSize){

        PageInfo<User> userPageInfo = userService.selectAllUserListByPageInfo(user,pageNo, pageSize);
        if (null != userPageInfo && !"".equals(userPageInfo)) {
            return selectSuccess(userPageInfo);
        }
        return selectFailed();
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
    public ResultData selectUserByPrimaryKey(@RequestBody User user){
        User userdata = userService.selectUserByPrimaryKey(user);
        if (null != userdata && !"".equals(userdata)) {
            return selectSuccess(userdata);
        }
        return selectFailed();
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
    public ResultData fuzzyUserByName(@RequestParam("userName") String userName){
        List<User> userList = userService.fuzzyUserByName(userName);
        if (null != userList && !"".equals(userList)) {
            return selectSuccess(userList);
        }
        return selectFailed();
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
    public ResultData addUser(@RequestBody User user){
        Integer integer = userService.addUser(user);
        if (integer>0){
            return addSuccess(integer);
        }
        return addFailed();
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
    public ResultData delUserByPrimaryKey(@RequestBody User user){
        Integer delInteger = userService.delUserByPrimaryKey(user);
        if (delInteger>0){
            return delSuccess(delInteger);
        }
        return delFailed();
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
    public ResultData delUser(@RequestBody User user){
        Integer delInteger = userService.delUser(user);
        if (delInteger>0){
            return delSuccess(delInteger);
        }
        return delFailed();
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
        Integer updInteger = userService.updateUserByPrimaryKey(user);
        if (updInteger>0){
            return updateSuccess(updInteger);
        }
        return updateFailed();
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
    public ResultData batchupdateUserByPrimaryKey(@RequestBody User user,@RequestParam("ids") Integer[] ids){
        Integer updInteger = userService.batchupdateUserByPrimaryKey(user, ids);
        if (updInteger>0){
            return updateSuccess(updInteger);
        }
        return updateFailed();
    }


}
