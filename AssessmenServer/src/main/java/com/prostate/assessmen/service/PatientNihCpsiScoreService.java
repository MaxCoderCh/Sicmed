package com.prostate.assessmen.service;

import com.prostate.assessmen.beans.NihCpsiScoreBean;
import com.prostate.assessmen.entity.PatientNihCpsiScore;

public interface PatientNihCpsiScoreService extends BaseService<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    /**
     * 根据 创建时间 和 患者ID 查询 评估结果
     * @use 1.校验单日评估数量
     * @param patientNihNpsiScore
     * @return
     */
    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    /**
     * 根据ID查询 评估结果
     * @param nihCpsiScoreId
     * @return
     */
    NihCpsiScoreBean getById(String nihCpsiScoreId);
}
