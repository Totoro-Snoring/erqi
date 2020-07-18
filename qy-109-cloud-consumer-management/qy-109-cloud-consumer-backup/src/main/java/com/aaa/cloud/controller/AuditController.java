package com.aaa.cloud.controller;

import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.service.IProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.controller
 * @ClassName: AuditController
 * @Author: sun
 * @Description:
 * @Date: 2020/7/17 11:25
 * @Version: 1.0
 */
@RestController
public class AuditController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @Description
     *      分页查询 已审核信息
     * @Author sun
     * @Date 2020/7/17 14:41
     */
    @PostMapping("/selectAuditInfo")
    public PageInfo selectAuditInfo(Integer pageNo, Integer pageSize) {
        return iProjectService.selectAuditInfo(pageNo, pageSize);
    }
    /**
     * @Description
     *      模糊查询 分页查询 已审核信息
     * @Author sun
     * @Date 2020/7/17 14:43
     */
    @PostMapping("/fuzzyQueryAduit")
    public PageInfo fuzzyQueryAduit(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("projectName") String projectName){
        return iProjectService.fuzzyQueryAduit(pageNo,pageSize,projectName);
    }
    @PostMapping("/selectAuditInfoZero")
    public PageInfo selectAuditInfoZero(Integer pageNo, Integer pageSize) {
        return iProjectService.selectAuditInfo(pageNo, pageSize);
    }
    /**
     * @Description
     *      模糊查询 分页查询 已审核信息
     * @Author sun
     * @Date 2020/7/17 14:43
     */
    @PostMapping("/fuzzyQueryAduitZero")
    public PageInfo fuzzyQueryAduitZero(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam("projectName") String projectName){
        return iProjectService.fuzzyQueryAduit(pageNo,pageSize,projectName);
    }
}
