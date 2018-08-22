package com.prostate.order.mapper.master;

import com.prostate.order.entity.OrderInquiry;

public interface OrderInquiryWriteMapper extends BaseWriteMapper<OrderInquiry> {

    int falseDeleteById(String orderId);
}