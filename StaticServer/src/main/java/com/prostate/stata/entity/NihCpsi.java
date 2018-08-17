package com.prostate.stata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NihCpsi implements Serializable {

    private String id;

    private String parentId;

    private String nihCpsiTitle;

    private String nihCpsiScore;

    private String nihCpsiType;

    private String nihCpsiWeight;

    private String orderWeight;

    private String createUser;

    private Date createTime;

    private String deleteUser;

    private Date deleteTime;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getNihCpsiTitle() {
        return nihCpsiTitle;
    }

    public void setNihCpsiTitle(String nihCpsiTitle) {
        this.nihCpsiTitle = nihCpsiTitle == null ? null : nihCpsiTitle.trim();
    }

    public String getNihCpsiScore() {
        return nihCpsiScore;
    }

    public void setNihCpsiScore(String nihCpsiScore) {
        this.nihCpsiScore = nihCpsiScore == null ? null : nihCpsiScore.trim();
    }

    public String getNihCpsiWeight() {
        return nihCpsiWeight;
    }

    public void setNihCpsiWeight(String nihCpsiWeight) {
        this.nihCpsiWeight = nihCpsiWeight == null ? null : nihCpsiWeight.trim();
    }

    public String getNihCpsiType() {
        return nihCpsiType;
    }

    public void setNihCpsiType(String nihCpsiType) {
        this.nihCpsiType = nihCpsiType;
    }

    public String getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(String orderWeight) {
        this.orderWeight = orderWeight == null ? null : orderWeight.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}