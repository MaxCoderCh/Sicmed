package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedRegisteredType;

public interface SicmedRegisteredTypeWriteMapper  extends BaseWriteMapper<SicmedRegisteredType>{
    @Override
    int insertSelective(SicmedRegisteredType sicmedRegisteredType);

    @Override
    int updateSelective(SicmedRegisteredType sicmedRegisteredType);

    @Override
    int deleteById(String id);
}