package com.aaa.cloud.service;

import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.*;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: IProjectService
 * @Description: api文档
 * @Date: 2020/7/15 16:01
 **/
@FeignClient(value = "system-interface")
public interface IProjectService {

    /**
     * @Author: zcl
     * @Methodname: doLogin
     * @Description:
     *         执行登录方法
     * @Date: 2020/7/15 16:04
     * @Param: [user]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /**
     * @Author: zcl
     * @ClassName: IProjectService
     * @Description:
     *          新增日志
     * @Date: 2020/7/15 17:46
    **/
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);



    /**
     * @Author: zcl
     * @Methodname: addMappingProject
     * @Description:
     *         添加测绘管理---项目审核信息
     * @Date: 2020/7/16 9:55
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/addMappingProject")
    ResultData addMappingProject(@RequestBody MappingProject mappingProject);

    /**
     * @Author: zcl
     * @Methodname: deleteMappingProject
     * @Description:
     *         根据主键/项目名称...删除项目审核信息
     * @Date: 2020/7/16 9:57
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/

    @DeleteMapping("/deleteMappingProjectByPrimaryKey")
    ResultData deleteMappingProjectById(@RequestBody MappingProject mappingProject);

    /**
     * @Author: zcl
     * @Methodname: updateMappingProject
     * @Description:
     *         更新项目审核信息
     * @Date: 2020/7/16 9:46
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    @PutMapping("/updateMappingProject")
    ResultData updateMappingProject(@RequestBody MappingProject mappingProject);

    /**
     * @Author: zcl
     * @Methodname: mapingProjectPageInfo
     * @Description:
     *       查询所有数据测绘管理---项目审核---项目信息，返回list
     * @Date: 2020/7/16 9:33
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectMappingProjectAll")
    ResultData selectMappingProjectAll();


    /**
     * @Author: zcl
     * @Methodname: selectMappingProjectOneByFiled
     * @Description:
     *         根据fileds查询一条数据 并排序
     *         TODO 项目名要做成模糊
     * @Date: 2020/7/16 10:49
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.model.MappingProject
     **/
    @PostMapping("/selectMappingProjectOneByFiled")
    ResultData selectMappingProjectOneByFiled(//@RequestBody Sqls where,
                                                  @RequestParam("orderByFiled")String orderByFiled,
                                                  @RequestParam("fileds") String... fileds);

    /**
     * @Author: zcl
     * @Methodname: maapingProjectPageInfo
     * @Description:
     *         分页查询项目审核信息
     * @Date: 2020/7/16 9:36
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectMappingProjectPageInfo")
    ResultData mappingProjectPageInfo(@RequestBody MappingProject mappingProject, @RequestParam("pageNo") Integer PageNo, @RequestParam("pageSize") Integer PageSize);

    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         模糊
     * @Date: 2020/7/16 19:09
     * @Param: [projectName, projectType, projectLeader, startDate]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/fuzzyMappingProject")
    ResultData fuzzyMappingProject( @RequestParam("projectName") String projectName,
                                           @RequestParam("projectType") String projectType,
                                           @RequestParam("projectLeader") String projectLeader,
                                           @RequestParam("startDate") String startDate
    );

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
     ResultData selectAllUserListByPageInfo( @RequestParam("pageNo") Integer pageNo,
                                             @RequestParam("pageSize") Integer pageSize);

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
     ResultData selectUserByPrimaryKey(@RequestBody User user);

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
     ResultData fuzzyUserByName(@RequestParam("userName") String userName);

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
    ResultData addUser(@RequestBody User user);

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
     ResultData delUserByPrimaryKey(@RequestBody User user);

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
     ResultData delUser(@RequestBody User user);

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
    ResultData updateUserByPrimaryKey(User user);

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
     ResultData batchupdateUserByPrimaryKey(@RequestBody User user,@RequestParam("ids") Integer[] ids);

    /**
     * @Description
     *      查询所有 分页查询
     * @Author sun
     * @Date 2020/7/16 19:45
     */
    @PostMapping("/selectListByPage")
    ResultData selectListByPage(@RequestBody MappingUnit mappingUnit, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);




    /**
     * @Description
     *     分页查询 审核信息
     * @Author sun
     * @Date 2020/7/17 11:22
     */
    @PostMapping("/selectAuditInfo")
    PageInfo selectAuditInfo(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Description
     *      分页模糊查询
     * @Author sun
     * @Date 2020/7/17 11:23
     */
    @PostMapping("/fuzzyQueryAduit")
    PageInfo fuzzyQueryAduit(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("projectName") String projectName);
    /**
     * @Description
     *     分页查询 审核信息
     * @Author sun
     * @Date 2020/7/17 11:22
     */
    @PostMapping("/selectAuditInfoZero")
    PageInfo selectAuditInfoZero(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     * @Description
     *      分页模糊查询
     * @Author sun
     * @Date 2020/7/17 11:23
     */
    @PostMapping("/fuzzyQueryAduitZero")
    PageInfo fuzzyQueryAduitZero(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam("projectName") String projectName);

    @PostMapping("/allDept")
    ResultData selectList(@RequestBody Dept dept);

    @DeleteMapping("/deleteDept")
    Integer delete(@RequestBody Dept dept);

    @PutMapping("/updateDept")
    Integer update(@RequestBody Dept dept);

    @PostMapping("/insertDept")
    Integer insert(@RequestBody Dept dept);



    @PostMapping("/allDict")
    List<Dict> selectList(Dict dict);

    /**
     * 根据主键删字典表
     * @param dict
     * @return
     */
    @DeleteMapping("/deleteDict")
    Integer delete(Dict dict);
    /**
     * 新增字典表
     * @param dict
     * @return
     */
    @PostMapping("/insertDict")
    Integer add(Dict dict);

    /**
     * 更新字典表
     * @param dict
     * @return
     */
    @PutMapping("/updateDict")
    Integer update(Dict dict);

}
