package com.prostate.record.mapper.master;

import com.prostate.record.entity.UserPatient;

public interface UserPatientWriteMapper extends BaseWriteMapper<UserPatient>{

    int removeByParams(UserPatient userPatient);

    int updateByParams(UserPatient userPatient);
}