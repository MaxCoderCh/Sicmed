package com.prostate.order.controller;

import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 转诊订单
 */
@Slf4j
@RestController
@RequestMapping(value = "order/turn")
public class OrderTurnController extends BaseController {

    @Autowired
    private OrderInquiryService orderInquiryService;

    /**
     * 创建订单
     */
    @PostMapping(value = "createOrder")
    public Map createOrder(String orderId, String doctorId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setDoctorId(doctorId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderType(OrderConstants.TURN_TYPE);

        orderInquiryService.updateSelective(orderInquiry);
        return null;
    }

    /**
     * 查询待接收 转诊订单
     */
    @GetMapping(value = "getUnReceiveTurnOrderList")
    public Map getUnReceiveTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderStatus(OrderConstants.TURN_TYPE);
        orderInquiry.setDoctorId(getToken());

        orderInquiryService.selectByParams(orderInquiry);

        return null;

    }
}
