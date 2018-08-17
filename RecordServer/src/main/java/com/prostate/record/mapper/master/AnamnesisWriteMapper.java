package com.prostate.record.mapper.master;

import com.prostate.record.entity.Anamnesis;

public interface AnamnesisWriteMapper extends BaseWriteMapper<Anamnesis> {

    int checkRepeated(Anamnesis anamnesis);
}