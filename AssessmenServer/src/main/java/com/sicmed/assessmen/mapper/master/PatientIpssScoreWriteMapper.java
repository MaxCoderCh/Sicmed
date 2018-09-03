package com.sicmed.assessmen.mapper.master;

import com.sicmed.assessmen.beans.IpssScoreBean;
import com.sicmed.assessmen.entity.PatientIpssScore;

public interface PatientIpssScoreWriteMapper extends BaseWriteMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}