package com.sicmed.assessmen.mapper.pra.write;

import com.sicmed.assessmen.entity.ProstaticMedicalExamination;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface ProstaticMedicalExaminationWriteMapper extends BaseWriteMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);
}