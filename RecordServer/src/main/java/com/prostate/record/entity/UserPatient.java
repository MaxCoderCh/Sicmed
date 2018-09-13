package com.prostate.record.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class UserPatient extends BaseEntity{
    private String id;

    private String userId;

    private String patientId;

    private String patientSource;

    private String patientType;

    private String patientStatus;

    private Date createTime;


}