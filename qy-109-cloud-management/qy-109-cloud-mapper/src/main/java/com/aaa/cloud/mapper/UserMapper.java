package com.aaa.cloud.mapper;

import com.aaa.cloud.model.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {
    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         根据用户名查询
     * @Date: 2020/7/17 15:43
     * @Param: [userName]
     * @Return: java.util.List<com.aaa.cloud.model.User>
     **/
    List<User> fuzzyUserByName(@Param("username")String userName);
}