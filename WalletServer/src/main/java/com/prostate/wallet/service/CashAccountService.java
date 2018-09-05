package com.prostate.wallet.service;

import com.prostate.wallet.entity.CashAccount;

public interface CashAccountService extends BaseService<CashAccount> {
    CashAccount getByParams(CashAccount cashAccount);
}
