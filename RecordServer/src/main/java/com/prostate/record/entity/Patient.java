package com.prostate.record.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient extends BaseEntity implements Serializable {

    @Null
    private String id;

    @Null
    private String patientNumber;

    @Length(min = 2,max = 5)
    private String patientName;

    @Length(min = 16,max = 18)
    private String patientCard;


    private String patientPhone;

    private String patientBirthday;

    @DecimalMin(value = "1" ,message = "年龄必须大于1")
    private String patientAge;

    private String patientHeight;

    private String patientWeight;

    private String patientSex;

    private String cityId;

    private String detailAddress;

    private String educationId;

    private String professionId;

    private String nationId;

    private String bloodGroupId;

    @Null
    private String createDoctor;

    //    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+08")
    @Null
    private Date createTime;

    @Null
    private String updateDoctor;

    @Null
    private Date updateTime;

    @Null
    private String deleteDoctor;

    @Null
    private Date deleteTime;

    @Null
    private String delFlag;


    @Override
    public String toString() {
        return "id:" + id
                + ",patientNumber:" + patientNumber
                + ",patientName:" + patientName
                + ",patientCard:" + patientCard
                + ",patientPhone:" + patientPhone
                + ",patientBirthday:" + patientBirthday
                + ",patientAge:" + patientAge
                + ",patientSex:" + patientSex
                + ",cityId:" + cityId
                + ",detailAddress:" + detailAddress
                + ",educationId:" + educationId
                + ",professionId:" + professionId
                + ",nationId:" + nationId
                + ",bloodGroupId:" + bloodGroupId
                + ",createDoctor:" + createDoctor
                + ",createTime:" + createTime
                + ",updateDoctor:" + updateDoctor
                + ",updateTime:" + updateTime
                + ",deleteDoctor:" + deleteDoctor
                + ",deleteTime:" + deleteTime
                + ",delFlag:" + delFlag;
    }

}