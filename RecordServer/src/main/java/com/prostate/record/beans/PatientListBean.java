package com.prostate.record.beans;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientListBean {

    private String patientId;

    private String patientName;

    private String patientAge;

    private String patientSex;

    private String patientSource;
}
