package com.aaa.cloud.controller;


import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.MappingUnit;
import com.aaa.cloud.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.mapper.controller
 * @ClassName: MappingUnitController
 * @Author: sun
 * @Description:
 * @Date: 2020/7/16 19:47
 * @Version: 1.0
 */
@RestController
public class MappingUnitController extends BaseController {

    @Autowired
    private IProjectService iProjectService;
    /**
     * @Description
     *      分页查询所有
     * @Author sun
     * @Date 2020/7/16 20:23
     */
    @PostMapping("/selectListByPage")
    public ResultData selectListByPage(MappingUnit mappingUnit, Integer pageNo, Integer pageSize){
        return iProjectService.selectListByPage(mappingUnit,pageNo,pageSize);
    }

}
