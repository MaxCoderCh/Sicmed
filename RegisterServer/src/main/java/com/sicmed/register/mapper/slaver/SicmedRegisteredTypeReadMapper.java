package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseReadMapper;
import com.sicmed.register.entity.SicmedRegisteredType;

import java.util.List;

public interface SicmedRegisteredTypeReadMapper extends BaseReadMapper<SicmedRegisteredType>{

    @Override
    SicmedRegisteredType selectById(String id);

    @Override
    List<SicmedRegisteredType> selectByParams(SicmedRegisteredType sicmedRegisteredType);
}