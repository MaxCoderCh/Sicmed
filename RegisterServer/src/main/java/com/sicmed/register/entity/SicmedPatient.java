package com.sicmed.register.entity;

import java.util.Date;

public class SicmedPatient {
    private String id;

    private String patientNumber;

    private String patientName;

    private String patientSex;

    private Integer patientAge;

    private Date patientBirthday;

    private String patientCard;

    private String patientTel;

    private String patientAddress;

    private Date patientFirstdate;

    private Date patientLastdate;

    private String medicareType;

    private String medicareNo;

    private String branchId;

    private String doctorId;

    private String remarks;

    private String createUser;

    private String updateUser;

    private String deleteUser;

    private Date createDate;

    private Date updateDate;

    private Date deleteDate;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber == null ? null : patientNumber.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public Date getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Date patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(String patientCard) {
        this.patientCard = patientCard == null ? null : patientCard.trim();
    }

    public String getPatientTel() {
        return patientTel;
    }

    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel == null ? null : patientTel.trim();
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress == null ? null : patientAddress.trim();
    }

    public Date getPatientFirstdate() {
        return patientFirstdate;
    }

    public void setPatientFirstdate(Date patientFirstdate) {
        this.patientFirstdate = patientFirstdate;
    }

    public Date getPatientLastdate() {
        return patientLastdate;
    }

    public void setPatientLastdate(Date patientLastdate) {
        this.patientLastdate = patientLastdate;
    }

    public String getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(String medicareType) {
        this.medicareType = medicareType == null ? null : medicareType.trim();
    }

    public String getMedicareNo() {
        return medicareNo;
    }

    public void setMedicareNo(String medicareNo) {
        this.medicareNo = medicareNo == null ? null : medicareNo.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser == null ? null : deleteUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    @Override
    public String toString() {
        return "SicmedPatient{" +
                "id='" + id + '\'' +
                ", patientNumber='" + patientNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", patientAge=" + patientAge +
                ", patientBirthday=" + patientBirthday +
                ", patientCard='" + patientCard + '\'' +
                ", patientTel='" + patientTel + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                ", patientFirstdate=" + patientFirstdate +
                ", patientLastdate=" + patientLastdate +
                ", medicareType='" + medicareType + '\'' +
                ", medicareNo='" + medicareNo + '\'' +
                ", branchId='" + branchId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", deleteUser='" + deleteUser + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", deleteDate=" + deleteDate +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}