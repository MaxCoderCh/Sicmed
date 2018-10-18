package com.sicmed.register.service;

import com.sicmed.register.base.BaseService;
import com.sicmed.register.entity.SicmedPatient;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 14:46
 * @Todo:  患者信息服务
 */
public interface SicmedPatientService  extends BaseService<SicmedPatient> {

    @Override
    int insertSelective(SicmedPatient sicmedPatient);

    @Override
    int updateSelective(SicmedPatient sicmedPatient);

    @Override
    SicmedPatient selectById(String id);

    @Override
    List<SicmedPatient> selectByParams(SicmedPatient sicmedPatient);

    @Override
    int deleteById(String id);

    /**
     *  根据身份证查询患者信息
     */
    SicmedPatient selectByCard(String patientCard);

    /**
     *  根据患者编号查询患者信息
     */
    SicmedPatient selectByNumber(String patientNumber);
}
