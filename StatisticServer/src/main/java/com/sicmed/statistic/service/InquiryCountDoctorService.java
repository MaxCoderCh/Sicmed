package com.sicmed.statistic.service;

import com.sicmed.statistic.entity.InquiryCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface InquiryCountDoctorService extends BaseService<InquiryCountDoctor> {
    int getInquiryCount(String doctorId);
}
