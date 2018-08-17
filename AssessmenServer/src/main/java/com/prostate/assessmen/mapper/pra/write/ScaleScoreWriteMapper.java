package com.prostate.assessmen.mapper.pra.write;

import com.prostate.assessmen.entity.PatientScaleScore;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface ScaleScoreWriteMapper extends BaseWriteMapper<PatientScaleScore> {

    int insertSelectiveById(PatientScaleScore scaleScore);
}