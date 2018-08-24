package com.prostate.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DealRecord {
    private String id;

    private String walletId;

    private String serialNumber;

    private String orderId;

    private String dealAmount;

    private String dealType;

    private String paymentType;

    private String walletBalance;

    private String remark;

    private Date createTime;

}