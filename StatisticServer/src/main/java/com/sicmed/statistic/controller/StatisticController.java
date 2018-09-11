package com.sicmed.statistic.controller;


import com.sicmed.statistic.entity.Statistic;
import com.sicmed.statistic.entity.StatisticConstant;
import com.sicmed.statistic.feignService.OrderServer;
import com.sicmed.statistic.feignService.RecordServer;
import com.sicmed.statistic.service.StatisticService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "statistic")
public class StatisticController extends BaseController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private RecordServer recordServer;

    @PostMapping(value = "addTotleIncome")
    public Map addTotleIncome(String orderId) {

        //查询订单 信息
        Map<String, Object> orderResultMap = orderServer.getOrder(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");

        String doctorId = orderMap.get("doctor").toString();
        String orderPriceStr = orderMap.get("orderPrice").toString();

        Statistic statistic = new Statistic();
        statistic.setUserId(doctorId);
        statistic.setStatisticType(StatisticConstant.INCOME_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);
        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value = value + Integer.parseInt(orderPriceStr);
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.DOCTOR_INCOME);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    @GetMapping(value = "getTotleIncome")
    public Map getTotleIncome() {

        Statistic statistic = new Statistic();
        statistic.setUserId(getToken());
        statistic.setStatisticType(StatisticConstant.INCOME_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);
        statistic = statisticService.getByParam(statistic);
        if (statistic != null) {
            String totleIncome = f2y(statistic.getStatisticValue());
            return querySuccessResponse(totleIncome);
        }
        return querySuccessResponse("0.00");
    }
    public static String f2y(String balance) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isBlank(balance)) {
            stringBuffer.append("0.00");
        } else if (balance.length() > 2) {
            stringBuffer.append(balance);
            stringBuffer.insert(stringBuffer.length() - 2, ".");
        } else if (balance.length() == 2) {
            stringBuffer.append("0.");
            stringBuffer.append(balance);
        } else if (balance.length() == 1) {
            stringBuffer.append("0.0");
            stringBuffer.append(balance);
        }
        return stringBuffer.toString();
    }

    /**
     * 医生 查看医生主页触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorClick")
    public Map addDoctorClick(String userId) {
        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.CLICK_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);
        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value++;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.DOCTOR_CLICK);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    /**
     * 患者 查看医生主页触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientClick")
    public Map addPatientClick(String userId) {
        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.CLICK_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);

        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value++;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.PATIENT_CLICK);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }


    /**
     * 医生 关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorFocus")
    public Map addDoctorFocus(String userId) {
        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.FOCUS_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);

        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value++;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.DOCTOR_FOCUS);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();

    }

    /**
     * 患者 关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientFocus")
    public Map addPatientFocus(String userId) {

        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.FOCUS_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);
        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value++;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.PATIENT_FOCUS);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    /**
     * 医生 取消关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorUnFocus")
    public Map removeDoctorFocus(String userId) {

        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.FOCUS_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);

        oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
        statisticService.updateSelective(oldStatistic);

        int value = Integer.parseInt(oldStatistic.getStatisticValue());
        value--;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.DOCTOR_UN_FOCUS);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    /**
     * 患者 取消关注医生 触发
     * -
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientUnFocus")
    public Map removePatientFocus(String userId) {
        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.FOCUS_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);

        oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
        statisticService.updateSelective(oldStatistic);

        int value = Integer.parseInt(oldStatistic.getStatisticValue());
        value--;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.PATIENT_UN_FOCUS);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    /**
     * 问诊 转诊 完成 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addOrderSuccess")
    public Map addOrderSuccess(String userId) {

        Statistic statistic = new Statistic();
        statistic.setUserId(userId);
        statistic.setStatisticType(StatisticConstant.INQUIRY_STATISTIC);
        statistic.setStatisticStatus(StatisticConstant.USABLE);

        Statistic oldStatistic = statisticService.getByParam(statistic);
        int value = 0;
        if (oldStatistic != null) {
            oldStatistic.setStatisticStatus(StatisticConstant.DISABLE);
            statisticService.updateSelective(oldStatistic);
            value = Integer.parseInt(oldStatistic.getStatisticValue());
        }
        value++;
        statistic.setStatisticValue(String.valueOf(value));
        statistic.setStatisticName(StatisticConstant.ORDER_SUCCESS);

        statisticService.insertSelective(statistic);

        return insertSuccseeResponse();
    }

    /**
     * 医生查询 自己的点击量 和 问诊患者  数量
     *
     * @return
     */
    @GetMapping(value = "getClickAndInquiry")
    public Map getClickAndInquiry() {
        Map<String, String> countMap = new HashMap<>();
        Statistic clickStatistic = new Statistic();

        clickStatistic.setUserId(getToken());
        clickStatistic.setStatisticType(StatisticConstant.CLICK_STATISTIC);
        clickStatistic.setStatisticStatus(StatisticConstant.USABLE);

        clickStatistic = statisticService.getByParam(clickStatistic);
        if (clickStatistic == null) {
            countMap.put("clickCount", "0");
        } else {
            countMap.put("clickCount", clickStatistic.getStatisticValue());
        }

        Statistic inquiryStatistic = new Statistic();

        inquiryStatistic.setUserId(getToken());
        inquiryStatistic.setStatisticType(StatisticConstant.INQUIRY_STATISTIC);
        inquiryStatistic.setStatisticStatus(StatisticConstant.USABLE);

        inquiryStatistic = statisticService.getByParam(inquiryStatistic);
        if (inquiryStatistic == null) {
            countMap.put("inquiryCount", "0");
        } else {
            countMap.put("inquiryCount", inquiryStatistic.getStatisticValue());
        }
        //查询 待接受 问诊订单 数量
        String acceptedOrderCount = orderServer.getAcceptedOrderCount(getToken());
        countMap.put("acceptedOrderCount", acceptedOrderCount);

        //查询 待接受 转诊订单 数量
        String acceptedTurnOrderCount = orderServer.getAcceptedTurnOrderCount(getToken());
        countMap.put("acceptedTurnOrderCount",acceptedTurnOrderCount);

        //查询 待接受 转诊患者 数量
        String acceptedTurnPatientCount = recordServer.getAcceptedTurnPatientCount(getToken());
        countMap.put("acceptedTurnPatientCount",acceptedTurnPatientCount);

        return querySuccessResponse(countMap);

    }

    /**
     * 根据医生ID查询 医生 统计信息
     *
     * @return
     */
    @GetMapping(value = "getDoctorCount")
    public Map getDoctorCount(String userId) {

        Map<String, String> countMap = new HashMap<>();
        //查询点击数量
        Statistic clickStatistic = new Statistic();

        clickStatistic.setUserId(userId);
        clickStatistic.setStatisticType(StatisticConstant.CLICK_STATISTIC);
        clickStatistic.setStatisticStatus(StatisticConstant.USABLE);

        clickStatistic = statisticService.getByParam(clickStatistic);
        if (clickStatistic == null) {
            countMap.put("clickCount", "0");
        } else {
            countMap.put("clickCount", clickStatistic.getStatisticValue());
        }

        //查询问诊患者数量
        Statistic inquiryStatistic = new Statistic();

        inquiryStatistic.setUserId(userId);
        inquiryStatistic.setStatisticType(StatisticConstant.INQUIRY_STATISTIC);
        inquiryStatistic.setStatisticStatus(StatisticConstant.USABLE);

        inquiryStatistic = statisticService.getByParam(inquiryStatistic);
        if (inquiryStatistic == null) {
            countMap.put("inquiryCount", "0");
        } else {
            countMap.put("inquiryCount", inquiryStatistic.getStatisticValue());
        }
        //查询关注数量
        Statistic focusStatistic = new Statistic();

        focusStatistic.setUserId(userId);
        focusStatistic.setStatisticType(StatisticConstant.FOCUS_STATISTIC);
        focusStatistic.setStatisticStatus(StatisticConstant.USABLE);

        focusStatistic = statisticService.getByParam(focusStatistic);
        if (focusStatistic == null) {
            countMap.put("focusCount", "0");
        } else {
            countMap.put("focusCount", focusStatistic.getStatisticValue());
        }



        return querySuccessResponse(countMap);
    }
}
