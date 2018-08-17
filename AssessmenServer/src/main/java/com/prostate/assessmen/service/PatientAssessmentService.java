package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.PatientAssessment;

import java.util.List;

public interface PatientAssessmentService {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);

    PatientAssessment selectLastByPatientId(PatientAssessment patientAssessment);
}
