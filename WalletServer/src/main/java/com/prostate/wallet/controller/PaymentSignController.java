package com.prostate.wallet.controller;


import com.prostate.wallet.entity.PaymentSign;
import com.prostate.wallet.service.DoctorWalletService;
import com.prostate.wallet.service.PaymentSignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/18 16:13
 * @Todo:  支付密码控制
 */
@Slf4j
@RestController
@RequestMapping("paymentSign")
public class PaymentSignController  extends BaseController {


    @Autowired
    private PaymentSignService paymentSignService;

    @Autowired
    private DoctorWalletService doctorWalletService;

    @Autowired
    private PaymentSign paymentSign;


    /**
     * @Author: bian
     * @Date: 2018/7/18 16:17
     * @todo: 添加支付密码
     * @param:   * @param null
     */
    @PostMapping("/save")
    public Map savePass(String paymentPassword) {
        paymentSign.setWalletId(getToken());
        paymentSign.setId(getToken());
        paymentSign.setPaymentPassword(paymentPassword);
        //首先判断这个钱包存不存在
        if (doctorWalletService.selectById(getToken()) == null) {
            return queryEmptyResponse();
        }
        if (paymentSignService.insertSelective(paymentSign) > 0){
            log.info("创建支付密码");
            return insertSuccseeResponse();
        }else {
            return  insertFailedResponse();
        }
    }


    /**
     * 判断是否已经有支付密码
     *
     * @return
     */
    @GetMapping("isExist")
    public Map isExist() {
        PaymentSign paymentSign = paymentSignService.selectById(getToken());
        if (paymentSign == null) {
            return querySuccessResponse(false);
        }
        return querySuccessResponse(true);
    }

    /**
     * @Author: bian
     * @Date: 2018/7/18 16:17
     * @todo:   修改支付密码
     * @param:   * @param null
     */
    @PostMapping("update")
    public Map updatePass(String paymentPassword) {
        paymentSign.setPaymentPassword(paymentPassword);
        paymentSign.setId(getToken());
        paymentSign.setWalletId(getToken());
        if (paymentSignService.updateSelective(paymentSign) > 0){
            return updateSuccseeResponse();
        }else {
            return updateFailedResponse();
        }
    }

    /**
     * @Author: 支付密码校验
     * @Date: 2018/7/18 16:17
     * @param:
     */
    @PostMapping("check")
    public Map checkPass(String paymentPassword) {
        paymentSign.setWalletId(getToken());
        paymentSign.setId(getToken());
        paymentSign.setPaymentPassword(paymentPassword);
        List<PaymentSign> paymentSigns  = paymentSignService.selectByParams(paymentSign);
        if (paymentSigns.isEmpty()){
            return queryEmptyResponse();
        } else {
            return querySuccessResponse(null);
        }
    }


    /**
     * 重设 支付 密码
     *
     * @param smsCode
     * @return
     */
    @PostMapping(value = "paymentPasswordReset")
    public Map paymentPasswordReset(String smsCode, String paymentPassword) {
        //短信验证码校验
        String cachePhone = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(cachePhone)) {
            return emptyParamResponse();
        }

        paymentSign.setPaymentPassword(paymentPassword);
        paymentSign.setId(getToken());
        paymentSign.setWalletId(getToken());
        if (paymentSignService.updateSelective(paymentSign) > 0) {
            return updateSuccseeResponse();
        } else {
            return updateFailedResponse();
        }
    }
}
