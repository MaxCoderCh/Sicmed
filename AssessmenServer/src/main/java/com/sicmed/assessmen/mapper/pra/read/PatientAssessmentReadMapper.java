package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.entity.PatientAssessment;
import com.sicmed.assessmen.mapper.BaseReadMapper;

import java.util.List;

public interface PatientAssessmentReadMapper extends BaseReadMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);

    PatientAssessment selectLastByPatientId(PatientAssessment patientAssessment);

    List<PatientAssessment> queryPageByParams(PatientAssessment patientAssessment);
}
