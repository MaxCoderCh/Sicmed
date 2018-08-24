package com.prostate.wallet.mapper.slaver;

import com.prostate.wallet.entity.DealRecord;

public interface DealRecordReadMapper extends BaseReadMapper<DealRecord>{

    int checkByOrder(DealRecord dealRecord);
}