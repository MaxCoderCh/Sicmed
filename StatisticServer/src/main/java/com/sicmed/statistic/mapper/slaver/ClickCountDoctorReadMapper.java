package com.sicmed.statistic.mapper.slaver;


import com.sicmed.statistic.entity.ClickCountDoctor;

public interface ClickCountDoctorReadMapper extends BaseReadMapper<ClickCountDoctor> {

    int getClickCount(String doctorId);
}