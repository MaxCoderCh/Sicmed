package com.sicmed.register.bean;

import com.sicmed.register.entity.GroupID;
import com.sicmed.register.entity.GroupWithoutId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 9:14
 * @Todo:  挂号对象
 */

public class RegisteredBean  {

    //主键
    @NotNull(message = "id不能为空",groups = GroupID.class)
    @Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
    private String id;        //挂号信息的id


    private String patientNumber; // 患者编号
    private String patientName; // 患者名字
    private String patientSex; // 患者性别
    private int patientAge;
    private String medicareNo;
    private String medicareType;
    private Date patientBirthday; // 患者生日

    @NotNull(message = "身份证号码不能为空",groups = GroupWithoutId.class)
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$",message = "身份证号码不符合规则",groups = GroupWithoutId.class)
    private String patientCard; // 患者身份证
    private Date patientFirstdate; // 患者第一次就诊时间
    private Date patientLastdate; // 患者最后一次就诊时间
    private String patientAddress; // 患者住址

    @Pattern(regexp = "^(13|14|15|16|17|18|19)\\d{9}$",message = "电话号码不符合规则",groups = GroupWithoutId.class)
    private String patientTel; // 患者电话

    @NotNull(message = "科室id不能为空",groups = GroupWithoutId.class)
    @Pattern(regexp = "^[A-Za-z0-9]{32}",message = "科室id必须是32位字符串",groups = GroupWithoutId.class)
    private String branchId;     //科室信息

    @NotNull(message = "医生信息id不能为空",groups = GroupWithoutId.class)
    @Pattern(regexp = "^[A-Za-z0-9]{32}",message = "医生信息必须是32位字符串",groups = GroupWithoutId.class)
    private String doctorId;     //医生信息

    @NotNull(message = "医保类型不能为空",groups = GroupWithoutId.class)
    @Pattern(regexp = "^[A-Za-z0-9]{32}",message = "医保类型信息必须是32位字符串",groups = GroupWithoutId.class)
    private String registeredTypeId;  //挂号类型

    @NotNull(message = "挂号金额不能为空",groups = GroupWithoutId.class)
    private String registeredPrice;  // 挂号费
    private String registeredStatus; //挂号转态

    @NotNull(message = "缴费类型不能为空",groups = GroupWithoutId.class)
    @Pattern(regexp = "^[A-Za-z0-9]{32}",message = "缴费类型信息必须是32位字符串",groups = GroupWithoutId.class)
    private String payType;

    public String getRegisteredStatus() {
        return registeredStatus;
    }

    public void setRegisteredStatus(String registeredStatus) {
        this.registeredStatus = registeredStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getMedicareNo() {
        return medicareNo;
    }

    public void setMedicareNo(String medicareNo) {
        this.medicareNo = medicareNo;
    }

    public String getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(String medicareType) {
        this.medicareType = medicareType;
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
        this.patientCard = patientCard;
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

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientTel() {
        return patientTel;
    }

    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRegisteredTypeId() {
        return registeredTypeId;
    }

    public void setRegisteredTypeId(String registeredTypeId) {
        this.registeredTypeId = registeredTypeId;
    }

    public String getRegisteredPrice() {
        return registeredPrice;
    }

    public void setRegisteredPrice(String registeredPrice) {
        this.registeredPrice = registeredPrice;
    }



    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "RegisteredBean{" +
                "id='" + id + '\'' +
                ", patientNumber='" + patientNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", patientAge=" + patientAge +
                ", medicareNo='" + medicareNo + '\'' +
                ", medicareType='" + medicareType + '\'' +
                ", patientBirthday=" + patientBirthday +
                ", patientCard='" + patientCard + '\'' +
                ", patientFirstdate=" + patientFirstdate +
                ", patientLastdate=" + patientLastdate +
                ", patientAddress='" + patientAddress + '\'' +
                ", patientTel='" + patientTel + '\'' +
                ", branchId='" + branchId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", registeredTypeId='" + registeredTypeId + '\'' +
                ", registeredPrice='" + registeredPrice + '\'' +
                ", registeredStatus='" + registeredStatus + '\'' +
                '}';
    }
}
