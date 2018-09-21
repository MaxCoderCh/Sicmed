package com.prostate.pay.controller;

import com.prostate.common.base.BaseController;
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
    public Map orderPay(String orderId, String orderPrice,String openid) {

        Map<String, String> data = new HashMap();
        data.put("body", "栗子医学-问诊费用支付");
        data.put("out_trade_no", orderId);
        data.put("fee_type", "CNY");
        data.put("total_fee", orderPrice);
        data.put("notify_url", "http://www.qlxlm.com/api-order/order/notify/paymentSuccess");
        data.put("trade_type", "JSAPI");
        data.put("product_id", orderId);
        data.put("device_info", "WEB");
        data.put("openid", openid);

        Map<String, String> map = weChatPayService.unifiedOrder(data);

        if (map.get("return_code").equals("SUCCESS")) {
            Map<String, String> signMap = new HashMap<>();
            signMap.put("appId", map.get("appid"));
            signMap.put("package", "prepay_id=" + map.get("prepay_id"));
            signMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestamp()));
            signMap.put("nonceStr", map.get("nonce_str"));
            signMap.put("signType", "MD5");
            try {
                signMap.put("sign", WXPayUtil.generateSignature(signMap, myWeChatPayConfig.getKey()));
            } catch (Exception e) {
                log.error("订单 签名失败");
                return insertFailedResponse();
            }
            return insertSuccseeResponse(signMap);
        }

        return insertFailedResponse();
    }


    /**
     * 退款
     *
     */
    @PostMapping(value = "refund")
    public String refund(String transactionId, String orderPrice){

        Map<String, String> data = new HashMap();
        data.put("transaction_id", transactionId);
        data.put("out_refund_no", transactionId);
        data.put("total_fee", orderPrice);
        data.put("refund_fee", orderPrice);


        Map<String, String> map = null;
        try {
            map = weChatPayService.refund(data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("map" + map.toString());

        if (map.get("return_code").equals("SUCCESS")) {
            return "SUCCESS";
        }
        log.error("退款失败");
        return "ERROR";
    }
}
