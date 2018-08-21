package com.sicmed.assessmen.mapper.pra.write;

import com.sicmed.assessmen.entity.PatientScaleScore;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface ScaleScoreWriteMapper extends BaseWriteMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}