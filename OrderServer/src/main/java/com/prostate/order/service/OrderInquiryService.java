package com.prostate.order.service;

import com.prostate.order.entity.OrderInquiry;

import java.util.List;


public interface OrderInquiryService extends BaseService<OrderInquiry> {
    List<OrderInquiry> queryByParams(OrderInquiry orderInquiry);

    int falseDeleteById(String orderId);

    int updateOrderSuccess(String orderId);
}
