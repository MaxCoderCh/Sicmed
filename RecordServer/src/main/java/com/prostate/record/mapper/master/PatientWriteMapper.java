package com.prostate.record.mapper.master;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;

import java.util.List;


public interface PatientWriteMapper extends BaseWriteMapper<Patient> {


    List<PatientBean> selectByParamss(Patient e);

    Patient selectByIdCard(String idCard);

    PatientBean selectPatientDetailById(String id);

    String selectCountByParams(PatientBean patientBean);

    PatientAnamnesisBean selectPatientInfoById(String id);

    int insertSelectiveById(Patient patient);
}