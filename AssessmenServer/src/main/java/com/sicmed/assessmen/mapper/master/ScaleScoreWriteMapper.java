package com.sicmed.assessmen.mapper.master;

import com.sicmed.assessmen.entity.PatientScaleScore;

public interface ScaleScoreWriteMapper extends BaseWriteMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}