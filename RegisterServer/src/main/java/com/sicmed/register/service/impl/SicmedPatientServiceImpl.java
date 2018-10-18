package com.sicmed.register.service.impl;

import com.sicmed.register.entity.SicmedPatient;
import com.sicmed.register.mapper.master.SicmedPatientWriteMapper;
import com.sicmed.register.mapper.slaver.SicmedPatientReadMapper;
import com.sicmed.register.service.SicmedPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 15:02
 * @Todo:
 */
@Service
public class SicmedPatientServiceImpl  implements SicmedPatientService {


   @Autowired
   private SicmedPatientWriteMapper sicmedPatientWriteMapper;
   @Autowired
   private SicmedPatientReadMapper sicmedPatientReadMapper;


    @Override
    public int insertSelective(SicmedPatient sicmedPatient) {
        return sicmedPatientWriteMapper.insertSelective(sicmedPatient);
    }

    @Override
    public int updateSelective(SicmedPatient sicmedPatient) {
        return 0;
    }

    @Override
    public SicmedPatient selectById(String id) {
        return sicmedPatientReadMapper.selectById(id);
    }

    @Override
    public List<SicmedPatient> selectByParams(SicmedPatient sicmedPatient) {
        return sicmedPatientReadMapper.selectByParams(sicmedPatient);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public SicmedPatient selectByCard(String patientCard) {
        return sicmedPatientReadMapper.selectByCard(patientCard);
    }

    @Override
    public SicmedPatient selectByNumber(String patientNumber) {
        return sicmedPatientReadMapper.selectByNumber(patientNumber);
    }
}
