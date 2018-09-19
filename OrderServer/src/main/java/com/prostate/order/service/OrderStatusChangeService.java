package com.prostate.order.service;

public interface OrderStatusChangeService {

    void notifyThirdServerPushPaymentSuccess(String openid, String transactionId, String goodsInfo, String orderPrice) throws Exception;

    void notifyThirdServerRefund(String transactionId, String orderPrice) throws Exception;

    String notifyUserServerGetOpenid(String userId) throws Exception;

    void notifyThirdServerPushRefundSuccess(String openid,String redundInfo, String orderPrice) throws Exception;
}
