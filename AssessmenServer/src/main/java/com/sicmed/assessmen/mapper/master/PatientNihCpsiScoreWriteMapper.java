package com.sicmed.assessmen.mapper.master;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;

public interface PatientNihCpsiScoreWriteMapper extends BaseWriteMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}