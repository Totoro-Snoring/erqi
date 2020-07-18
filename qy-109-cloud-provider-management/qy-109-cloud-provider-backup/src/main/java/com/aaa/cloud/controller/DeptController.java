package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.Dept;
import com.aaa.cloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zjh
 * @Date 2020/7/17
 * @Description //TODO
 */
@RestController
public class DeptController extends CommonController<Dept> {


    @Autowired
    private DeptService deptService;

   @Override
    public BaseService<Dept> getBaseService() {
        return deptService;
    }

    /**
     * 查询所有部门
     * @param dept
     * @return
     */
    @PostMapping("/allDept")
    public List<Dept> selectList(Dept dept){
        return deptService.select(dept);
    }

    /**
     *删除部门
     * @param dept
     * @return
     */
    @DeleteMapping("/deleteDept")
    public Integer delete( Dept dept) {
        return deptService.deleteByPrimaryKey(dept);
    }

    /**
     * 条件查询
     * @param dept
     * @return
     */

//    @PostMapping("/selectListByFiled")
//    public List<Dept> selectListByFiled(@RequestBody Dept dept, @RequestParam("deptName") String deptName){
//        return deptService.selectListByFiled( "","deptName");
//    }
    /**
     *
     * 根据主键更新部门
     * @param dept
     * @return
     */
    @PutMapping("/updateDept")
    public Integer update(@RequestBody Dept dept) {
        return deptService.update(dept);
    }

    /**
     * 添加部门信息
     * @param dept
     * @return
     */
    @PostMapping("/insertDept")
   public Integer insert(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    /**
     * 根據主鍵批量刪除
     * @param ids
     * @return
     */

    @DeleteMapping("/deleteDeptByIds")
   public Integer deleteByIds( List<Integer> ids){
        return deptService.deleteByIds(ids);
    }


    /**
     * 查询一个
     *
     * @param deptId
     * @return
     */
    @PostMapping("/selectDeptById")

    public Dept selectOne(@RequestParam("deptId") Dept deptId){
        return deptService.selectOne(deptId);
    }

}
