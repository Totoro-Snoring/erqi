package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.MappingUnit;
import com.aaa.cloud.service.MappingUnitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.mapper.controller
 * @ClassName: MappingUnitController
 * @Author: sun
 * @Description:
 * @Date: 2020/7/16 19:23
 * @Version: 1.0
 */
@RestController
public class MappingUnitController extends CommonController<MappingUnit> {

    @Autowired
    private MappingUnitService mappingUnitService;
    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }

    /**
     * @Description
     *      查询所有 分页查询
     * @Author sun
     * @Date 2020/7/16 20:15
     */
    @PostMapping("/selectListByPage")
    public ResultData selectListByPage( @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo<MappingUnit> mappingUnitPageInfo = mappingUnitService.selectListByPage(pageNo, pageSize);

        if (mappingUnitPageInfo != null && !"".equals(mappingUnitPageInfo)){
            System.out.println("拆线呢成功");
            return selectSuccess(mappingUnitPageInfo);
        }
        return selectFailed();
    }
}
