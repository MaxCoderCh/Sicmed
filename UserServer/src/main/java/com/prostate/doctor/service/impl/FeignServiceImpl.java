package com.prostate.doctor.service.impl;

import com.prostate.doctor.feignService.StaticServer;
import com.prostate.doctor.feignService.StatisticServer;
import com.prostate.doctor.feignService.ThirdServer;
import com.prostate.doctor.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class FeignServiceImpl implements FeignService {

    @Autowired
    private ThirdServer thirdServer;
    @Autowired
    private StaticServer stataServer;
    @Autowired
    private StatisticServer statisticServer;

    private final static String SUCCESS_CODE = "20000";
    private final static String ERROR_CODE = "ERROR";

    /***************************** ThirdServer *****************************/

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> ThirdServeridCard(String url) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.idCard(url);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            Map<String, String> orderMap = (Map<String, String>) serverResultMap.get("result");
            log.info("根据身份证:" + url + " 获取 身份证信息 成功!");
            return orderMap;
        } else {
            log.info("根据身份证:" + url + " 获取 身份证信息 失败!");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendRegisterCode(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendRegisterCode(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("发送注册验证码到 phoneNumber:" + phoneNumber + "成功");
        } else {
            log.info("发送注册验证码到 phoneNumber:" + phoneNumber + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendLoginCode(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendLoginCode(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("发送登陆验证码到 phoneNumber:" + phoneNumber + "成功");
        } else {
            log.info("发送登陆验证码到 phoneNumber:" + phoneNumber + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void ThirdServerSendPasswordReplaceCode(String phoneNumber) throws Exception {
        Map<String, Object> serverResultMap = thirdServer.sendPasswordReplaceCode(phoneNumber);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("发送密码重置验证码到 phoneNumber:" + phoneNumber + "成功");
        } else {
            log.info("发送密码重置验证码到 phoneNumber:" + phoneNumber + "失败");
            throw new Exception("");
        }
    }


    /***************************** StatisticServer *****************************/
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddOrderSuccess(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addOrderSuccess(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计订单成功 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计订单成功 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddDoctorFocus(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addDoctorFocus(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计医生关注医生 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计医生关注医生 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddPatientFocus(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addPatientFocus(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计患者关注医生 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计患者关注医生 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddDoctorClick(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addDoctorClick(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计医生点击 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计医生点击 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddPatientClick(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addPatientClick(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计患者点击 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计患者点击 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddDoctorUnFocus(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addDoctorUnFocus(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计医生取消关注 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计医生取消关注 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void StatisticServerAddPatientUnFocus(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.addPatientUnFocus(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 统计患者取消关注 USER ID:" + userId + "成功");
        } else {
            log.info("调用 StatisticServer 统计患者取消关注 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> StatisticServerGetDoctorCount(String userId) throws Exception {
        Map<String, Object> serverResultMap = statisticServer.getDoctorCount(userId);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StatisticServer 查询医生统计数据 USER ID:" + userId + "成功");
            return (Map<String, String>) serverResultMap.get("result");
        } else {
            log.info("调用 StatisticServer 查询医生统计数据 USER ID:" + userId + "失败");
            throw new Exception("");
        }
    }

    /***************************** StaticServer *****************************/

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetHospitalById(String id) throws Exception {
        Map<String, Object> serverResultMap = stataServer.getHospitalById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 根据 HOSPITAL ID:" + id + "查询医院信息成功");
            return String.valueOf(serverResultMap.get("result"));
        } else {
            log.info("调用 StaticServer 根据 HOSPITAL ID:" + id + "查询医院信息失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetBranchById(String id) throws Exception {
        Map<String, Object> serverResultMap = stataServer.getBranchById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 根据 BRANCH ID:" + id + "查询科室信息成功");
            return String.valueOf(serverResultMap.get("result"));
        } else {
            log.info("调用 StaticServer 根据 BRANCH ID:" + id + "查询科室信息成功");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public String StaticServerGetTitleById(String id) throws Exception {
        Map<String, Object> serverResultMap = stataServer.getTitleById(id);
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 根据 TITLE ID:" + id + "查询职称信息成功");
            return String.valueOf(serverResultMap.get("result"));
        } else {
            log.info("调用 StaticServer 根据 TITLE ID:" + id + "查询职称信息成功");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> StaticServerHospitalJson() throws Exception {
        Map<String, Object> serverResultMap = stataServer.hospitalJson();
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 查询医院信息成功");
            return (Map<String, String>) serverResultMap.get("result");
        } else {
            log.info("调用 StaticServer 查询医院信息失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> StaticServerBranchServiceJson() throws Exception {
        Map<String, Object> serverResultMap = stataServer.branchServiceJson();
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 查询科室信息成功");
            return (Map<String, String>) serverResultMap.get("result");
        } else {
            log.info("调用 StaticServer 查询科室信息失败");
            throw new Exception("");
        }
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public Map<String, String> StaticServerDoctorTitleJson() throws Exception {
        Map<String, Object> serverResultMap = stataServer.doctorTitleJson();
        String resultCode = String.valueOf(serverResultMap.get("code"));
        if (SUCCESS_CODE.equals(resultCode)) {
            log.info("调用 StaticServer 查询职称信息成功");
            return (Map<String, String>) serverResultMap.get("result");
        } else {
            log.info("调用 StaticServer 查询职称信息失败");
            throw new Exception("");
        }
    }
}
