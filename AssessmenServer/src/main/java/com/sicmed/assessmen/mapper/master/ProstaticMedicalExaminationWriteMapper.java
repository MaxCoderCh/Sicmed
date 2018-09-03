package com.sicmed.assessmen.mapper.master;

import com.sicmed.assessmen.entity.ProstaticMedicalExamination;

public interface ProstaticMedicalExaminationWriteMapper extends BaseWriteMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);
}