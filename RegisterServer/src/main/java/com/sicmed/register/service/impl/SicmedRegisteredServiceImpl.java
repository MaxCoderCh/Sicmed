package com.sicmed.register.service.impl;

import com.sicmed.register.entity.SicmedRegistered;
import com.sicmed.register.mapper.master.SicmedRegisteredWriteMapper;
import com.sicmed.register.mapper.slaver.SicmedRegisteredReadMapper;
import com.sicmed.register.service.SicmedRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 9:40
 * @Todo:
 */
@Service
public class SicmedRegisteredServiceImpl  implements SicmedRegisteredService {


    @Autowired
    private SicmedRegisteredReadMapper sicmedRegisteredReadMapper;
    @Autowired
    private SicmedRegisteredWriteMapper sicmedRegisteredWriteMapper;


    @Override
    public int insertSelective(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredWriteMapper.insertSelective(sicmedRegistered);
    }

    @Override
    public int updateSelective(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredWriteMapper.updateSelective(sicmedRegistered);
    }

    @Override
    public SicmedRegistered selectById(String id) {
        return sicmedRegisteredReadMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SicmedRegistered> selectByParams(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.selectByParams(sicmedRegistered);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<SicmedRegistered> selectByParams2(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.selectByParams2(sicmedRegistered);
    }

    @Override
    public List<SicmedRegistered> registerRecord(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.registerRecord(sicmedRegistered);
    }

    @Override
    public List<SicmedRegistered> exchangeRecord(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.exchangeRecord(sicmedRegistered);
    }

    @Override
    public List<SicmedRegistered> returnRecord(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.returnRecord(sicmedRegistered);
    }

    @Override
    public List<SicmedRegistered> patientNotPay(SicmedRegistered sicmedRegistered) {
        return sicmedRegisteredReadMapper.patientNotPay(sicmedRegistered);
    }
}
