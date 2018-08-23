package com.sicmed.assessmen.service.impl;

import com.sicmed.assessmen.entity.PatientAssessment;
import com.sicmed.assessmen.mapper.pra.write.PatientAssessmentWriteMapper;
import com.sicmed.assessmen.mapper.pra.read.PatientAssessmentReadMapper;
import com.sicmed.assessmen.service.PatientAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAssessmentServiceImpl implements PatientAssessmentService {

    @Autowired
    private PatientAssessmentWriteMapper assessmentWriteMapper;

    @Autowired
    private PatientAssessmentReadMapper assessmentReadMapper;

    @Override
    public PatientAssessment selectById(PatientAssessment patientAssessment) {
        return assessmentWriteMapper.selectById(patientAssessment);
    }

    @Override
    public List<PatientAssessment> selectByPatientId(PatientAssessment patientAssessment) {
        return assessmentReadMapper.selectByPatientId(patientAssessment);
    }

    @Override
    public PatientAssessment selectLastByPatientId(PatientAssessment patientAssessment) {
        return assessmentReadMapper.selectLastByPatientId(patientAssessment);
    }

    @Override
    public List<PatientAssessment> queryPageByParams(PatientAssessment patientAssessment) {
        return assessmentReadMapper.queryPageByParams(patientAssessment);
    }
}
