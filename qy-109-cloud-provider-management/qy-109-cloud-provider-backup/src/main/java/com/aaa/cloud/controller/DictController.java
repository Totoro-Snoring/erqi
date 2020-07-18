package com.aaa.cloud.controller;

import com.aaa.cloud.base.CommonController;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.Dict;
import com.aaa.cloud.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zjh
 * @Date 2020/7/17
 * @Description //TODO
 */
@RestController
public class DictController extends CommonController<Dict> {


    @Autowired
    private DictService dictService;

    @Override
    public BaseService<Dict> getBaseService() {
        return dictService;
    }


    /**
     * 查询所有字典
     * @param dict
     * @return
     */
    @PostMapping("/allDict")
    public List<Dict> selectList(Dict dict){
        return dictService.select(dict);
    }

    /**
     * 根据主键删字典表
     * @param dict
     * @return
     */
    @DeleteMapping("/deleteDict")
    public Integer delete(Dict dict){
        return dictService.deleteByPrimaryKey(dict);
    }

    /**
     * 新增字典表
     * @param dict
     * @return
     */
    @PostMapping("/insertDict")
    public Integer add(@RequestBody Dict dict){
        return dictService.add(dict);
    }

    /**
     * 更新字典表
     * @param dict
     * @return
     */
    @PutMapping("/updateDict")
    public Integer update(Dict dict){
        return dictService.update(dict);
    }

    /**
     * 根据主键批量删除
     * @param ids
     * @return
     */

    @DeleteMapping("/deleteDictByIds")
    public Integer deleteDictByIds(@RequestParam("dictIds") List<Integer> dictIds){
        return dictService.deleteByIds(dictIds);
    }
}
