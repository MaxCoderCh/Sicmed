package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseReadMapper;
import com.sicmed.register.entity.SicmedPatient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicmedPatientReadMapper extends BaseReadMapper<SicmedPatient>{
    @Override
    SicmedPatient selectById(String id);

    @Override
    List<SicmedPatient> selectByParams(SicmedPatient sicmedPatient);

    /**
     *  根据身份证查询患者信息
     */
    SicmedPatient selectByCard(String patientCard);


    /**
     *  根据患者编号查询患者信息
     */
    SicmedPatient selectByNumber(String patientNumber);
}