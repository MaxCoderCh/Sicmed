package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;
import com.sicmed.assessmen.mapper.BaseReadMapper;

public interface PatientNihCpsiScoreReadMapper extends BaseReadMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}