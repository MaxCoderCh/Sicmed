package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.beans.IpssScoreBean;
import com.sicmed.assessmen.entity.PatientIpssScore;
import com.sicmed.assessmen.mapper.BaseReadMapper;

public interface PatientIpssScoreReadMapper extends BaseReadMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}