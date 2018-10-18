package com.sicmed.register.mapper.master;

import com.sicmed.register.base.BaseWriteMapper;
import com.sicmed.register.entity.SicmedPatient;
import org.springframework.stereotype.Repository;

@Repository
public interface SicmedPatientWriteMapper extends BaseWriteMapper<SicmedPatient>{
    @Override
    int insertSelective(SicmedPatient sicmedPatient);

    @Override
    int updateSelective(SicmedPatient sicmedPatient);

    @Override
    int deleteById(String id);
}