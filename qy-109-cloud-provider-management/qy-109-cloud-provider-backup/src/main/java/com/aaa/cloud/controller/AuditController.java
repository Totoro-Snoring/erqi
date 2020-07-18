package com.aaa.cloud.controller;

import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.Audit;
import com.aaa.cloud.service.AuditService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.controller
 * @ClassName: AuditController
 * @Author: sun
 * @Description:
 * @Date: 2020/7/17 9:09
 * @Version: 1.0
 */
@RestController
public class AuditController extends CommonController<Audit> {

    @Autowired
    private AuditService auditService;

    @Override
    public BaseService<Audit> getBaseService() {
        return auditService;
    }

    /**
     * @Description
     *      分页查询 已审核项目信息
     * @Author sun
     * @Date 2020/7/17 10:27
     */
    @PostMapping("/selectAuditInfo")
    public PageInfo selectAuditInfo(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        List list = auditService.selectAuditInfo();
        if (list.size()>0){
            PageHelper.startPage(pageNo,pageSize);
            return new PageInfo(list);
        }
        return new PageInfo();
    }
    /**
     * @Description
     *      模糊分页查询  已审核
     * @Author sun
     * @Date 2020/7/17 14:51
     */
    @PostMapping("/fuzzyQueryAduit")
   public PageInfo fuzzyQueryAduit(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,
                             @RequestParam("projectName") String projectName){
        List list = auditService.fuzzyQueryAduit(projectName);
        if (list.size()> 0){
            PageHelper.startPage(pageNo,pageSize);
            return new PageInfo(list);
        }
        return new PageInfo();
    }
    /**
     * @Description
     *      分页查询 未审核项目信息
     * @Author sun
     * @Date 2020/7/17 10:27
     */
    @PostMapping("/selectAuditInfoZero")
    public PageInfo selectAuditInfoZero(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        List list = auditService.selectAuditInfo();
        if (null != list && !"".equals(list)){
            PageHelper.startPage(pageNo,pageSize);
            return new PageInfo(list);
        }
        return new PageInfo();
    }
    /**
     * @Description
     *      模糊分页查询  未审核
     * @Author sun
     * @Date 2020/7/17 14:51
     */
    @PostMapping("/fuzzyQueryAduitZero")
    public PageInfo fuzzyQueryAduitZero(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,
                             @RequestParam("projectName") String projectName){
        List list = auditService.fuzzyQueryAduit(projectName);
        if (null != list && !"".equals(list)){
            PageHelper.startPage(pageNo,pageSize);
            return new PageInfo(list);
        }
        return new PageInfo();
    }


}
