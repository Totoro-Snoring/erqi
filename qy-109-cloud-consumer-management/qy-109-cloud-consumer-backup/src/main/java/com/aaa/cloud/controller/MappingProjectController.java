package com.aaa.cloud.controller;

import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.MappingProject;
import com.aaa.cloud.service.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zcl
 * @ClassName: MappingProjectController
 * @Description: 项目审核
 * @Date: 2020/7/16 13:20
 **/
@RestController
@Api(value = "项目汇交", tags = "项目汇交接口")
public class MappingProjectController extends BaseController {
    @Autowired
    private IProjectService iProjectService;


    /**
     * @Author: zcl
     * @Methodname: addMappingProject
     * @Description: 添加数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 15:54
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    @PostMapping("/addMappingProject")
    public ResultData addMappingProject(MappingProject mappingProject) {
        return iProjectService.addMappingProject(mappingProject);
    }

    /**
     * @Author: zcl
     * @Methodname: deleteMappingProjectById
     * @Description: 根据主键/普通列删除数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 15:56
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    @DeleteMapping("/deleteMappingProjectById")
    public ResultData deleteMappingProjectById(MappingProject mappingProject) {
        return iProjectService.deleteMappingProjectById(mappingProject);
    }

    /**
     * @Author: zcl
     * @Methodname: updateMappingProject
     * @Description: 修改数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 15:58
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    public ResultData updateMappingProject(MappingProject mappingProject) {
        return iProjectService.updateMappingProject(mappingProject);
    }


    /**
     * @Author: zcl
     * @Methodname: mapingProjectPageInfo
     * @Description: 查询所有数据测绘管理---项目审核---项目信息，返回list
     * @Date: 2020/7/16 9:33
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectMappingProjectAll")
    public ResultData selectMappingProjectAll() {

        return iProjectService.selectMappingProjectAll();
    }


    /**
     * @Author: zcl
     * @Methodname: mappingProjectPageInfo
     * @Description: 分页查询所有数据测绘管理---项目审核---项目信息，返回list
     * @Date: 2020/7/16 16:02
     * @Param: [mappingProject, PageNo, PageSize]
     * @Return: com.github.pagehelper.PageInfo<com.aaa.cloud.model.MappingProject>
     **/
    @PostMapping("/selectMappingProjectPageInfo")
    public ResultData mappingProjectPageInfo(MappingProject mappingProject, Integer PageNo, Integer PageSize) {
        return iProjectService.mappingProjectPageInfo(mappingProject, PageNo, PageSize);
    }


    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         模糊
     * @Date: 2020/7/16 19:11
     * @Param: [projectName, projectType, projectLeader, startDate]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/fuzzyMappingProject")
    public ResultData fuzzyMappingProject(String projectName, String projectType, String projectLeader, String startDate
    ) {
        return iProjectService.fuzzyMappingProject(projectName, projectType, projectLeader, startDate);
    }
}
