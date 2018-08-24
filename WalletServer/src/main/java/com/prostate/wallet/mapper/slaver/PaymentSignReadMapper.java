package com.prostate.wallet.mapper.slaver;

import com.prostate.wallet.entity.PaymentSign;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentSignReadMapper  extends BaseReadMapper<PaymentSign>{

    @Override
    PaymentSign selectById(String id);

    @Override
    List<PaymentSign> selectByParams(PaymentSign paymentSign);
}