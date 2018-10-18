package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedRegistration;

public interface SicmedRegistrationWriteMapper   extends BaseWriteMapper<SicmedRegistration>{
    @Override
    int insertSelective(SicmedRegistration sicmedRegistration);

    @Override
    int updateSelective(SicmedRegistration sicmedRegistration);

    @Override
    int deleteById(String id);
}