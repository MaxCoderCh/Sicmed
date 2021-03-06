package com.prostate.order.service.impl;

import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.mapper.master.OrderInquiryWriteMapper;
import com.prostate.order.mapper.slaver.OrderInquiryReadMapper;
import com.prostate.order.service.OrderInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInquiryServiceImpl implements OrderInquiryService {
    @Autowired
    private OrderInquiryReadMapper orderInquiryReadMapper;
    @Autowired
    private OrderInquiryWriteMapper orderInquiryWriteMapper;


    @Override
    public int insertSelective(OrderInquiry orderInquiry) {
        return orderInquiryWriteMapper.insertSelective(orderInquiry);
    }

    @Override
    public int updateSelective(OrderInquiry orderInquiry) {
        return orderInquiryWriteMapper.updateSelective(orderInquiry);
    }

    @Override
    public OrderInquiry selectById(String id) {
        return orderInquiryReadMapper.selectById(id);
    }


    @Override
    public List<OrderInquiry> selectByParams(OrderInquiry orderInquiry) {
        return orderInquiryReadMapper.selectByParams(orderInquiry);
    }

    @Override
    public int deleteById(String id) {
        return orderInquiryWriteMapper.deleteById(id);
    }

    @Override
    public List<OrderInquiry> queryByParams(OrderInquiry orderInquiry) {
        return orderInquiryReadMapper.queryByParams(orderInquiry);
    }

    @Override
    public int falseDeleteById(String orderId) {
        return orderInquiryWriteMapper.falseDeleteById(orderId);
    }

    @Override
    public int updateOrderSuccess(String orderId) {
        return orderInquiryWriteMapper.updateOrderSuccess(orderId);
    }

    @Override
    public String countByParams(OrderInquiry orderInquiry) {
        return orderInquiryReadMapper.countByParams(orderInquiry);
    }

    @Override
    public List<OrderInquiry> queryProgressByParams(OrderInquiry orderInquiry) {
        return orderInquiryReadMapper.queryProgressByParams(orderInquiry);
    }

    @Override
    public List<OrderInquiry> queryDoneByParams(OrderInquiry orderInquiry) {
        return orderInquiryReadMapper.queryDoneByParams(orderInquiry);
    }
}
