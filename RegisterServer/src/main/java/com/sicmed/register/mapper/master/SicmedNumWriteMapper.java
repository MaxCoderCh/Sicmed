package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedNum;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedNumWriteMapper extends BaseWriteMapper<SicmedNum>{
    @Override
    int insertSelective(SicmedNum sicmedNum);

    @Override
    int updateSelective(SicmedNum sicmedNum);

    @Override
    int deleteById(String id);
}