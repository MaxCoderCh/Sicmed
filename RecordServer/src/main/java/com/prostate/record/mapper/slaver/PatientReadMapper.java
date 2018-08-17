package com.prostate.record.mapper.slaver;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;

import java.util.List;


public interface PatientReadMapper extends BaseReadMapper<Patient> {


    List<PatientBean> selectByParamss(Patient e);

    Patient selectByIdCard(String idCard);

    PatientBean selectPatientDetailById(String id);

    String selectCountByParams(PatientBean patientBean);

    PatientAnamnesisBean selectPatientInfoById(String id);

    List<PatientBean> getList(PatientBean patientBean);


}