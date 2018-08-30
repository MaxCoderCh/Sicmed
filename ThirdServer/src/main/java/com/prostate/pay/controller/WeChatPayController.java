package com.prostate.pay.controller;

import com.prostate.common.base.BaseController;
import com.prostate.feignService.OrderServer;
import com.prostate.pay.entity.UnifiedOrderEntity;
import com.prostate.pay.service.WeChatPayService;
import com.prostate.pay.wxpay.sdk.MyWeChatPayConfig;
import com.prostate.pay.wxpay.sdk.WXPayUtil;
import com.prostate.redis.RedisSerive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "pay/weChat")
public class WeChatPayController extends BaseController {

    @Autowired
    private WeChatPayService weChatPayService;


    @Autowired
    private OrderServer orderServer;

    @Autowired
    private RedisSerive redisSerive;


    @Autowired
    private MyWeChatPayConfig myWeChatPayConfig;

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
    public Map orderPay(String orderId) throws Exception {

        Map<String, Object> reMap = orderServer.getOrder(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) reMap.get("result");

        log.info("orderMap" + orderMap.toString());

        Map<String, String> data = new HashMap();
        data.put("body", "栗子医学-问诊费用支付");
        data.put("out_trade_no", orderId);
        data.put("fee_type", "CNY");
        data.put("total_fee", orderMap.get("orderPrice").toString());
        data.put("notify_url", "http://www.yilaiyiwang.com/api-order/order/notify/paymentSuccess");
        data.put("trade_type", "JSAPI");
        data.put("product_id", orderId);
        data.put("device_info", "WEB");
        data.put("openid", redisSerive.getOpenid());
        log.info("data" + data.toString());

        Map<String, String> map = weChatPayService.unifiedOrder(data);
        log.info("map" + map.toString());

        if (map.get("return_code").equals("SUCCESS")) {
            Map<String, String> signMap = new HashMap<>();
            signMap.put("appId", map.get("appid"));
            signMap.put("package", "prepay_id=" + map.get("prepay_id"));
            signMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
            signMap.put("nonceStr", map.get("nonce_str"));
            signMap.put("signType", "MD5");
            signMap.put("sign", WXPayUtil.generateSignature(signMap, myWeChatPayConfig.getKey()));
            return insertSuccseeResponse(signMap);
        }

        return insertFailedResponse();
    }
}
