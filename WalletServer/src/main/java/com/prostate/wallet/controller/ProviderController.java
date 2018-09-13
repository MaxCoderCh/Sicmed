package com.prostate.wallet.controller;

import com.prostate.wallet.entity.DealRecord;
import com.prostate.wallet.entity.DealRecordConstant;
import com.prostate.wallet.entity.DoctorWallet;
import com.prostate.wallet.feignService.OrderServer;
import com.prostate.wallet.service.DealRecordService;
import com.prostate.wallet.service.DoctorWalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "provider")
public class ProviderController extends BaseController{
    @Autowired
    private DoctorWalletService doctorWalletService;

    @Autowired
    private DealRecordService dealRecordService;

    @Autowired
    private OrderServer orderServer;

    /**
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "addOrderIncome")
    public String addOrderIncome(String orderId) throws Exception {
        //查询订单 信息
        Map<String, Object> orderResultMap = orderServer.getOrder(orderId);

        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");
        log.info(orderMap.toString());
        String doctorId = orderMap.get("doctor").toString();
        String orderPriceStr = orderMap.get("orderPrice").toString();

        int orderPrice = Integer.parseInt(orderPriceStr);
        //获取 医生钱包
        DoctorWallet doctorWallet = doctorWalletService.selectByDoctorId(doctorId);
        if (doctorWallet==null){
            doctorWallet = doctorWalletService.create(doctorId);
        }
        String walletBalanceStr = doctorWallet.getWalletBalance();
        int walletBalance = Integer.parseInt(walletBalanceStr);

        int newWalletBalance = orderPrice + walletBalance;
        String newWalletBalanceStr = String.valueOf(newWalletBalance);

        //添加 收支明细
        DealRecord dealRecord = new DealRecord();
        dealRecord.setOrderId(orderId);
        int i = dealRecordService.checkByOrder(dealRecord);
        if (i > 0) {
            throw new Exception("交易记录已存在");
        }

        dealRecord.setOrderId(orderId);
        dealRecord.setWalletId(doctorWallet.getId());
        dealRecord.setSerialNumber(RandomStringUtils.randomNumeric(24));
        dealRecord.setDealAmount(orderMap.get("orderPrice").toString());
        dealRecord.setDealType(DealRecordConstant.INCOME_TYPE);
        dealRecord.setPaymentType("微信支付");
        dealRecord.setWalletBalance(newWalletBalanceStr);
        dealRecord.setRemark("交易备注");
        dealRecordService.insertSelective(dealRecord);
        //修改 钱包余额
        doctorWallet.setWalletBalance(newWalletBalanceStr);
         i = doctorWalletService.updateSelective(doctorWallet);
        if (i<=0){
            log.error("医生余额修改失败");
            return "ERROR";
        }
        return "SUCCESS";
    }


    /**
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "cashOrder")
    public String cashOrder(String orderId) throws Exception {
        //查询订单 信息
        Map<String, Object> orderResultMap = orderServer.getOrderCash(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");

        String doctorId = orderMap.get("createUser").toString();
        String orderPriceStr = orderMap.get("orderPrice").toString();

        int orderPrice = Integer.parseInt(orderPriceStr);
        //获取 医生钱包
        DoctorWallet doctorWallet = doctorWalletService.selectByDoctorId(doctorId);
        if (doctorWallet==null){
            doctorWallet = doctorWalletService.create(doctorId);
        }
        String walletBalanceStr = doctorWallet.getWalletBalance();
        int walletBalance = Integer.parseInt(walletBalanceStr);

        int newWalletBalance = walletBalance - orderPrice;
        String newWalletBalanceStr = String.valueOf(newWalletBalance);

        //添加 收支明细
        DealRecord dealRecord = new DealRecord();
        dealRecord.setOrderId(orderId);
        int i = dealRecordService.checkByOrder(dealRecord);
        if (i > 0) {
            throw new Exception("交易记录已存在");
        }

        dealRecord.setOrderId(orderId);
        dealRecord.setWalletId(doctorWallet.getId());
        dealRecord.setSerialNumber(RandomStringUtils.randomNumeric(24));
        dealRecord.setDealAmount(orderMap.get("orderPrice").toString());
        dealRecord.setDealType(DealRecordConstant.EXPEND_TYPE);
        dealRecord.setPaymentType("微信支付");
        dealRecord.setWalletBalance(newWalletBalanceStr);
        dealRecord.setRemark("交易备注");
        dealRecordService.insertSelective(dealRecord);
        //修改 钱包余额
        doctorWallet.setWalletBalance(newWalletBalanceStr);
        i = doctorWalletService.updateSelective(doctorWallet);
        if (i<=0){
            throw new Exception("医生余额修改失败");
        }
        return "SUCCESS";
    }

}

