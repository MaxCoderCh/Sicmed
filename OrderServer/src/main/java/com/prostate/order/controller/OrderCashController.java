package com.prostate.order.controller;

import com.prostate.order.entity.OrderCash;
import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.feignService.WalletServer;
import com.prostate.order.service.OrderCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "order/cash")
public class OrderCashController extends BaseController {

    @Autowired
    private OrderCashService orderCashService;

    @Autowired
    private WalletServer walletServer;
    /**
     * 添加 提现 订单
     */
    @PostMapping(value = "addOrderCash")
    public Map addOrderCash(String accountId, String orderPrice) {
        OrderCash orderCash = new OrderCash();

        orderCash.setAccountId(accountId);
        orderCash.setCreateUser(getToken());
        orderCash.setOrderType(OrderConstants.CASH_TYPE);
        orderCash.setOrderStatus(OrderConstants.NO_ACCEPTED);
        orderCash.setOrderDescription("订单描述");
        orderCash.setOrderNumber("ORDER_NUMBER");
        orderCash.setOrderPrice(orderPrice);

        int i = orderCashService.insertSelective(orderCash);
        if (i > 0) {
            walletServer.cashOrder(orderCash.getId());
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }
    /**
     * 2.查询提现订单
     */
    @PostMapping(value = "getOrderCash")
    public Map getOrderCash(String orderId) {

        OrderCash orderCash = orderCashService.selectById(orderId);
        if (orderCash == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(orderCash);
    }

}
