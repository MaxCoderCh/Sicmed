package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.beans.MedicalExaminationBean;
import com.sicmed.assessmen.entity.ProstaticMedicalExamination;

import java.util.List;

public interface ProstaticMedicalExaminationReadMapper extends BaseReadMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);

    List<MedicalExaminationBean> queryPageByParams(ProstaticMedicalExamination prostaticMedicalExamination);
}