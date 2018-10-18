package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedRegisteredPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedRegisteredPriceWriteMapper extends BaseWriteMapper<SicmedRegisteredPrice>{

    @Override
    int insertSelective(SicmedRegisteredPrice sicmedRegisteredPrice);

    @Override
    int updateSelective(SicmedRegisteredPrice sicmedRegisteredPrice);

    @Override
    int deleteById(String id);
}