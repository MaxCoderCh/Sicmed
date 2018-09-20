package com.sicmed.assessmen.service;


import java.util.Map;

public interface FeignService {

    Map<String, String> notifyOrderServerOrderDoneSuccess(String orderId) throws Exception;

    void notifyWalletServerAddOrderIncome(String orderId,String doctorId,String orderPrice) throws Exception;

    void notifyRecordServerAddUserPatientByOrder(String userId, String patientId, String orderType) throws Exception;

    void notifyStatisticServerAddTotleIncome(String userId, String orderPrice) throws Exception;

    Map<String, String> notifyOrderServerGetOrder(String orderId) throws Exception;

    String notifyUserServerGetPhoneNumber(String userId) throws Exception;

    void notifyThirdServerSendInquiryEndToDoctor(String phoneNumber) throws Exception;

    String notifyUserServerGetOpenid(String userId) throws Exception;

    void notifyThirdServerPushOrderSuccessToWechat(String openid,String transactionId) throws Exception;

}
