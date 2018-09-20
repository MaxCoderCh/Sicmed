package com.prostate.order.service.impl;

import com.prostate.order.feignService.RecordServer;
import com.prostate.order.feignService.ThirdServer;
import com.prostate.order.feignService.UserServer;
import com.prostate.order.feignService.WalletServer;
import com.prostate.order.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class FeignServiceImpl implements FeignService {

    @Autowired
    private RecordServer recordServer;
    @Autowired
    private ThirdServer thirdServer;
    @Autowired
    private UserServer userServer;
    @Autowired
    private WalletServer walletServer;

    private final static String SUCCESS_CODE = "20000";
    private final static String ERROR_CODE = "ERROR";
/***************************** RecordServer *****************************/
    /**
     * 调用 RecordServer 根据 USER ID查询 患者列表
     *
     * @param userId
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void RecordServerGetPatientListById(String userId) throws Exception {
        Map<String, Object> serverResultMap = recordServer.getPatientListById(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("根据USER ID:" + userId + " 查询 患者列表 成功");
        } else {
            log.info("根据USER ID:" + userId + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    /**
     * 调用 RecordServer 根据 patientIds 查询 患者列表
     *
     * @param patientIds
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void RecordServerGetPatientListByIds(String patientIds) throws Exception {
        Map<String, Object> serverResultMap = recordServer.getPatientListByIds(patientIds);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("根据patientIds:" + patientIds + " 查询 患者列表 成功");
        } else {
            log.info("根据patientIds:" + patientIds + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

/***************************** ThirdServer *****************************/
    /**
     * 发送支付成功 短信 给 问诊发起 人
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendPaymentSuccess(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendPaymentSuccess(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用ThirdServer 发送支付成功 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用ThirdServer 发送支付成功 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("");
        }
    }

    /**
     * 订单 结束 发送 短信 通知 问诊 发起人
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendInquiryEndToPatient(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendInquiryEndToPatient(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用ThirdServer 发送订单结束通知 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用ThirdServer 发送订单结束通知 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("");
        }
    }

    /**
     * 订单 被拒绝 发送 退款通知 给 问诊发起人
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendInquiryRefund(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendInquiryRefund(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用ThirdServer 发送退款通知 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用ThirdServer 发送退款通知 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("");
        }
    }

    /**
     * 审核通过 发送短信给医生
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendProveSuccess(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendProveSuccess(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用 ThirdServer 发送审核成功通知 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用 ThirdServer 发送审核成功通知 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("");
        }
    }

    /**
     * 审核失败 发送短信给医生
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendProveFailed(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendProveFailed(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用 ThirdServer 发送审核失败通知 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用 ThirdServer 发送审核失败通知 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("");
        }
    }

    /**
     * 提现申请成功 发送 提现申请成功短信 给医生
     *
     * @param phoneNumber
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendBalanceForCash(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendBalanceForCash(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            serverResultMap.get("result");
            log.info("调用 ThirdServer 发送提现申请成功短信通知 给 phoneNumber:" + phoneNumber + " 成功");
        } else {
            log.info("调用 ThirdServer 发送提现申请成功短信通知 给 phoneNumber:" + phoneNumber + " 失败");
            throw new Exception("RPC 调用异常");
        }
    }

    /**
     * 发送支付成功 模版消息 给 问诊发起 人
     *
     * @param openid
     * @param orderId
     * @param goodsInfo
     * @param orderPrice
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerPushPaymentSuccessToWechat(String openid, String orderId, String goodsInfo, String orderPrice) throws Exception {
        String serverResult = thirdServer.pushPaymentSuccessToWechat(openid, orderId, goodsInfo, orderPrice);
        if (SUCCESS_CODE.equals(serverResult)) {
            log.info("调用 ThirdServer 发送支付成功模版消息 给 openid:" + openid + " 成功");
        } else {
            log.info("调用 ThirdServer 发送支付成功模版消息 给 openid:" + openid + " 失败");
            throw new Exception("RPC 调用异常");
        }
    }

    /**
     * 退款成功 发送微信模版消息 给 付款人
     *
     * @param openid
     * @param refundInfo
     * @param refundPrice
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerPushOrderFailedToWechat(String openid, String refundInfo, String refundPrice) throws Exception {
        String serverResult = thirdServer.pushOrderFailedToWechat(openid, refundInfo, refundPrice);
        if (SUCCESS_CODE.equals(serverResult)) {
            log.info("调用 ThirdServer 发送退款成功微信模版消息 给 openid:" + openid + " 成功");
        } else {
            log.info("调用 ThirdServer 发送退款成功微信模版消息 给 openid:" + openid + " 失败");
            throw new Exception("RPC 调用异常");
        }
    }

    /**
     * 订单 被拒绝 调用 退款服务 退款
     *
     * @param transactionId
     * @param orderPrice
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerRefund(String transactionId, String orderPrice) throws Exception {
        String serverResult = thirdServer.refund(transactionId, orderPrice);
        if (SUCCESS_CODE.equals(serverResult)) {
            log.info("调用 ThirdServer 调用 退款服务 退款 transactionId:" + transactionId + " 成功");
        } else {
            log.info("调用 ThirdServer 调用 退款服务 退款 transactionId:" + transactionId + " 失败");
            throw new Exception("RPC 调用异常");
        }
    }

    /***************************** ThirdServer *****************************/
    /**
     * 根据 用户 ID查询 openid
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String UserServerGetOpenid(String userId) throws Exception {
        String serverResult = userServer.getOpenid(userId);
        if (ERROR_CODE.equals(serverResult)) {
            log.info("调用 UserServer 查询openid 失败");
            throw new Exception("RPC 调用异常");
        } else {
            log.info("调用 UserServer 查询openid 成功");
            return serverResult;
        }
    }

    /***************************** WalletServer *****************************/

    /**
     * 提现 申请 发起成功 通知 WalletServer 修改 钱包余额
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void WalletServerCashOrder(String orderId, String doctorId, String orderPrice) throws Exception {
        Map<String, Object> serverResultMap = walletServer.cashOrder(orderId,doctorId,orderPrice);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("通知 WalletServer 修改 钱包余额  成功");
        } else {
            log.info("通知 WalletServer 修改 钱包余额  失败");
            throw new Exception("RPC 调用异常");
        }
    }
}
