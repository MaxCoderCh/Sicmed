package com.prostate.order.mapper.slaver;

import com.prostate.order.entity.OrderInquiry;

import java.util.List;

public interface OrderInquiryReadMapper extends BaseReadMapper<OrderInquiry>{

    List<OrderInquiry> queryByParams(OrderInquiry orderInquiry);

    String countByParams(OrderInquiry orderInquiry);

    List<OrderInquiry> queryProgressByParams(OrderInquiry orderInquiry);

    List<OrderInquiry> queryDoneByParams(OrderInquiry orderInquiry);
}