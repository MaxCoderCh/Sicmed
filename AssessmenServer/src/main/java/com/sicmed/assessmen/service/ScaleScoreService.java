package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.PatientScaleScore;
import org.springframework.stereotype.Service;

@Service
public interface ScaleScoreService extends BaseService<PatientScaleScore> {
    int insertSelectiveById(PatientScaleScore scaleScore);
}
