package com.aaa.cloud.service;

import com.aaa.cloud.mapper.MappingProjectMapper;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.MappingProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: MappingProjectService
 * @Description:
 * @Date: 2020/7/16 13:19
 **/
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;


//    @Override
//    public BaseMapper<MappingProject> getBaseMapper() {
//        return mappingProjectMapper;
//    }

    /**
     * @Author: zcl
     * @Methodname: mapingProjectPageInfo
     * @Description: 分页查询所有数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 9:33
     * @Param: [mappingProject]
     * @Return: com.aaa.cloud.base.ResultData
     **/
    @Override
    public PageInfo<MappingProject> selectListByPage(MappingProject mappingProject, Integer pageNo, Integer pageSize) {

        List<MappingProject> select = null;
        PageInfo<MappingProject> pageInfo = null;

        if ( pageNo > 0 && pageSize > 0) {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 使用自定义的sql语句，返回查询结果
            select = mappingProjectMapper.selectAll();
            mappingProjectMapper.selectAll();
            // 将查询的结果 进行分页
            pageInfo = new PageInfo<MappingProject>(select);
            // 判断 结果是否为空
            if (null == pageInfo && "".equals(pageInfo)){
                // 说明结果是空，返回null
                return null;
            }else {
                // 返回 分页结果
                return pageInfo;
            }
        } else {
            return null;
        }
    }
    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         TODO 模糊查询
     * @Date: 2020/7/16 18:52
     * @Param: [mappingProject, pageNo, pageSize]
     * @Return: com.github.pagehelper.PageInfo<com.aaa.cloud.model.MappingProject>
     **/
    public List<MappingProject> fuzzyMappingProject(String projectName,String projectType,String projectLeader,String startDate){

        //MappingProject mappingProject = new MappingProject();
        if (projectName == null && "".equals(projectName)){
        return null;
        }
          return mappingProjectMapper.fuzzyMappingProject(projectName, projectType, projectLeader, startDate);

    }

    /**
     * @Author: zcl
     * @Methodname: addMappingProject
     * @Description:
     *         添加数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 18:13
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    public Integer addMappingProject(MappingProject mappingProject) {
        if (null != mappingProject && !"".equals(mappingProject)) {
            Integer add = mappingProjectMapper.insertSelective(mappingProject);
            return add;
        } else {
            return null;
        }
    }
    /**
     * @Author: zcl
     * @Methodname: deleteMappingProjectById
     * @Description:
     *        删除数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 18:34
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    public Integer deleteMappingProjectById(MappingProject mappingProject){
        if (null != mappingProject && !"".equals(mappingProject)) {

            Integer delinteger = mappingProjectMapper.deleteByPrimaryKey(mappingProject);

            return delinteger;
        } else {
            return null;
        }
    }

    /**
     * @Author: zcl
     * @Methodname: updateMappingProject
     * @Description:
     *         修改数据测绘管理---项目审核---项目信息
     * @Date: 2020/7/16 18:41
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     **/
    public Integer updateMappingProject(MappingProject mappingProject){
        if (null != mappingProject && !"".equals(mappingProject)) {
            Integer delinteger = mappingProjectMapper.updateByPrimaryKey(mappingProject);
            return delinteger;
        } else {
            return null;
        }
    }


}
