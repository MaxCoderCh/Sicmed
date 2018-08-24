package com.sicmed.assessmen.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class MedicalExaminationBean {

    private String id;

    private String bloodRoutineAnswer;

    private String digitalRectalAnswer;

    private String expressedProstaticSecretionAnswer;

    private String specificAntigenAnswer;

    private String ultrasonographyBAnswer;

    private String urineFlowRateAnswer;

    private String urineRoutineAnswer;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date createTime;
}
