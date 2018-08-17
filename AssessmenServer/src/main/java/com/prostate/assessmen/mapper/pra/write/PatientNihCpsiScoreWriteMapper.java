package com.prostate.assessmen.mapper.pra.write;

import com.prostate.assessmen.beans.NihCpsiScoreBean;
import com.prostate.assessmen.entity.PatientNihCpsiScore;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface PatientNihCpsiScoreWriteMapper extends BaseWriteMapper<PatientNihCpsiScore> {

    int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore);

    PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore);

    NihCpsiScoreBean getById(String nihCpsiScoreId);
}