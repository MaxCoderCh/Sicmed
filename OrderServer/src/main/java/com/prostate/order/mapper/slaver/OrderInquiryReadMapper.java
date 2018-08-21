package com.prostate.order.mapper.slaver;

import com.prostate.order.beans.OrderInquiryBean;
import com.prostate.order.entity.OrderInquiry;

import java.util.List;

public interface OrderInquiryReadMapper extends BaseReadMapper<OrderInquiry>{

    List<OrderInquiry> queryByParams(OrderInquiry orderInquiry);
}