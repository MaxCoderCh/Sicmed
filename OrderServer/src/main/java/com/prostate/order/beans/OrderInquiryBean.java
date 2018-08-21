package com.prostate.order.beans;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderInquiryBean {

    private String id;
    private String orderStatus;
    private String orderType;
    private String orderDescription;
    private String patientName;
    private String patientSex;
    private String patientAge;


}
