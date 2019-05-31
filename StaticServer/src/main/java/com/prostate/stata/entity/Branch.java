package com.prostate.stata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Branch {
    private String id;

    private String parentBranchId;

    private String branchName;

    private String branchGrade;

    private String branchInform;

    private Integer branchWeight;

    private String orderWeight;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String deleteUser;

    private Date deleteTime;

    private String delFlag;


}