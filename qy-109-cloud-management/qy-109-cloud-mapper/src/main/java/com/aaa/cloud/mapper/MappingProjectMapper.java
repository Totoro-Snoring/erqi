package com.aaa.cloud.mapper;

import com.aaa.cloud.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: zcl
 * @ClassName: MappingProjectMapper
 * @Description:
 *         自己的方法
 * @Date: 2020/7/16 11:11
**/

public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
     * @Author: zcl
     * @Methodname: fuzzyMappingProject
     * @Description:
     *         动态sql  进行模糊查询
     * @Date: 2020/7/16 11:16
     * @Param: [projectName, projectType, projectLeater, starDate]
     * @Return: java.util.List<com.aaa.cloud.model.MappingProject>
     **/

    List<MappingProject> fuzzyMappingProject(@Param("projectName")String projectName,
                                             @Param("projectType")String projectType,
                                             @Param("projectLeader")String projectLeater,
                                             @Param("starDate")String starDate
                                             );

}