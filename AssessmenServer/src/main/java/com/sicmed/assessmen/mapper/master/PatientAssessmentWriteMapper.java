package com.sicmed.assessmen.mapper.master;

import com.sicmed.assessmen.entity.PatientAssessment;

import java.util.List;

public interface PatientAssessmentWriteMapper extends BaseWriteMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);
}
