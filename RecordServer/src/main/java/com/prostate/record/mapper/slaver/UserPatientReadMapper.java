package com.prostate.record.mapper.slaver;

import com.prostate.record.beans.WechatPatientBean;
import com.prostate.record.entity.UserPatient;

import java.util.List;

public interface UserPatientReadMapper extends BaseReadMapper<UserPatient>{

    List<WechatPatientBean> getPatientIdList(UserPatient userPatient);
}