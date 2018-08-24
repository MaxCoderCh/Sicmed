package com.sicmed.assessmen.service;

import com.sicmed.assessmen.beans.MedicalExaminationBean;
import com.sicmed.assessmen.entity.ProstaticMedicalExamination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProstaticMedicalExaminationService extends BaseService<ProstaticMedicalExamination> {

    /**
     * 根据 创建时间 和 患者ID 查询 评估结果
     *
     * @param prostaticMedicalExamination
     * @return
     * @use 1.校验单日评估数量
     */
    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    /**
     * 根据患者和日期 查询一条 化验单解读记录
     * @param prostaticMedicalExamination
     * @return
     */
    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);

    List<MedicalExaminationBean> queryPageByParams(ProstaticMedicalExamination prostaticMedicalExamination);
}
