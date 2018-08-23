package com.prostate.record.mapper.slaver;

import com.prostate.record.beans.*;
import com.prostate.record.entity.Patient;

import java.util.List;


public interface PatientReadMapper extends BaseReadMapper<Patient> {


    List<PatientBean> selectByParamss(Patient e);

    Patient selectByIdCard(String idCard);

    PatientBean selectPatientDetailById(String id);

    String selectCountByParams(PatientBean patientBean);

    PatientAnamnesisBean selectPatientInfoById(String id);

    List<WeChatPatientBean> getPatientListByIds(String[] patientIds);

    List<PatientListBean> queryByParams(QueryPatientParamBean queryPatientParamBean);
}