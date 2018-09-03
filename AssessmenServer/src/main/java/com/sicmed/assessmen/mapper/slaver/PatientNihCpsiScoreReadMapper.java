package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;

public interface PatientNihCpsiScoreReadMapper extends BaseReadMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}