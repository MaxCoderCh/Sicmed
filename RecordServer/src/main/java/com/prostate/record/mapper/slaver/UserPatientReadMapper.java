package com.prostate.record.mapper.slaver;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;

import java.util.List;

public interface UserPatientReadMapper extends BaseReadMapper<UserPatient>{

    List<WeChatPatientBean> getPatientList(UserPatient userPatient);

    UserPatient getByPatientIdAndToken(UserPatient userPatient);

    int selectCountByParams(UserPatient userPatient);

    String countByParams(UserPatient userPatient);

    List<WeChatPatientBean> getAcceptedTurnPatientList(UserPatient userPatient);

}