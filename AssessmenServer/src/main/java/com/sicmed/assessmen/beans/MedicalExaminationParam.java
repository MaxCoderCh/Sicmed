package com.sicmed.assessmen.beans;

import java.util.Date;

public class MedicalExaminationParam {


    private String patientAge;

    private String bloodRoutineAnswer;

    private String digitalRectalAnswer;

    private String expressedProstaticSecretionAnswer;

    private String specificAntigenAnswer;

    private String ultrasonographyBAnswer;

    private String urineFlowRateAnswer;

    private String urineRoutineAnswer;


    private Date bloodRoutineInspectTime;
    private Date digitalRectalInspectTime;
    private Date expressedProstaticSecretionInspectTime;
    private Date specificAntigenInspectTime;
    private Date ultrasonographyBInspectTime;
    private Date urineFlowRateInspectTime;
    private Date urineRoutineInspectTime;


    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getBloodRoutineAnswer() {
        return bloodRoutineAnswer;
    }

    public void setBloodRoutineAnswer(String bloodRoutineAnswer) {
        this.bloodRoutineAnswer = bloodRoutineAnswer;
    }

    public String getDigitalRectalAnswer() {
        return digitalRectalAnswer;
    }

    public void setDigitalRectalAnswer(String digitalRectalAnswer) {
        this.digitalRectalAnswer = digitalRectalAnswer;
    }

    public String getExpressedProstaticSecretionAnswer() {
        return expressedProstaticSecretionAnswer;
    }

    public void setExpressedProstaticSecretionAnswer(String expressedProstaticSecretionAnswer) {
        this.expressedProstaticSecretionAnswer = expressedProstaticSecretionAnswer;
    }

    public String getSpecificAntigenAnswer() {
        return specificAntigenAnswer;
    }

    public void setSpecificAntigenAnswer(String specificAntigenAnswer) {
        this.specificAntigenAnswer = specificAntigenAnswer;
    }

    public String getUltrasonographyBAnswer() {
        return ultrasonographyBAnswer;
    }

    public void setUltrasonographyBAnswer(String ultrasonographyBAnswer) {
        this.ultrasonographyBAnswer = ultrasonographyBAnswer;
    }

    public String getUrineFlowRateAnswer() {
        return urineFlowRateAnswer;
    }

    public void setUrineFlowRateAnswer(String urineFlowRateAnswer) {
        this.urineFlowRateAnswer = urineFlowRateAnswer;
    }

    public String getUrineRoutineAnswer() {
        return urineRoutineAnswer;
    }

    public void setUrineRoutineAnswer(String urineRoutineAnswer) {
        this.urineRoutineAnswer = urineRoutineAnswer;
    }

    public Date getBloodRoutineInspectTime() {
        return bloodRoutineInspectTime;
    }

    public void setBloodRoutineInspectTime(Date bloodRoutineInspectTime) {
        this.bloodRoutineInspectTime = bloodRoutineInspectTime;
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

    public Date getUltrasonographyBInspectTime() {
        return ultrasonographyBInspectTime;
    }

    public void setUltrasonographyBInspectTime(Date ultrasonographyBInspectTime) {
        this.ultrasonographyBInspectTime = ultrasonographyBInspectTime;
    }

    public Date getUrineFlowRateInspectTime() {
        return urineFlowRateInspectTime;
    }

    public void setUrineFlowRateInspectTime(Date urineFlowRateInspectTime) {
        this.urineFlowRateInspectTime = urineFlowRateInspectTime;
    }

    public Date getUrineRoutineInspectTime() {
        return urineRoutineInspectTime;
    }

    public void setUrineRoutineInspectTime(Date urineRoutineInspectTime) {
        this.urineRoutineInspectTime = urineRoutineInspectTime;
    }

    @Override
    public String toString() {
        return "MedicalExaminationParam{" +
                "patientAge='" + patientAge + '\'' +
                ", bloodRoutineAnswer='" + bloodRoutineAnswer + '\'' +
                ", digitalRectalAnswer='" + digitalRectalAnswer + '\'' +
                ", expressedProstaticSecretionAnswer='" + expressedProstaticSecretionAnswer + '\'' +
                ", specificAntigenAnswer='" + specificAntigenAnswer + '\'' +
                ", ultrasonographyBAnswer='" + ultrasonographyBAnswer + '\'' +
                ", urineFlowRateAnswer='" + urineFlowRateAnswer + '\'' +
                ", urineRoutineAnswer='" + urineRoutineAnswer + '\'' +
                ", bloodRoutineInspectTime=" + bloodRoutineInspectTime +
                ", digitalRectalInspectTime=" + digitalRectalInspectTime +
                ", expressedProstaticSecretionInspectTime=" + expressedProstaticSecretionInspectTime +
                ", specificAntigenInspectTime=" + specificAntigenInspectTime +
                ", ultrasonographyBInspectTime=" + ultrasonographyBInspectTime +
                ", urineFlowRateInspectTime=" + urineFlowRateInspectTime +
                ", urineRoutineInspectTime=" + urineRoutineInspectTime +
                '}';
    }
}
