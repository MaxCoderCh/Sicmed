package com.prostate.wallet.mapper.slaver;

import com.prostate.wallet.entity.DoctorWallet;

public interface DoctorWalletReadMapper extends BaseReadMapper<DoctorWallet> {


    DoctorWallet selectByDoctorId(String doctorId);

}