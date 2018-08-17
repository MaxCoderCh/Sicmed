package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.beans.IpssScoreBean;
import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.mapper.BaseReadMapper;

public interface PatientIpssScoreReadMapper extends BaseReadMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}