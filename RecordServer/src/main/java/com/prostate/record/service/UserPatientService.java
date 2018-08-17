package com.prostate.record.service;

import com.prostate.record.beans.WechatPatientBean;
import com.prostate.record.entity.UserPatient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPatientService extends BaseService<UserPatient> {
    int addUserPatient(String patientId, String token,String patientSource);

    List<WechatPatientBean> getPatientIdList(UserPatient userPatient);
}
