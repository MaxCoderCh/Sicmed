package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedRegistration;

public interface SicmedRegistrationReadMapper extends BaseWriteMapper<SicmedRegistration>{
    @Override
    int insertSelective(SicmedRegistration sicmedRegistration);

    @Override
    int updateSelective(SicmedRegistration sicmedRegistration);

    @Override
    int deleteById(String id);
}