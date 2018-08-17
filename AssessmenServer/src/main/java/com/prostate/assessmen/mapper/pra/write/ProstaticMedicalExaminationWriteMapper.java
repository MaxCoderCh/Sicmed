package com.prostate.assessmen.mapper.pra.write;

import com.prostate.assessmen.entity.ProstaticMedicalExamination;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface ProstaticMedicalExaminationWriteMapper extends BaseWriteMapper<ProstaticMedicalExamination> {

    ProstaticMedicalExamination selectByCreateTimeAndPatientId(ProstaticMedicalExamination prostaticMedicalExamination);

    ProstaticMedicalExamination selectByPatientAndData(ProstaticMedicalExamination prostaticMedicalExamination);
}