package com.sicmed.archive.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class MedicalReport {
    private String id;

    private String patientId;

    private String reportUrl;

    private String reportType;

    private String reportGroup;

    private Date submitTime;

    private Date assayTime;

    private Date createTime;


}