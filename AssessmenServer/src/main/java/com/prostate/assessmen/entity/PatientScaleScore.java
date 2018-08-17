package com.prostate.assessmen.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientScaleScore {
    private String id;

    private String patientId;

    private String inflammationScore;

    private String symptomScore;

    private String lifeScore;

    private String scaleScore;

    private String optionScore;

    private String answer;

    private String caution;

    private String createDoctor;

    private Date createTime;

    private String updateDoctor;

    private Date updateTime;

    private String deleteDoctor;

    private Date deleteTime;

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

    public String getInflammationScore() {
        return inflammationScore;
    }

    public void setInflammationScore(String inflammationScore) {
        this.inflammationScore = inflammationScore == null ? null : inflammationScore.trim();
    }

    public String getOptionScore() {
        return optionScore;
    }

    public void setOptionScore(String optionScore) {
        this.optionScore = optionScore;
    }

    public String getSymptomScore() {
        return symptomScore;
    }

    public void setSymptomScore(String symptomScore) {
        this.symptomScore = symptomScore == null ? null : symptomScore.trim();
    }

    public String getLifeScore() {
        return lifeScore;
    }

    public void setLifeScore(String lifeScore) {
        this.lifeScore = lifeScore == null ? null : lifeScore.trim();
    }

    public String getScaleScore() {
        return scaleScore;
    }

    public void setScaleScore(String scaleScore) {
        this.scaleScore = scaleScore == null ? null : scaleScore.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution == null ? null : caution.trim();
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
}