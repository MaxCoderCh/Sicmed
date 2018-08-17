package com.sicmed.statistic.service;

import com.sicmed.statistic.entity.FocusCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface FocusCountDoctorService extends BaseService<FocusCountDoctor> {
    int getFocusCount(String doctorId);
}
