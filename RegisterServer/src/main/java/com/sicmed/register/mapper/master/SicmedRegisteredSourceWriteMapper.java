package com.sicmed.register.mapper.master;

import com.sicmed.register.entity.SicmedRegisteredSource;

public interface SicmedRegisteredSourceWriteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SicmedRegisteredSource record);

    int insertSelective(SicmedRegisteredSource record);

    SicmedRegisteredSource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SicmedRegisteredSource record);

    int updateByPrimaryKey(SicmedRegisteredSource record);
}