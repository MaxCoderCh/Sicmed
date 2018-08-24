package com.prostate.wallet.mapper.slaver;

import com.prostate.wallet.entity.ReceiptPayment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptPaymentReadMapper extends BaseReadMapper<ReceiptPayment>{

    @Override
    ReceiptPayment selectById(String id);

    @Override
    List<ReceiptPayment> selectByParams(ReceiptPayment e);

    List<ReceiptPayment> list(ReceiptPayment e);

    //统计数据量
    int  count(String walletId);
}