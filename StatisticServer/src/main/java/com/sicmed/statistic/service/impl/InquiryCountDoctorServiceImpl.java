package com.sicmed.statistic.service.impl;

import com.sicmed.statistic.entity.InquiryCountDoctor;
import com.sicmed.statistic.mapper.master.InquiryCountDoctorWriteMapper;
import com.sicmed.statistic.mapper.slaver.InquiryCountDoctorReadMapper;
import com.sicmed.statistic.service.InquiryCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InquiryCountDoctorServiceImpl implements InquiryCountDoctorService {


    @Autowired
    private InquiryCountDoctorReadMapper inquiryCountDoctorReadMapper;

    @Autowired
    private InquiryCountDoctorWriteMapper inquiryCountDoctorWriteMapper;

    @Override
    public int insertSelective(InquiryCountDoctor inquiryCountDoctor) {
        return inquiryCountDoctorWriteMapper.insertSelective(inquiryCountDoctor);
    }

    @Override
    public int updateSelective(InquiryCountDoctor inquiryCountDoctor) {
        return 0;
    }

    @Override
    public InquiryCountDoctor selectById(String id) {
        return null;
    }

    @Override
    public List<InquiryCountDoctor> selectByParams(InquiryCountDoctor inquiryCountDoctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int getInquiryCount(String doctorId) {
        return inquiryCountDoctorReadMapper.getInquiryCount(doctorId);
    }
}
