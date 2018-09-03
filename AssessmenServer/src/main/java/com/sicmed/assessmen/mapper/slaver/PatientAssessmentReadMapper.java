package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.entity.PatientAssessment;

import java.util.List;

public interface PatientAssessmentReadMapper extends BaseReadMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);

    PatientAssessment selectLastByPatientId(PatientAssessment patientAssessment);

    List<PatientAssessment> queryPageByParams(PatientAssessment patientAssessment);
}
