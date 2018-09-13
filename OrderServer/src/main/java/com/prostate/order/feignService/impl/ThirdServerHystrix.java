package com.prostate.order.feignService.impl;

import com.prostate.order.feignService.ThirdServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThirdServerHystrix extends BaseServerHystrix implements ThirdServer {



    @Override
    public Map<String, Object> sendPaymentSuccess(String phoneNumber) {
        return resultMap;
    }

    @Override
    public Map<String, Object> sendInquiryEndToPatient(String phoneNumber) {
        return resultMap;
    }

    @Override
    public Map<String, Object> sendInquiryRefund(String phoneNumber) {
        return resultMap;
    }

    @Override
    public Map<String, Object> sendProveSuccess(String phoneNumber) {
        return resultMap;
    }

    @Override
    public Map<String, Object> sendProveFailed(String phoneNumber) {
        return resultMap;
    }

    @Override
    public Map<String, Object> sendBalanceForCash(String phoneNumber) {
        return resultMap;
    }

    @Override
    public String pushPaymentSuccessToWechat(String openid, String orderId, String goodsInfo, String orderPrice) {
        return "ERROR";
    }

    @Override
    public String pushOrderFailedToWechat(String openid, String refundInfo, String refundPrice) {
        return "ERROR";
    }

    @Override
    public String refund(String orderId) {
        return "ERROR";
    }
}
