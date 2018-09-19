package com.prostate.order.service.impl;

import com.prostate.order.feignService.RecordServer;
import com.prostate.order.feignService.ThirdServer;
import com.prostate.order.feignService.UserServer;
import com.prostate.order.feignService.WalletServer;
import com.prostate.order.service.OrderStatusChangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderStatusChangeServiceImpl implements OrderStatusChangeService {

    private final RecordServer recordServer;
    private final ThirdServer thirdServer;
    private final UserServer userServer;
    private final WalletServer walletServer;

    @Autowired
    public OrderStatusChangeServiceImpl(RecordServer recordServer, ThirdServer thirdServer, UserServer userServer, WalletServer walletServer) {
        this.recordServer = recordServer;
        this.thirdServer = thirdServer;
        this.userServer = userServer;
        this.walletServer = walletServer;
    }

    //调用 ThirdServer 支付成功模版消息推送服务
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerPushPaymentSuccess(String openid, String transactionId, String goodsInfo, String orderPrice) throws Exception {

        String serverResult = thirdServer.pushPaymentSuccessToWechat(openid, transactionId, goodsInfo, orderPrice);

        if (serverResult.equals("ERROR")) {
            log.error("ORDER_NO:" + transactionId + "---调用thirdServer 通知公众号 失败!");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("ORDER_NO:" + transactionId + "---调用thirdServer 通知公众号 成功!");
        }
    }


    //调用 ThirdServer 退款服务
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerRefund(String transactionId, String orderPrice) throws Exception {
        String serverResult = thirdServer.refund(transactionId, orderPrice);
        if ("ERROR".equals(serverResult)) {
            log.error("transactionId:" + transactionId + "--调用退款 服务 失败");
            throw new Exception("RPC调用异常");
        } else if (serverResult.equals("SUCCESS")) {
            log.info("transactionId:" + transactionId + "---调用退款 服务 成功!");
        }
    }

    //调用 UserServer 微信用户 openid 查询服务
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String notifyUserServerGetOpenid(String userId) throws Exception {
        String openid = userServer.getOpenid(userId);
        if (openid.equals("ERROR")) {
            log.error("调用 userServer 查询 openid 失败");
            throw new Exception("RPC调用异常");
        } else {
            log.info("调用 userServer 查询 openid 成功!");
            return openid;
        }
    }

    //调用 ThirdServer 退款成功模版消息推送服务
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerPushRefundSuccess(String openid,String redundInfo, String orderPrice) throws Exception {
        String thirdResult = thirdServer.pushOrderFailedToWechat(openid, redundInfo, orderPrice);
        if ("ERROR".equals(thirdResult)) {
            log.error("openid:" + openid + "--调用模版推送服务 失败!");
            throw new Exception("RPC调用异常");
        } else {
            log.info("openid:" + openid + "--调用模版推送服务 成功!");
        }
    }
}
