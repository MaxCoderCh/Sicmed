package com.sicmed.register.mapper.slaver;

import com.sicmed.register.base.BaseReadMapper;
import com.sicmed.register.entity.SicmedRegistered;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicmedRegisteredReadMapper extends BaseReadMapper<SicmedRegistered>{


    SicmedRegistered selectByPrimaryKey(String id);



    @Override
    SicmedRegistered selectById(String id);

    @Override
    List<SicmedRegistered> selectByParams(SicmedRegistered sicmedRegistered);


    /**
     *  可以退号的患者（包含正常挂号合已经改号的患者）
     */
    List<SicmedRegistered> selectByParams2(SicmedRegistered sicmedRegistered);



    /**
     *  挂号记录
     */
    List<SicmedRegistered> registerRecord(SicmedRegistered sicmedRegistered);
    /**
     *  挂号记录
     */
    List<SicmedRegistered> exchangeRecord(SicmedRegistered sicmedRegistered);
    /**
     *  挂号记录
     */
    List<SicmedRegistered> returnRecord(SicmedRegistered sicmedRegistered);

    /**
     *  所有的待缴费
     */
    List<SicmedRegistered> patientNotPay(SicmedRegistered sicmedRegistered);
}