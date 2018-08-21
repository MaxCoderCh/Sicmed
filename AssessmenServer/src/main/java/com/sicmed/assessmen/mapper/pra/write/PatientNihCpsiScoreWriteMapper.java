package com.sicmed.assessmen.mapper.pra.write;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface PatientNihCpsiScoreWriteMapper extends BaseWriteMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}