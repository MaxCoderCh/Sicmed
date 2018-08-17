package com.prostate.assessmen.mapper.pra.write;

import com.prostate.assessmen.beans.IpssScoreBean;
import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface PatientIpssScoreWriteMapper extends BaseWriteMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}