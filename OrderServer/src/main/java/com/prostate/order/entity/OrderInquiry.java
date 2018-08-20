package com.prostate.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class OrderInquiry {
    private String id;

    private String buyer;

    private String seller;

    private String patient;

    private String doctor;

    private String goods;

    private String orderPrice;

    private String orderType;

    private String orderStatus;

    private String patientArchive;

    private String orderDescription;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Date deleteTime;

    private String deleteUser;

    private String delFlag;

   
}