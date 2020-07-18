package com.aaa.cloud.service;

import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.mapper.UserMapper;
import com.aaa.cloud.model.MappingProject;
import com.aaa.cloud.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: UserService
 * @Description:
 *
 * @Date: 2020/7/17 8:57
**/
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;


    /**
     * @Author: zcl
     * @ClassName: UserService
     * @Description:
     *         分页查询用户
     * @Date: 2020/7/17 9:43
    **/
    public PageInfo<User> selectAllUserListByPageInfo(@RequestBody User user, Integer pageNo, Integer pageSize) {
        List<User> selectList = null;
        PageInfo<User> pageInfo = null;
        //TODO 分页业务逻辑待完善
        if ( pageNo > 0 && pageSize > 0) {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 使用自定义的sql语句，返回查询结果
            PageInfo<User> userPageInfo = super.selectListByPage(user, pageNo, pageSize);
            // 将查询的结果 进行分页
//            pageInfo = new PageInfo<User>(selectList);
            // 判断 结果是否为空
            if (null == userPageInfo && "".equals(userPageInfo)){
                // 说明结果是空，返回null
                System.out.println("暂无数据");
                return null;
            }else {
                // 返回 分页结果
                return userPageInfo;
            }
        } else {
            System.out.println("页数/条数不能为零");
            return null;
        }
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
    public User selectUserByPrimaryKey(User user){
        if (null != user && !"".equals(user)){
            User userList = userMapper.selectByPrimaryKey(user);
            return userList;
        }
        return null;
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
    public List<User> fuzzyUserByName(String userName){
        if (null != userName && !"".equals(userName)) {
            List<User> userList = userMapper.fuzzyUserByName(userName);
            return userList;
        }
        return null;
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
        public Integer addUser(User user){
            if (null != user && !"".equals(user)){
                Integer integer = userMapper.insertSelective(user);
                return integer;
            }
            return  null;
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
        public Integer delUserByPrimaryKey(User user){
            if (null != user && !"".equals(user)){
                Integer integer = userMapper.deleteByPrimaryKey(user);
                System.out.println("删除成功");
                return integer;
            }
            return  null;
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
    public Integer delUser(User user){
        if (null != user && !"".equals(user)){
            Integer integer = userMapper.delete(user);
            return integer;
        }
        return  null;
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
    public Integer updateUserByPrimaryKey(User user){
        if (null != user && !"".equals(user)){
            Integer integer = userMapper.updateByPrimaryKeySelective(user);
            return integer;
        }
        return  null;
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
    public Integer batchupdateUserByPrimaryKey(User user,Integer[] ids){
        if (null != user && !"".equals(user)){
            Integer integer = super.batchUpdate(user, ids);
            //Integer integer1 = userMapper.updateByExampleSelective(user,ids);
            return integer;
        }
        return  null;
    }

}
