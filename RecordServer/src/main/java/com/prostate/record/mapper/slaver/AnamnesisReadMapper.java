package com.prostate.record.mapper.slaver;

import com.prostate.record.entity.Anamnesis;

import java.util.List;

public interface AnamnesisReadMapper extends BaseReadMapper<Anamnesis> {

    int checkRepeated(Anamnesis anamnesis);

    List<Anamnesis> getByPatientId(String patientId);
}