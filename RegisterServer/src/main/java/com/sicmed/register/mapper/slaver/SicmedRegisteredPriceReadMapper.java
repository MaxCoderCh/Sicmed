package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseReadMapper;
import com.sicmed.register.entity.SicmedRegisteredPrice;

import java.util.List;

public interface SicmedRegisteredPriceReadMapper extends BaseReadMapper<SicmedRegisteredPrice>{
    @Override
    SicmedRegisteredPrice selectById(String id);

    @Override
    List<SicmedRegisteredPrice> selectByParams(SicmedRegisteredPrice sicmedRegisteredPrice);
}