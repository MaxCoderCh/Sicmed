package com.prostate.record.beans;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class PatientAnamnesisBean extends Patient implements Serializable {

    private static final long serialVersionUID = 7226090218569549907l;

    private List<Anamnesis> anamnesisAllergyDrugList;

    private List<Anamnesis> anamnesisEatingDrugList;

    private List<Anamnesis> anamnesisIllnessList;

    private List<Anamnesis> anamnesisSurgicalHistoryList;

    private List<Anamnesis> otherList;

    private String bloodGroup;

    private String professionName;

    private String EducationName;

    private String NationName;

    private String provinceId;

    private String citysId;

    private String patientSource;

    private Map<String,String> cityDetailMap;

    public void setPatient(Patient patient) {
        super.setId(patient.getId());
        super.setPatientNumber(patient.getPatientNumber());
        super.setPatientName(patient.getPatientName());
        super.setPatientCard(patient.getPatientCard());
        super.setPatientPhone(patient.getPatientPhone());
        super.setPatientBirthday(patient.getPatientBirthday());
        super.setPatientAge(patient.getPatientAge());
        super.setPatientSex(patient.getPatientSex());
        super.setPatientHeight(patient.getPatientHeight());
        super.setPatientWeight(patient.getPatientWeight());
        super.setDetailAddress(patient.getDetailAddress());
        super.setCreateTime(patient.getCreateTime());
        super.setBloodGroupId(patient.getBloodGroupId());
        super.setEducationId(patient.getEducationId());
        super.setNationId(patient.getNationId());
        super.setProfessionId(patient.getProfessionId());
    }


}
