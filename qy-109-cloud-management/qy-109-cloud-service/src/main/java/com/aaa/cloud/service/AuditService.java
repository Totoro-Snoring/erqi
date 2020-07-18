package com.aaa.cloud.service;

import com.aaa.cloud.base.BaseService;
import com.aaa.cloud.mapper.AuditMapper;
import com.aaa.cloud.model.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: qy-109
 * @Package: com.aaa.sun.mapper.service
 * @ClassName: AuditService
 * @Author: sun
 * @Description:
 * @Date: 2020/7/17 8:43
 * @Version: 1.0
 */
@Service
public class AuditService extends BaseService<Audit> {
    @Autowired
    private AuditMapper auditMapper;
    /**
     * @Description
     *      连表查询数据 已审核
     * @Author sun
     * @Date 2020/7/17 10:10
     */
    public List selectAuditInfo() {
        List list = auditMapper.selectAuditInfo();
        if (list.size()>0){
            return list;
        }
        return null;
    }

    /**
     * @Description
     *      模糊查询 已审核
     * @Author sun
     * @Date 2020/7/17 10:10
     */
    public List fuzzyQueryAduit(String projectName) {
        List list = auditMapper.fuzzyQueryAduit(projectName);
        if (list.size()>0){
            return list;
        }
        return null;
    }
    /**
     * @Description
     *      连表查询数据 未审核
     * @Author sun
     * @Date 2020/7/17 10:10
     */
    public List selectAuditInfoZero() {
        List list = auditMapper.selectAuditInfoZero();
        if (list.size()>0){
            return list;
        }
        return null;
    }

    /**
     * @Description
     *      模糊查询  未审核
     * @Author sun
     * @Date 2020/7/17 10:10
     */
    public List fuzzyQueryAduitZero(String projectName) {
        List list = auditMapper.fuzzyQueryAduitZero(projectName);
        if (list.size()>0){
            return list;
        }
        return null;
    }
}
