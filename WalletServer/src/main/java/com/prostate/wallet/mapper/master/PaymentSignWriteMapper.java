package com.prostate.wallet.mapper.master;

import com.prostate.wallet.entity.PaymentSign;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSignWriteMapper extends BaseWriteMapper<PaymentSign>{
    @Override
    int insertSelective(PaymentSign paymentSign);

    @Override
    int updateSelective(PaymentSign paymentSign);

    @Override
    int deleteById(String id);
}