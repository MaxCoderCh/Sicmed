package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedRegistered;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedRegisteredWriteMapper extends BaseWriteMapper<SicmedRegistered>{
    @Override
    int insertSelective(SicmedRegistered sicmedRegistered);

    @Override
    int updateSelective(SicmedRegistered sicmedRegistered);

    @Override
    int deleteById(String id);
}