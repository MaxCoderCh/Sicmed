package com.prostate.record.service;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPatientService extends BaseService<UserPatient> {
    int addUserPatient(String patientId, String token,String patientSource);

    List<WeChatPatientBean> getPatientList(UserPatient userPatient);

    int removeByParams(UserPatient userPatient);

    int updateByParams(UserPatient userPatient);

    UserPatient getByPatientIdAndToken(UserPatient userPatient);

    int selectCountByParams(UserPatient userPatient);

    String countByParams(UserPatient userPatient);

    List<WeChatPatientBean> getAcceptedTurnPatientList(UserPatient userPatient);

    int deleteByParam(UserPatient oldUserPatient);
}
