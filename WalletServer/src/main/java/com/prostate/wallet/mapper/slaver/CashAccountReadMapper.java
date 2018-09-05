package com.prostate.wallet.mapper.slaver;

import com.prostate.wallet.entity.CashAccount;

public interface CashAccountReadMapper extends BaseReadMapper<CashAccount> {

    CashAccount getByParams(CashAccount cashAccount);
}