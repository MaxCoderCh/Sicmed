package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.entity.ProstaticMedicalExamination;
import com.sicmed.assessmen.mapper.BaseReadMapper;

public interface ProstaticMedicalExaminationReadMapper extends BaseReadMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);
}