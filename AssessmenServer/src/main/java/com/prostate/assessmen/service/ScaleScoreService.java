package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.PatientScaleScore;
import org.springframework.stereotype.Service;

@Service
public interface ScaleScoreService extends BaseService<PatientScaleScore> {
    int insertSelectiveById(PatientScaleScore scaleScore);
}
