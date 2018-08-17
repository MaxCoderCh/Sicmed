package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.entity.PatientAssessment;
import com.prostate.assessmen.mapper.BaseReadMapper;

import java.util.List;

public interface PatientAssessmentReadMapper extends BaseReadMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);

    PatientAssessment selectLastByPatientId(PatientAssessment patientAssessment);
}
