package com.prostate.assessmen.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.assessmen.entity.PatientNihCpsiScore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NihCpsiScoreBean extends PatientNihCpsiScore {

    private String inflammationScore; //炎症评分

    private String symptomScore; //症状评分

    private String lifeScore; //生活质量评分

    public String getInflammationScore() {
        return inflammationScore;
    }

    public void setInflammationScore(String inflammationScore) {
        this.inflammationScore = inflammationScore;
    }

    public String getSymptomScore() {
        return symptomScore;
    }

    public void setSymptomScore(String symptomScore) {
        this.symptomScore = symptomScore;
    }

    public String getLifeScore() {
        return lifeScore;
    }

    public void setLifeScore(String lifeScore) {
        this.lifeScore = lifeScore;
    }
}
