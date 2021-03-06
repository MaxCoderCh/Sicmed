package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.beans.IpssScoreBean;
import com.sicmed.assessmen.entity.PatientIpssScore;

public interface PatientIpssScoreReadMapper extends BaseReadMapper<PatientIpssScore> {

    int insertSelectiveById(PatientIpssScore patientIpssScore);

    PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore);

    IpssScoreBean getById(String ipssScoreId);
}