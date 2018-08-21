package com.sicmed.assessmen.mapper.pra.write;

import com.sicmed.assessmen.beans.IpssScoreBean;
import com.sicmed.assessmen.entity.PatientIpssScore;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface PatientIpssScoreWriteMapper extends BaseWriteMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}