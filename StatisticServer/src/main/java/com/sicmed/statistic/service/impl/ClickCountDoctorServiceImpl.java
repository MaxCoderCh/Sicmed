package com.sicmed.statistic.service.impl;

import com.sicmed.statistic.entity.ClickCountDoctor;
import com.sicmed.statistic.mapper.master.ClickCountDoctorWriteMapper;
import com.sicmed.statistic.mapper.slaver.ClickCountDoctorReadMapper;
import com.sicmed.statistic.service.ClickCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClickCountDoctorServiceImpl implements ClickCountDoctorService {

    @Autowired
    private ClickCountDoctorReadMapper clickCountDoctorReadMapper;

    @Autowired
    private ClickCountDoctorWriteMapper clickCountDoctorWriteMapper;

    @Override
    public int insertSelective(ClickCountDoctor clickCountDoctor) {
        return clickCountDoctorWriteMapper.insertSelective(clickCountDoctor);
    }

    @Override
    public int updateSelective(ClickCountDoctor clickCountDoctor) {
        return 0;
    }

    @Override
    public ClickCountDoctor selectById(String id) {
        return null;
    }

    @Override
    public List<ClickCountDoctor> selectByParams(ClickCountDoctor clickCountDoctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int getClickCount(String doctorId) {
        return clickCountDoctorReadMapper.getClickCount(doctorId);
    }
}
