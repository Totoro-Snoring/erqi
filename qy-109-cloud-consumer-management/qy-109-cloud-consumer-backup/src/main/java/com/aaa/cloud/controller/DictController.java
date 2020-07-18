package com.aaa.cloud.controller;


import com.aaa.cloud.base.BaseController;
import com.aaa.cloud.model.Dict;
import com.aaa.cloud.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zjh
 * @Date 2020/7/17
 * @Description //TODO
 */
@RestController
public class DictController extends BaseController {
    @Autowired
    IProjectService dictService;

    @PostMapping("/allDict")
    public List<Dict> selectList(Dict dict){
        return dictService.selectList(dict);
    }

    /**
     * 根据主键删字典表
     * @param dict
     * @return
     */
    @DeleteMapping("/deleteDict")
    public Integer delete(Dict dict){
        return dictService.delete(dict);
    }
    /**
     * 新增字典表
     * @param dict
     * @return
     */
    @PostMapping("/insertDict")
    public Integer add(Dict dict){
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
}
