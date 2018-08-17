package com.sicmed.statistic.mapper.slaver;


import com.sicmed.statistic.entity.InquiryCountDoctor;

public interface InquiryCountDoctorReadMapper  extends BaseReadMapper<InquiryCountDoctor> {

    int getInquiryCount(String doctorId);
}