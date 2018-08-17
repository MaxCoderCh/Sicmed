package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.entity.PatientScaleScore;
import com.prostate.assessmen.mapper.BaseReadMapper;

public interface ScaleScoreReadMapper extends BaseReadMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}