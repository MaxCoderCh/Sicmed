package com.sicmed.register.service;

import com.sicmed.register.base.BaseService;
import com.sicmed.register.entity.SicmedRegistered;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 9:39
 * @Todo:  患者挂号服务
 */

public interface SicmedRegisteredService  extends BaseService<SicmedRegistered> {

    @Override
    int insertSelective(SicmedRegistered sicmedRegistered);

    @Override
    int updateSelective(SicmedRegistered sicmedRegistered);

    @Override
    SicmedRegistered selectById(String id);

    @Override
    List<SicmedRegistered> selectByParams(SicmedRegistered sicmedRegistered);

    /**
     *  可以退号的患者
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

    @Override
    int deleteById(String id);

    /**
     *  所有的代缴费
     */
    List<SicmedRegistered> patientNotPay(SicmedRegistered sicmedRegistered);

    /**
     *   统计挂号
     */
}
