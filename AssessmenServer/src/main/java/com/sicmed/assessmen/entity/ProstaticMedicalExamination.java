package com.sicmed.assessmen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Null;
import java.util.Date;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProstaticMedicalExamination extends BaseEntity{

    @Null
    private String id;

    private String patientId;

    private String bloodRoutineAnswer;

    private String digitalRectalAnswer;

    private String expressedProstaticSecretionAnswer;

    private String specificAntigenAnswer;

    private String ultrasonographyBAnswer;

    private String urineFlowRateAnswer;

    private String urineRoutineAnswer;

    private String medicalExaminationRemark;

    private String bloodRoutineRemark;

    private String digitalRectalRemark;

    private String expressedProstaticSecretionRemark;

    private String specificAntigenRemark;

    private String ultrasonographyBRemark;

    private String urineFlowRateRemark;

    private String urineRoutineRemark;

    private String createDoctor;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date createTime;

    private String updateDoctor;

    private Date updateTime;

    private String deleteDoctor;

    private Date deleteTime;

    private String delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date urineRoutineInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date urineFlowRateInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date ultrasonographyBInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date digitalRectalInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date expressedProstaticSecretionInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date specificAntigenInspectTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date bloodRoutineInspectTime;


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

    public String getBloodRoutineAnswer() {
        return bloodRoutineAnswer;
    }

    public void setBloodRoutineAnswer(String bloodRoutineAnswer) {
        this.bloodRoutineAnswer = bloodRoutineAnswer == null ? null : bloodRoutineAnswer.trim();
    }

    public String getDigitalRectalAnswer() {
        return digitalRectalAnswer;
    }

    public void setDigitalRectalAnswer(String digitalRectalAnswer) {
        this.digitalRectalAnswer = digitalRectalAnswer == null ? null : digitalRectalAnswer.trim();
    }

    public String getExpressedProstaticSecretionAnswer() {
        return expressedProstaticSecretionAnswer;
    }

    public void setExpressedProstaticSecretionAnswer(String expressedProstaticSecretionAnswer) {
        this.expressedProstaticSecretionAnswer = expressedProstaticSecretionAnswer == null ? null : expressedProstaticSecretionAnswer.trim();
    }

    public String getSpecificAntigenAnswer() {
        return specificAntigenAnswer;
    }

    public void setSpecificAntigenAnswer(String specificAntigenAnswer) {
        this.specificAntigenAnswer = specificAntigenAnswer == null ? null : specificAntigenAnswer.trim();
    }

    public String getUltrasonographyBAnswer() {
        return ultrasonographyBAnswer;
    }

    public void setUltrasonographyBAnswer(String ultrasonographyBAnswer) {
        this.ultrasonographyBAnswer = ultrasonographyBAnswer == null ? null : ultrasonographyBAnswer.trim();
    }

    public String getUrineFlowRateAnswer() {
        return urineFlowRateAnswer;
    }

    public void setUrineFlowRateAnswer(String urineFlowRateAnswer) {
        this.urineFlowRateAnswer = urineFlowRateAnswer == null ? null : urineFlowRateAnswer.trim();
    }

    public String getUrineRoutineAnswer() {
        return urineRoutineAnswer;
    }

    public void setUrineRoutineAnswer(String urineRoutineAnswer) {
        this.urineRoutineAnswer = urineRoutineAnswer == null ? null : urineRoutineAnswer.trim();
    }

    public String getMedicalExaminationRemark() {
        return medicalExaminationRemark;
    }

    public void setMedicalExaminationRemark(String medicalExaminationRemark) {
        this.medicalExaminationRemark = medicalExaminationRemark == null ? null : medicalExaminationRemark.trim();
    }

    public String getBloodRoutineRemark() {
        return bloodRoutineRemark;
    }

    public void setBloodRoutineRemark(String bloodRoutineRemark) {
        this.bloodRoutineRemark = bloodRoutineRemark == null ? null : bloodRoutineRemark.trim();
    }

    public String getDigitalRectalRemark() {
        return digitalRectalRemark;
    }

    public void setDigitalRectalRemark(String digitalRectalRemark) {
        this.digitalRectalRemark = digitalRectalRemark == null ? null : digitalRectalRemark.trim();
    }

    public String getExpressedProstaticSecretionRemark() {
        return expressedProstaticSecretionRemark;
    }

    public void setExpressedProstaticSecretionRemark(String expressedProstaticSecretionRemark) {
        this.expressedProstaticSecretionRemark = expressedProstaticSecretionRemark == null ? null : expressedProstaticSecretionRemark.trim();
    }

    public String getSpecificAntigenRemark() {
        return specificAntigenRemark;
    }

    public void setSpecificAntigenRemark(String specificAntigenRemark) {
        this.specificAntigenRemark = specificAntigenRemark == null ? null : specificAntigenRemark.trim();
    }

    public String getUltrasonographyBRemark() {
        return ultrasonographyBRemark;
    }

    public void setUltrasonographyBRemark(String ultrasonographyBRemark) {
        this.ultrasonographyBRemark = ultrasonographyBRemark == null ? null : ultrasonographyBRemark.trim();
    }

    public String getUrineFlowRateRemark() {
        return urineFlowRateRemark;
    }

    public void setUrineFlowRateRemark(String urineFlowRateRemark) {
        this.urineFlowRateRemark = urineFlowRateRemark == null ? null : urineFlowRateRemark.trim();
    }

    public String getUrineRoutineRemark() {
        return urineRoutineRemark;
    }

    public void setUrineRoutineRemark(String urineRoutineRemark) {
        this.urineRoutineRemark = urineRoutineRemark == null ? null : urineRoutineRemark.trim();
    }

    public String getCreateDoctor() {
        return createDoctor;
    }

    public void setCreateDoctor(String createDoctor) {
        this.createDoctor = createDoctor == null ? null : createDoctor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateDoctor() {
        return updateDoctor;
    }

    public void setUpdateDoctor(String updateDoctor) {
        this.updateDoctor = updateDoctor == null ? null : updateDoctor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteDoctor() {
        return deleteDoctor;
    }

    public void setDeleteDoctor(String deleteDoctor) {
        this.deleteDoctor = deleteDoctor == null ? null : deleteDoctor.trim();
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

    public Date getUrineRoutineInspectTime() {
        return urineRoutineInspectTime;
    }

    public void setUrineRoutineInspectTime(Date urineRoutineInspectTime) {
        this.urineRoutineInspectTime = urineRoutineInspectTime;
    }

    public Date getUrineFlowRateInspectTime() {
        return urineFlowRateInspectTime;
    }

    public void setUrineFlowRateInspectTime(Date urineFlowRateInspectTime) {
        this.urineFlowRateInspectTime = urineFlowRateInspectTime;
    }

    public Date getUltrasonographyBInspectTime() {
        return ultrasonographyBInspectTime;
    }

    public void setUltrasonographyBInspectTime(Date ultrasonographyBInspectTime) {
        this.ultrasonographyBInspectTime = ultrasonographyBInspectTime;
    }

    public Date getDigitalRectalInspectTime() {
        return digitalRectalInspectTime;
    }

    public void setDigitalRectalInspectTime(Date digitalRectalInspectTime) {
        this.digitalRectalInspectTime = digitalRectalInspectTime;
    }

    public Date getExpressedProstaticSecretionInspectTime() {
        return expressedProstaticSecretionInspectTime;
    }

    public void setExpressedProstaticSecretionInspectTime(Date expressedProstaticSecretionInspectTime) {
        this.expressedProstaticSecretionInspectTime = expressedProstaticSecretionInspectTime;
    }

    public Date getSpecificAntigenInspectTime() {
        return specificAntigenInspectTime;
    }

    public void setSpecificAntigenInspectTime(Date specificAntigenInspectTime) {
        this.specificAntigenInspectTime = specificAntigenInspectTime;
    }

    public Date getBloodRoutineInspectTime() {
        return bloodRoutineInspectTime;
    }

    public void setBloodRoutineInspectTime(Date bloodRoutineInspectTime) {
        this.bloodRoutineInspectTime = bloodRoutineInspectTime;
    }

    @Override
    public String toString() {
        return "ProstaticMedicalExamination{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", bloodRoutineAnswer='" + bloodRoutineAnswer + '\'' +
                ", digitalRectalAnswer='" + digitalRectalAnswer + '\'' +
                ", expressedProstaticSecretionAnswer='" + expressedProstaticSecretionAnswer + '\'' +
                ", specificAntigenAnswer='" + specificAntigenAnswer + '\'' +
                ", ultrasonographyBAnswer='" + ultrasonographyBAnswer + '\'' +
                ", urineFlowRateAnswer='" + urineFlowRateAnswer + '\'' +
                ", urineRoutineAnswer='" + urineRoutineAnswer + '\'' +
                ", medicalExaminationRemark='" + medicalExaminationRemark + '\'' +
                ", bloodRoutineRemark='" + bloodRoutineRemark + '\'' +
                ", digitalRectalRemark='" + digitalRectalRemark + '\'' +
                ", expressedProstaticSecretionRemark='" + expressedProstaticSecretionRemark + '\'' +
                ", specificAntigenRemark='" + specificAntigenRemark + '\'' +
                ", ultrasonographyBRemark='" + ultrasonographyBRemark + '\'' +
                ", urineFlowRateRemark='" + urineFlowRateRemark + '\'' +
                ", urineRoutineRemark='" + urineRoutineRemark + '\'' +
                ", createDoctor='" + createDoctor + '\'' +
                ", createTime=" + createTime +
                ", updateDoctor='" + updateDoctor + '\'' +
                ", updateTime=" + updateTime +
                ", deleteDoctor='" + deleteDoctor + '\'' +
                ", deleteTime=" + deleteTime +
                ", delFlag='" + delFlag + '\'' +
                ", urineRoutineInspectTime=" + urineRoutineInspectTime +
                ", urineFlowRateInspectTime=" + urineFlowRateInspectTime +
                ", ultrasonographyBInspectTime=" + ultrasonographyBInspectTime +
                ", digitalRectalInspectTime=" + digitalRectalInspectTime +
                ", expressedProstaticSecretionInspectTime=" + expressedProstaticSecretionInspectTime +
                ", specificAntigenInspectTime=" + specificAntigenInspectTime +
                ", bloodRoutineInspectTime=" + bloodRoutineInspectTime +
                '}';
    }
}