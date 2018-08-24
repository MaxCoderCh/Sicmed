package com.prostate.wallet.mapper.master;

import com.prostate.wallet.entity.ReceiptPayment;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptPaymentWriteMapper extends BaseWriteMapper<ReceiptPayment>{


    @Override
    int insertSelective(ReceiptPayment receiptPayment);

    @Override
    int updateSelective(ReceiptPayment receiptPayment);

    @Override
    int deleteById(String id);
}