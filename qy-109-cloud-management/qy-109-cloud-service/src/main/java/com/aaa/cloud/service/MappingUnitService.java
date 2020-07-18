package com.aaa.cloud.service;

import com.aaa.cloud.mapper.MappingUnitMapper;
import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.model.MappingUnit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.mapper.service
 * @ClassName: MappingUnitService
 * @Author: sun
 * @Description:
 * @Date: 2020/7/16 11:06
 * @Version: 1.0
 */
@Service
public class MappingUnitService extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;


    /**
     * @Description
     *      分页查询所有
     * @Author sun
     * @Date 2020/7/16 20:19
     */

    public PageInfo<MappingUnit> selectListByPage(Integer pageNo, Integer pageSize){
        List<MappingUnit> select = null;
        PageInfo<MappingUnit> pageInfo = null;
        PageHelper.startPage(pageNo, pageSize);
        // 使用自定义的sql语句，返回查询结果
        select = mappingUnitMapper.selectAll();
        // 将查询的结果 进行分页
        pageInfo = new PageInfo<MappingUnit>(select);

        if ("".equals(pageInfo) || pageInfo == null){
            return null;
        }
        return pageInfo;

    }
    /**
     * @Description
     *      修改
     * @Author sun
     * @Date 2020/7/16 20:20
     */

//    public Integer update1(MappingUnit mappingUnit){
//        if (mappingUnit == null && "".equals(mappingUnit)){
//            return null;
//        }
//        Integer update = super.update(mappingUnit);
//        if (update > 0){
//            return update;
//        }
//        return null;
//    }
//    /**
//     * @Description
//     *  删除
//     * @Author sun
//     * @Date 2020/7/16 20:20
//     */
//    public Integer delete(MappingUnit mappingUnit){
//        if (mappingUnit == null && "".equals(mappingUnit)){
//            return null;
//        }
//        Integer delete = super.update(mappingUnit);
//        if (delete > 0){
//            return delete;
//        }
//        return null;
//    }
//    /**
//     * @Description
//     *          添加
//     * @Author sun
//     * @Date 2020/7/16 20:20
//     */
//    public Integer add(MappingUnit mappingUnit){
//        if (mappingUnit == null && "".equals(mappingUnit)){
//            return null;
//        }
//        Integer add = super.update(mappingUnit);
//        if (add > 0){
//            return add;
//        }
//        return null;
//    }

}
