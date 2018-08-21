package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.entity.PatientScaleScore;
import com.sicmed.assessmen.mapper.BaseReadMapper;

public interface ScaleScoreReadMapper extends BaseReadMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}