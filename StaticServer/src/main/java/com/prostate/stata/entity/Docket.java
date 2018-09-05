package com.prostate.stata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Docket extends BaseEntity{

    private String id;

    private String docketName;

    private String docketValue;

    private String docketType;

    private String docketStatus;

    private Date createTime;

    private String createUser;

}