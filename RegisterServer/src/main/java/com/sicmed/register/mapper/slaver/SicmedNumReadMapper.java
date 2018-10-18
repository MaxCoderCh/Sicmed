package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseReadMapper;
import com.sicmed.register.entity.SicmedNum;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicmedNumReadMapper   extends BaseReadMapper<SicmedNum>{
    @Override
    SicmedNum selectById(String id);

    @Override
    List<SicmedNum> selectByParams(SicmedNum sicmedNum);
}