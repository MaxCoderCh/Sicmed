package com.prostate.order.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Setter
@Getter
@ToString
public class OrderInquiryListBean {
    //订单id
    private String id;
    //患者名字
    private String patientName;
    //患者年龄
    private String patientAge;
    //患者性别
    private String patientSex;
    //问题描述
    private String problemDescription;
    //订单价格价格
    private String orderPrice;
    //订单状态
    private String orderStatus;
    //订单日期
    private Date createTime;

}
