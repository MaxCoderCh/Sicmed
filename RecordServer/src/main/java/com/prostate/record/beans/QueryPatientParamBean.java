package com.prostate.record.beans;


import com.prostate.record.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryPatientParamBean extends BaseEntity{

    private String doctorId;
    private String patientName;
    private String stickerId;
}
