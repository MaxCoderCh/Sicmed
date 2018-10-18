package com.sicmed.register.entity;

import com.sicmed.register.base.BaseEntity;

import java.util.Date;

public class SicmedRegistered extends BaseEntity{
    private String id;

    private String patientId;

    private String branchId;

    private String doctorId;

    private String registrationType;

    private String registeredStatus;

    private String registeredPrice;

    private String priceStatus;

    private Date registeredBeginDate;

    private Date registeredEndDate;

    /**
     *  新增收费员信息和缴费类型信息
     */
    private String chargeUser;   //收费员信息
    private String refundUser;   //退费者信息
    private String payType;

    private String createUser;   //挂号员信息

    private Date createDate;

    private String updateUser;   //改号者信息

    private Date updateDate;

    private String deleteUser;    //退号者信息

    private Date deleteDate;

    private String remarks;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType == null ? null : registrationType.trim();
    }

    public String getRegisteredStatus() {
        return registeredStatus;
    }

    public void setRegisteredStatus(String registeredStatus) {
        this.registeredStatus = registeredStatus == null ? null : registeredStatus.trim();
    }

    public String getRegisteredPrice() {
        return registeredPrice;
    }

    public void setRegisteredPrice(String registeredPrice) {
        this.registeredPrice = registeredPrice == null ? null : registeredPrice.trim();
    }

    public String getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(String priceStatus) {
        this.priceStatus = priceStatus == null ? null : priceStatus.trim();
    }

    public Date getRegisteredBeginDate() {
        return registeredBeginDate;
    }

    public void setRegisteredBeginDate(Date registeredBeginDate) {
        this.registeredBeginDate = registeredBeginDate;
    }

    public Date getRegisteredEndDate() {
        return registeredEndDate;
    }

    public void setRegisteredEndDate(Date registeredEndDate) {
        this.registeredEndDate = registeredEndDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser;
    }


    @Override
    public String toString() {
        return "SicmedRegistered{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", registrationType='" + registrationType + '\'' +
                ", registeredStatus='" + registeredStatus + '\'' +
                ", registeredPrice='" + registeredPrice + '\'' +
                ", priceStatus='" + priceStatus + '\'' +
                ", registeredBeginDate=" + registeredBeginDate +
                ", registeredEndDate=" + registeredEndDate +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", updateUser='" + updateUser + '\'' +
                ", updateDate=" + updateDate +
                ", deleteUser='" + deleteUser + '\'' +
                ", deleteDate=" + deleteDate +
                ", remarks='" + remarks + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}