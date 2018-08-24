package com.prostate.wallet.service.impl;

import com.prostate.wallet.entity.DealRecord;
import com.prostate.wallet.mapper.master.DealRecordWriteMapper;
import com.prostate.wallet.mapper.slaver.DealRecordReadMapper;
import com.prostate.wallet.service.DealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealRecordServiceImpl implements DealRecordService {

    @Autowired
    private DealRecordWriteMapper dealRecordWriteMapper;

    @Autowired
    private DealRecordReadMapper dealRecordReadMapper;

    @Override
    public int insertSelective(DealRecord dealRecord) {
        return dealRecordWriteMapper.insertSelective(dealRecord);
    }

    @Override
    public int updateSelective(DealRecord dealRecord) {
        return dealRecordWriteMapper.updateSelective(dealRecord);
    }

    @Override
    public DealRecord selectById(String id) {
        return dealRecordReadMapper.selectById(id);
    }

    @Override
    public List<DealRecord> selectByParams(DealRecord dealRecord) {
        return dealRecordReadMapper.selectByParams(dealRecord);
    }

    @Override
    public int deleteById(String id) {
        return dealRecordWriteMapper.deleteById(id);
    }

    @Override
    public int checkByOrder(DealRecord dealRecord) {
        return dealRecordReadMapper.checkByOrder(dealRecord);
    }
}
