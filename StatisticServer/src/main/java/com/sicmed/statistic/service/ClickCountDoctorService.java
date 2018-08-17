package com.sicmed.statistic.service;

import com.sicmed.statistic.entity.ClickCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface ClickCountDoctorService extends BaseService<ClickCountDoctor> {
    int getClickCount(String doctorId);
}
