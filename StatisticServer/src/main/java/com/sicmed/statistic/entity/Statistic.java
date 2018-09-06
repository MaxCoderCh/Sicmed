package com.sicmed.statistic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Statistic {
    private String id;

    private String userId;

    private String statisticName;

    private String statisticValue;

    private String statisticType;

    private String statisticStatus;

    private Date createTime;


}