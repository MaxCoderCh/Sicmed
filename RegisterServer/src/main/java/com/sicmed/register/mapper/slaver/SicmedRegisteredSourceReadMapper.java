package com.sicmed.register.mapper.slaver;

import com.sicmed.register.entity.SicmedRegisteredSource;

public interface SicmedRegisteredSourceReadMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredSource record);

    int insertSelective(SicmedRegisteredSource record);

    SicmedRegisteredSource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredSource record);

    int updateByPrimaryKey(SicmedRegisteredSource record);
}