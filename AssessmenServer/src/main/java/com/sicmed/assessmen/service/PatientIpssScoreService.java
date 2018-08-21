package com.sicmed.assessmen.service;

import com.sicmed.assessmen.beans.IpssScoreBean;
import com.sicmed.assessmen.entity.PatientIpssScore;

public interface PatientIpssScoreService extends BaseService<PatientIpssScore> {
    int insertSelectiveById(PatientIpssScore patientIpssScore);

    /**
     * 根据 创建时间 和 患者ID 查询 评估结果
     * @use 1.校验单日评估数量
     * @param patientIpssScore
     * @return
     */
    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}
