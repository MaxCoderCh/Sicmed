package com.prostate.assessmen.mapper.pra.write;

import com.prostate.assessmen.entity.PatientAssessment;
import com.prostate.assessmen.mapper.BaseWriteMapper;

import java.util.List;

public interface PatientAssessmentWriteMapper extends BaseWriteMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);
}
