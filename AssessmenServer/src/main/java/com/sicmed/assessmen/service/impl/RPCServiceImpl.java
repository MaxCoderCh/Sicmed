package com.sicmed.assessmen.service.impl;

import com.sicmed.assessmen.feignService.*;
import com.sicmed.assessmen.service.RPCService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class RPCServiceImpl implements RPCService {

    private final DoctorServer doctorServer;
    private final FileServer fileServer;
    private final OrderServer orderServer;
    private final RecordServer recordServer;
    private final StatisticServer statisticServer;
    private final ThirdServer thirdServer;
    private final UserServer userServer;
    private final WalletServer walletServer;


    @Autowired
    public RPCServiceImpl(DoctorServer doctorServer, FileServer fileServer, OrderServer orderServer, RecordServer recordServer, StatisticServer statisticServer, ThirdServer thirdServer, UserServer userServer, WalletServer walletServer) {
        this.doctorServer = doctorServer;
        this.fileServer = fileServer;
        this.orderServer = orderServer;
        this.recordServer = recordServer;
        this.statisticServer = statisticServer;
        this.thirdServer = thirdServer;
        this.userServer = userServer;
        this.walletServer = walletServer;
    }

    //调用订单服务 修改订单状态
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> notifyOrderServerOrderDoneSuccess(String orderId) throws Exception {
        Map<String, Object> serverResult = orderServer.orderDoneSuccess(orderId);
        String resultCode = String.valueOf(serverResult.get("code"));
        if (resultCode.equals("20000")) {
            log.info("ORDER_NO:" + orderId + "---调用订单服务 修改订单状态 成功!");
            Map<String, String> orderInquiry = (Map<String, String>) serverResult.get("result");
            return orderInquiry;
        } else {
            log.error("ORDER_NO:" + orderId + "---调用订单服务 修改订单状态 失败!");
            throw new Exception("RPC调用异常");
        }
    }

    //调用钱包服务 订单收益 存入医生零钱
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyWalletServerAddOrderIncome(String orderId,String doctorId,String orderPrice) throws Exception {
        String serverResult = walletServer.addOrderIncome(orderId,doctorId,orderPrice);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + orderId + "---调用钱包服务 订单收益 存入医生零钱 失败!");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + orderId + "---调用钱包服务 订单收益 存入医生零钱成功!");
        }
    }


    //调用档案服务 建立医生-患者关系
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyRecordServerAddUserPatientByOrder(String userId, String patientId, String orderType) throws Exception {
        String serverResult = recordServer.addUserPatientByOrder(userId,patientId,orderType);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + "---调用档案服务 建立医生-患者关系 失败!");
            throw new Exception("RPC调用异常");

        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + "---调用 档案服务 建立医生-患者关系 成功!");
        }
    }


    //调用统计服务 修改总收益
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyStatisticServerAddTotleIncome(String userId, String orderPrice) throws Exception {
        String serverResult = statisticServer.addTotleIncome(userId,orderPrice);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + "---调用统计服务 修改总收益 失败!");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + "---调用统计服务 修改总收益 成功!");
        }
    }

    //调用订单服务 查询订单信息
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> notifyOrderServerGetOrder(String orderId) throws Exception {
        Map<String, Object> serverResult = orderServer.getOrder(orderId);
        String resultCode = String.valueOf(serverResult.get("code"));
        if ("20000".equals(resultCode)) {
            log.info("ORDER_NO:" + orderId + "---调用订单服务 查询订单信息 成功!");
            Map<String, String> orderInquiry = (Map<String, String>) serverResult.get("result");
            return orderInquiry;
        } else {
            log.error("ORDER_NO:" + orderId + "---调用订单服务 查询订单信息 失败!");
            throw new Exception("RPC调用异常");
        }
    }


    //调用UserServer服务 查询医生手机号
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String notifyUserServerGetPhoneNumber(String userId) throws Exception {
        String serverResult = userServer.getPhoneNumber(userId);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + "---调用UserServer服务 查询医生手机号 失败!");
            throw new Exception("RPC调用异常");
        } else {
            log.info("ORDER_NO:" + "---用UserServer服务 查询医生手机号 成功!");
            return serverResult;
        }
    }

    //调用ThirdServer 短信提醒医生订单结束
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerSendInquiryEndToDoctor(String phoneNumber) throws Exception {
        String serverResult = thirdServer.sendInquiryEndToDoctor(phoneNumber);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + "---调用ThirdServer 短信提醒医生订单结束 失败!");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + "---调用ThirdServer 短信提醒医生订单结束 成功!");
        }
    }


    //调用UserServer服务 查询weChat用户openid
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String notifyUserServerGetOpenid(String userId) throws Exception {
        String serverResult = userServer.getOpenid(userId);
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + "---调用UserServer服务 查询weChat用户openid 失败!");
            throw new Exception("RPC调用异常");
        } else {
            log.info("ORDER_NO:" + "---调用UserServer服务 查询weChat用户openid 成功!");
            return serverResult;
        }
    }

    //调用ThirdServer 提醒公众号 订单结束
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerPushOrderSuccessToWechat(String openid, String transactionId) throws Exception {
        String serverResult = thirdServer.pushOrderSuccessToWechat(openid, transactionId, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + transactionId + "---调用ThirdServer 提醒公众号 订单结束 失败!");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + transactionId + "---调用ThirdServer 提醒公众号 订单结束 成功!");
        }
    }
}
