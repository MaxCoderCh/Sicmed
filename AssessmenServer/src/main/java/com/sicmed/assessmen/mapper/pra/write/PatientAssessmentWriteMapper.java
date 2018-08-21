package com.sicmed.assessmen.mapper.pra.write;

import com.sicmed.assessmen.entity.PatientAssessment;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

import java.util.List;

public interface PatientAssessmentWriteMapper extends BaseWriteMapper<PatientAssessment> {
    PatientAssessment selectById(PatientAssessment patientAssessment);

    List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment);
}
