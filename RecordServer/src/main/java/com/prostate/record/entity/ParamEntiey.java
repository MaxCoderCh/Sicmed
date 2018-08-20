package com.prostate.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Arrays;

@Setter
@Getter
public class ParamEntiey {

    @Length(min = 32,max = 32,message = "患者ID格式不正确")
    public String patientId;

    public String[] anamnesisAllergyDrugIds;

    public String[] anamnesisEatingDrugIds;

    public String[] anamnesisIllnessIds;

    public String[] anamnesisSurgicalHistoryIds;

    public String[] otherIds;


    @Override
    public String toString() {
        return "ParamEntiey{" +
                "patientId='" + patientId + '\'' +
                ", anamnesisAllergyDrugIds=" + Arrays.toString(anamnesisAllergyDrugIds) +
                ", anamnesisEatingDrugIds=" + Arrays.toString(anamnesisEatingDrugIds) +
                ", anamnesisIllnessIds=" + Arrays.toString(anamnesisIllnessIds) +
                ", anamnesisSurgicalHistoryIds=" + Arrays.toString(anamnesisSurgicalHistoryIds) +
                ", otherIds=" + Arrays.toString(otherIds) +
                '}';
    }
}
