package com.prostate.order.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInquiryBean {

    private String id;
    private String orderStatus;
    private String orderType;
    private String orderDescription;
    private String patientName;
    private String patientSex;
    private String patientAge;
    @JsonIgnore
    private String patientId;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String orderPrice;


}
