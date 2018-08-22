package com.prostate.order.controller;

import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "order/notify")
public class OrderNotifyController extends BaseController {
    @Autowired
    private OrderInquiryService orderInquiryService;

    /**
     * 支付成功 通知 接口
     */
    @PostMapping(value = "paymentSuccess")
    public Map paymentSuccess() {


        return null;
    }

    /**
     * 订单完成 通知 接口
     */
    @PostMapping(value = "orderDoneSuccess")
    public String orderDoneSuccess(String orderId) {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        int i = orderInquiryService.updateSelective(orderInquiry);
        if (i > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

}
