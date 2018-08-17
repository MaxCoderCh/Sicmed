package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.beans.NihCpsiScoreBean;
import com.prostate.assessmen.entity.PatientNihCpsiScore;
import com.prostate.assessmen.mapper.BaseReadMapper;

public interface PatientNihCpsiScoreReadMapper extends BaseReadMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}