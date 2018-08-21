package com.prostate.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class OrderInquiry implements Serializable{

    private String id;

    @JsonIgnore
    private String buyer;

    @JsonIgnore
    private String seller;

    private String patient;

    private String doctor;

    @JsonIgnore
    private String goods;

    @JsonIgnore
    private String orderNumber;

    private String orderPrice;

    private String orderType;

    private String orderStatus;

    private String patientArchive;

    private String orderDescription;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private String createUser;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private String updateUser;

    @JsonIgnore
    private Date deleteTime;

    @JsonIgnore
    private String deleteUser;

    @JsonIgnore
    private String delFlag;


}