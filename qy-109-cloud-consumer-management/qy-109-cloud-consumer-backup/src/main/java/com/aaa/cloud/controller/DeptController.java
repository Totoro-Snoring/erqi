package com.aaa.cloud.controller;

import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.base.ResultData;
import com.aaa.cloud.model.Dept;
import com.aaa.cloud.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zjh
 * @Date 2020/7/17
 * @Description //TODO
 */
@RestController
public class DeptController extends BaseController {
    @Autowired
    private IProjectService deptService;

    @PostMapping("/allDept")
    public ResultData selectList(@RequestBody Dept dept) {
        return deptService.selectList(dept);
    }

    @DeleteMapping("/deleteDept")
    Integer delete(@RequestBody Dept dept) {
        return deptService.delete(dept);
    }

    @PutMapping("/updateDept")
    Integer update(@RequestBody Dept dept) {
        return deptService.update(dept);
    }

    @PostMapping("/insertDept")
    Integer insert(@RequestBody Dept dept) {

        return deptService.insert(dept);
    }
}
