package com.prostate.record.service.impl;

import com.prostate.record.feignService.OrderServer;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.feignService.StatisticServer;
import com.prostate.record.feignService.ThirdServer;
import com.prostate.record.service.FeignService;
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
    private OrderServer orderServer;
    @Autowired
    private StaticServer staticServer;
    @Autowired
    private StatisticServer statisticServer;
    @Autowired
    private ThirdServer thirdServer;
    private final static String SUCCESS_CODE = "20000";
    private final static String ERROR_CODE = "ERROR";

    /***************************** OrderServer *****************************/
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> OrderServerGetOrder(String orderId) throws Exception {
        Map<String, Object> serverResultMap = orderServer.getOrder(orderId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            Map<String, String> orderMap = (Map<String, String>) serverResultMap.get("result");
            log.info("根据ORDER ID:" + orderId + " 查询 患者列表 成功");
            return orderMap;
        } else {
            log.info("根据ORDER ID:" + orderId + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    /***************************** StaticServer *****************************/

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetAnamnesisAllergyDrugById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getAnamnesisAllergyDrugById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String allergyDrugName = String.valueOf(serverResultMap.get("result"));
            log.info("根据AllergyDrug ID:" + id + " 查询 患者列表 成功");
            return allergyDrugName;
        } else {
            log.info("根据AllergyDrug ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetAnamnesisEatingDrugById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getAnamnesisEatingDrugById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String eatingDrugName = String.valueOf(serverResultMap.get("result"));
            log.info("根据EatingDrug ID:" + id + " 查询 患者列表 成功");
            return eatingDrugName;
        } else {
            log.info("根据EatingDrug ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetAnamnesisIllnessById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getAnamnesisIllnessById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String illnessName = String.valueOf(serverResultMap.get("result"));
            log.info("根据Illness ID:" + id + " 查询 患者列表 成功");
            return illnessName;
        } else {
            log.info("根据Illness ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetSurgicalHistoryById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getSurgicalHistoryById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String surgicalHistoryName = String.valueOf(serverResultMap.get("result"));
            log.info("根据SurgicalHistory ID:" + id + " 查询 患者列表 成功");
            return surgicalHistoryName;
        } else {
            log.info("根据SurgicalHistory ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String,String> StaticServerGetCityDetail(String cityId) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getCityDetail(cityId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            Map<String,String> cityDetailMap = (Map<String, String>) serverResultMap.get("result");
            log.info("根据CityDetail ID:" + cityId + " 查询 患者列表 成功");
            return cityDetailMap;
        } else {
            log.info("根据CityDetail ID:" + cityId + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetEducationById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getEducationById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String educationName = String.valueOf(serverResultMap.get("result"));
            log.info("根据Education ID:" + id + " 查询 患者列表 成功");
            return educationName;
        } else {
            log.info("根据Education ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetBloodGroupById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getBloodGroupById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String bloodGroupName = String.valueOf(serverResultMap.get("result"));
            log.info("根据BloodGroup ID:" + id + " 查询 患者列表 成功");
            return bloodGroupName;
        } else {
            log.info("根据BloodGroup ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetNationById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getNationById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String nationName = String.valueOf(serverResultMap.get("result"));
            log.info("根据Nation ID:" + id + " 查询 患者列表 成功");
            return nationName;
        } else {
            log.info("根据Nation ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetProfessionById(String id) throws Exception {
        Map<String, Object> serverResultMap = staticServer.getProfessionById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            String professionName = String.valueOf(serverResultMap.get("result"));
            log.info("根据Profession ID:" + id + " 查询 患者列表 成功");
            return professionName;
        } else {
            log.info("根据Profession ID:" + id + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    /***************************** StatisticServer *****************************/

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> StatisticServerAddOrderSuccess(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addOrderSuccess(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            Map<String, String> orderMap = (Map<String, String>) serverResultMap.get("result");
            log.info("根据USER ID:" + userId + " 查询 患者列表 成功");
            return orderMap;
        } else {
            log.info("根据USER ID:" + userId + " 查询 患者列表 失败");
            throw new Exception("");
        }
    }

    /***************************** ThirdServer *****************************/
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> ThirdServeridCard(String url) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.idCard(url);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            Map<String, String> orderMap = (Map<String, String>) serverResultMap.get("result");
            log.info("根据身份证:" + url + " 获取 身份证信息 成功");
            return orderMap;
        } else {
            log.info("根据身份证:" + url + " 获取 身份证信息 失败");
            throw new Exception("RPC 调用异常");
        }
    }


}

