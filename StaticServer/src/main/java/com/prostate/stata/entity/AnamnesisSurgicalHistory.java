package com.prostate.stata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnamnesisSurgicalHistory implements Serializable {
    private String id;

    private String surgicalHistoryName;

    private String surgicalHistorySpellName;

    private String surgicalHistoryNumber;

    private Integer orderWeight;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String deleteUser;

    private Date deleteTime;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSurgicalHistoryName() {
        return surgicalHistoryName;
    }

    public void setSurgicalHistoryName(String surgicalHistoryName) {
        this.surgicalHistoryName = surgicalHistoryName == null ? null : surgicalHistoryName.trim();
    }

    public String getSurgicalHistorySpellName() {
        return surgicalHistorySpellName;
    }

    public void setSurgicalHistorySpellName(String surgicalHistorySpellName) {
        this.surgicalHistorySpellName = surgicalHistorySpellName == null ? null : surgicalHistorySpellName.trim();
    }

    public String getSurgicalHistoryNumber() {
        return surgicalHistoryNumber;
    }

    public void setSurgicalHistoryNumber(String surgicalHistoryNumber) {
        this.surgicalHistoryNumber = surgicalHistoryNumber == null ? null : surgicalHistoryNumber.trim();
    }

    public Integer getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(Integer orderWeight) {
        this.orderWeight = orderWeight;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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