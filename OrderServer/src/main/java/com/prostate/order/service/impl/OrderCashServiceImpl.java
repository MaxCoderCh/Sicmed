package com.prostate.order.service.impl;

import com.prostate.order.entity.OrderCash;
import com.prostate.order.mapper.master.OrderCashWriteMapper;
import com.prostate.order.mapper.slaver.OrderCashReadMapper;
import com.prostate.order.service.OrderCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCashServiceImpl implements OrderCashService {

    @Autowired
    private OrderCashWriteMapper orderCashWriteMapper;
    @Autowired
    private OrderCashReadMapper orderCashReadMapper;

    @Override
    public int insertSelective(OrderCash orderCash) {
        return orderCashWriteMapper.insertSelective(orderCash);
    }

    @Override
    public int updateSelective(OrderCash orderCash) {
        return orderCashWriteMapper.updateSelective(orderCash);
    }

    @Override
    public OrderCash selectById(String id) {
        return orderCashReadMapper.selectById(id);
    }

    @Override
    public List<OrderCash> selectByParams(OrderCash orderCash) {
        return orderCashReadMapper.selectByParams(orderCash);
    }

    @Override
    public int deleteById(String id) {
        return orderCashWriteMapper.deleteById(id);
    }
}
