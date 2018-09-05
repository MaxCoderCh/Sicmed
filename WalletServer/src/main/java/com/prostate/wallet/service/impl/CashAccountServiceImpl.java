package com.prostate.wallet.service.impl;

import com.prostate.wallet.entity.CashAccount;
import com.prostate.wallet.mapper.master.CashAccountWriteMapper;
import com.prostate.wallet.mapper.slaver.CashAccountReadMapper;
import com.prostate.wallet.service.CashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashAccountServiceImpl implements CashAccountService {

    @Autowired
    private CashAccountWriteMapper cashAccountWriteMapper;
    @Autowired
    private CashAccountReadMapper cashAccountReadMapper;

    @Override
    public int insertSelective(CashAccount cashAccount) {
        return cashAccountWriteMapper.insertSelective(cashAccount);
    }

    @Override
    public int updateSelective(CashAccount cashAccount) {
        return cashAccountWriteMapper.updateSelective(cashAccount);
    }

    @Override
    public CashAccount selectById(String id) {
        return cashAccountReadMapper.selectById(id);
    }

    @Override
    public List<CashAccount> selectByParams(CashAccount cashAccount) {
        return cashAccountReadMapper.selectByParams(cashAccount);
    }

    @Override
    public int deleteById(String id) {
        return cashAccountWriteMapper.deleteById(id);
    }

    @Override
    public CashAccount getByParams(CashAccount cashAccount) {
        return cashAccountReadMapper.getByParams(cashAccount);
    }
}
