package com.aaa.cloud.mapper;

import com.aaa.cloud.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {
    List selectAuditInfo();

    List fuzzyQueryAduit(String projectName);

    List selectAuditInfoZero();

    List fuzzyQueryAduitZero(String projectName);
}