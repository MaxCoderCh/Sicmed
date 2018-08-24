package com.prostate.wallet.service;

import com.prostate.wallet.entity.DealRecord;

public interface DealRecordService extends BaseService<DealRecord> {
    int checkByOrder(DealRecord dealRecord);
}
