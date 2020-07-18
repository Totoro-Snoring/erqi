package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.MappingProject;
import com.aaa.cloud.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: MappingProjectController
 * @Description: provider  MappingProjectController
 * @Date: 2020/7/16 13:40
 **/
@RestController
public class MappingProjectController extends CommonController<MappingProject> {
    @Autowired
    private MappingProjectService mappingProjectService;

    @Override
    public BaseService<MappingProject> getBaseService() {
        return mappingProjectService;
    }

    /**
     * @Author: zcl
     * @Methodname: mapingProjectPageInfo
     * @Description: 分页查询数据  测绘管理---项目审核---项目信息，返回list
     * @Date: 2020/7/16 9:33
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/selectMappingProjectAll")
    public ResultData selectListByPage(@RequestBody MappingProject mappingProject,
                                       @RequestParam("pageNo") Integer pageNo,
                                       @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> mappingProjectPageInfo = mappingProjectService.selectListByPage(mappingProject, pageNo, pageSize);
        if (null == mappingProjectPageInfo && "".equals(mappingProjectPageInfo)) {
            return super.operationFailed();
        }
        return super.operationSuccess();

    }

    /**
     * @Author: zcl
     * @Methodname: mapingProjectPageInfo
     * @Description: 添加测绘管理---项目审核---项目信息
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/addMappingProject")
    public ResultData addMappingProject(@RequestBody MappingProject mappingProject) {
        Integer integer = mappingProjectService.addMappingProject(mappingProject);

        if (integer>0){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author: zcl
     * @Methodname: deleteMappingProjectById
     * @Description:
     *        删除测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 18:38
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @DeleteMapping("/deleteMappingProjectByPrimaryKey")
   public ResultData deleteMappingProjectById(@RequestBody MappingProject mappingProject){
        Integer delinteger = mappingProjectService.deleteMappingProjectById(mappingProject);
        if (delinteger>0){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author: zcl
     * @Methodname: deleteMappingProjectById
     * @Description:
     *        修改测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 18:38
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PutMapping("/updateMappingProject")
    public ResultData updateMappingProject(@RequestBody MappingProject mappingProject){

        Integer updinteger = mappingProjectService.updateMappingProject(mappingProject);
        if (updinteger>0){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         TODO 迷糊查询 应该有错
     * @Date: 2020/7/16 19:07
     * @Param: [projectName, projectType, projectLeader, startDate]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @PostMapping("/fuzzyMappingProject")
    public ResultData fuzzyMappingProject( @RequestParam("projectName") String projectName,
                                           @RequestParam("projectType") String projectType,
                                           @RequestParam("projectLeader") String projectLeader,
                                           @RequestParam("startDate") String startDate
                                           ){
        List<MappingProject> fuzzyList = mappingProjectService.fuzzyMappingProject(projectName, projectType, projectLeader, startDate);
        if (null == fuzzyList && "".equals(fuzzyList)) {
            return super.operationFailed();
        }
        return super.operationSuccess();
    }
}



