package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.entity.PatientScaleScore;

public interface ScaleScoreReadMapper extends BaseReadMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}