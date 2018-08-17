package com.sicmed.statistic.mapper.slaver;


import com.sicmed.statistic.entity.FocusCountDoctor;

public interface FocusCountDoctorReadMapper extends BaseReadMapper<FocusCountDoctor> {

    int getFocusCount(String doctorId);
}