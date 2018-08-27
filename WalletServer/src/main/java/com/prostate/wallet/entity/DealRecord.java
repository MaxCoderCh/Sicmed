package com.prostate.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DealRecord extends BaseEntity {
    private String id;

    private String walletId;

    private String serialNumber;

    private String orderId;

    private String dealAmount;

    private String dealType;

    private String paymentType;

    private String walletBalance;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}