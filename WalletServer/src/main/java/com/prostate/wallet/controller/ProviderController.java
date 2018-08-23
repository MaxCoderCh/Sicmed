package com.prostate.wallet.controller;

import com.prostate.wallet.entity.ReceiptPayment;
import com.prostate.wallet.feignService.OrderServer;
import com.prostate.wallet.service.DoctorWalletService;
import com.prostate.wallet.service.ReceiptPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "")
public class ProviderController extends BaseController{
    @Autowired
    private DoctorWalletService doctorWalletService;

    @Autowired
    private ReceiptPaymentService receiptPaymentService;

    @Autowired
    private OrderServer orderServer;

    @PostMapping(value = "addOrderIncome")
    public Map addOrderIncome(String orderId){
        //查询订单 信息
        Map<String, Object> orderResultMap = orderServer.getOrder(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");
        orderMap.get("orderPrice");
        orderMap.get("id");

        ReceiptPayment receiptPayment = new ReceiptPayment();

        return null;
    }

}

