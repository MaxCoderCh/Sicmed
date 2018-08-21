package com.sicmed.assessmen.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryRecord {
    private String id;

    private String patient;

    private String patientArchive;

    private String inquiryDescription;

    private String inquiryAnswer;

    private String recordStatus;

    private String recordType;

    private String caution;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String deleteUser;

    private Date deleteTime;

    private String delFlag;


}