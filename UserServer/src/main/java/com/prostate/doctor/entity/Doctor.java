package com.prostate.doctor.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Doctor {

    private String id;

    private String doctorPhone;

    private String doctorPassword;

    private String salt;

    private Date createTime;

    private String createIp;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String userRole;

}