package com.prostate.order.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface FeignService {

    List<LinkedHashMap<String, String>> RecordServerGetPatientListById(String userId) throws Exception;

    List<LinkedHashMap<String, String>> RecordServerGetPatientListByIds(String patientIds) throws Exception;

    void ThirdServerSendPaymentSuccess(String phoneNumber) throws Exception;

    void ThirdServerSendInquiryEndToPatient(String phoneNumber) throws Exception;

    void ThirdServerSendInquiryRefund(String phoneNumber) throws Exception;

    void ThirdServerSendProveSuccess(String phoneNumber) throws Exception;

    void ThirdServerSendProveFailed(String phoneNumber) throws Exception;

    void ThirdServerSendBalanceForCash(String phoneNumber) throws Exception;

    Map<String,String> ThirdServerOrderPay(String orderId,String orderPrice,String openid) throws Exception;

    void ThirdServerPushPaymentSuccessToWechat(String openid, String orderId, String goodsInfo, String orderPrice) throws Exception;

    void ThirdServerPushOrderFailedToWechat(String openid, String refundInfo, String refundPrice) throws Exception;

    void ThirdServerRefund(String transactionId, String orderPrice) throws Exception;

    String UserServerGetOpenid(String userId) throws Exception;

    void WalletServerCashOrder(String orderId, String doctorId, String orderPrice) throws Exception;
}
