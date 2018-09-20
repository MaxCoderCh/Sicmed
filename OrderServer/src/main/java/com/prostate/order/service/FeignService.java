package com.prostate.order.service;

public interface FeignService {

    void RecordServerGetPatientListById(String userId) throws Exception;

    void RecordServerGetPatientListByIds(String patientIds) throws Exception;

    void ThirdServerSendPaymentSuccess(String phoneNumber) throws Exception;

    void ThirdServerSendInquiryEndToPatient(String phoneNumber) throws Exception;

    void ThirdServerSendInquiryRefund(String phoneNumber) throws Exception;

    void ThirdServerSendProveSuccess(String phoneNumber) throws Exception;

    void ThirdServerSendProveFailed(String phoneNumber) throws Exception;

    void ThirdServerSendBalanceForCash(String phoneNumber) throws Exception;

    void ThirdServerPushPaymentSuccessToWechat(String openid, String orderId, String goodsInfo, String orderPrice) throws Exception;

    void ThirdServerPushOrderFailedToWechat(String openid, String refundInfo, String refundPrice) throws Exception;

    void ThirdServerRefund(String transactionId, String orderPrice) throws Exception;

    String UserServerGetOpenid(String userId) throws Exception;

    void WalletServerCashOrder(String orderId, String doctorId, String orderPrice) throws Exception;
}
