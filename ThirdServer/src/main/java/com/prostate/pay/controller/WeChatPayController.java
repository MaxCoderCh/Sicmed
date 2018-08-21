package com.prostate.pay.controller;

import com.prostate.common.base.BaseController;
import com.prostate.feignService.OrderServer;
import com.prostate.pay.entity.UnifiedOrderEntity;
import com.prostate.pay.service.WeChatPayService;
import com.prostate.pay.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "pay/weChat")
public class WeChatPayController extends BaseController {

    @Autowired
    private WeChatPayService weChatPayService;


    @Autowired
    private OrderServer orderServer;
    /**
     * 公众号支付
     *
     * @param unifiedOrderEntity
     * @return
     */
    @PostMapping(value = "unifiedOrder")
    public Map unifiedOrder(UnifiedOrderEntity unifiedOrderEntity) {

        Map<String, String> data = new HashMap();
//        data.put("body", "栗子医学-问诊费用支付);
//        data.put("out_trade_no", "2016090910595900000012");
//        data.put("device_info", "");
//        data.put("fee_type", "CNY");
//        data.put("total_fee", "1");
//        data.put("spbill_create_ip", "123.12.12.123");
//        data.put("notify_url", "http://www.example.com/wxpay/notify");
//        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
//        data.put("product_id", "12");
        data.put("body", unifiedOrderEntity.getBody());
        data.put("out_trade_no", unifiedOrderEntity.getOutTradeNo());
        data.put("device_info", unifiedOrderEntity.getDeviceInfo());
        data.put("fee_type", unifiedOrderEntity.getFeeType());
        data.put("total_fee", unifiedOrderEntity.getTotalFee());
        data.put("spbill_create_ip", unifiedOrderEntity.getSpBillCreateIp());
        data.put("notify_url", unifiedOrderEntity.getNotifyUrl());
        data.put("trade_type", unifiedOrderEntity.getTradeType());  // 此处指定为公众号支付
        data.put("product_id", unifiedOrderEntity.getProductId());

        return weChatPayService.unifiedOrder(data);
    }


    @PostMapping(value = "orderPay")
    public Map orderPay(String orderId) {

        Map<String, Object> reMap = orderServer.getOrder(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) reMap.get("result");

        Map<String, String> data = new HashMap();
        data.put("body", "栗子医学-问诊费用支付");
        data.put("out_trade_no", orderId);
        data.put("fee_type", "CNY");
        data.put("total_fee", orderMap.get("orderPrice").toString());
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");
        data.put("product_id", "orderId");

        Map<String, String> map = weChatPayService.unifiedOrder(data);
        if (map.get("result_code").equals("SUCCESS")) {
            map.remove("code_url");
            map.remove("trade_type");
            map.remove("return_msg");
            map.remove("result_code");
            map.remove("mch_id");
            map.remove("return_code");
            map.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
            return insertSuccseeResponse(map);
        }

        return insertFailedResponse();
    }
}
