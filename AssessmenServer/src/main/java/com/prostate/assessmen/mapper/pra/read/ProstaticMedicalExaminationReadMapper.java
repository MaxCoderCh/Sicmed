package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.mapper.BaseReadMapper;

public interface ProstaticMedicalExaminationReadMapper extends BaseReadMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);
}