package com.prostate.wallet.service.impl;

import com.prostate.wallet.entity.DoctorWallet;
import com.prostate.wallet.mapper.master.DoctorWalletWriteMapper;
import com.prostate.wallet.mapper.slaver.DoctorWalletReadMapper;
import com.prostate.wallet.service.DoctorWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: bian
 * @Date: 2018/7/17 13:50
 * @Todo:
 */
@Service
public class DoctorWalletServiceImpl implements DoctorWalletService {

    @Autowired
    private DoctorWalletWriteMapper doctorWalletWriteMapper;

    @Autowired
    private DoctorWalletReadMapper  doctorWalletReadMapper;


    @Override
    public int insertSelective(DoctorWallet e) {
        return doctorWalletWriteMapper.insertSelective(e);
    }

    @Override
    public DoctorWallet create(String userId) {
        DoctorWallet doctorWallet = new DoctorWallet();
        doctorWallet.setWalletBalance("0");
        doctorWallet.setCreateUser(userId);
        doctorWallet.setDoctorId(userId);
        doctorWallet.setId(userId);
        doctorWalletWriteMapper.insertSelective(doctorWallet);
        return doctorWallet;
    }

    @Override
    public int updateSelective(DoctorWallet e) {
        return doctorWalletWriteMapper.updateSelective(e);
    }

    @Override
    public DoctorWallet selectById(String id) {
        try {
            DoctorWallet doctorWallet  = doctorWalletReadMapper.selectById(id);
            return doctorWallet;
        }catch (Exception e){
            return new DoctorWallet();
        }
    }

    @Override
    public DoctorWallet selectByDoctorId(String doctorId) {
        return doctorWalletReadMapper.selectByDoctorId(doctorId);
    }

    @Override
    public List<DoctorWallet> selectByParams(DoctorWallet e) {
        return doctorWalletReadMapper.selectByParams(e);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
