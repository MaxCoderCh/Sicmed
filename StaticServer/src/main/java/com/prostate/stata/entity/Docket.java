package com.prostate.stata.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Docket extends BaseEntity{

    private String id;

    private String docketName;

    private String docketType;

    private String docketStatus;

    private Date createTime;

    private String createUser;

}