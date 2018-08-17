package com.sicmed.statistic.controller;

import com.sicmed.statistic.service.ClickCountDoctorService;
import com.sicmed.statistic.service.FocusCountDoctorService;
import com.sicmed.statistic.service.InquiryCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "count")
public class CountController extends BaseController {

    @Autowired
    private ClickCountDoctorService clickCountDoctorService;

    @Autowired
    private InquiryCountDoctorService inquiryCountDoctorService;

    @Autowired
    private FocusCountDoctorService focusCountDoctorService;

    /**
     * 医生查询 自己的点击量 和 问诊患者  数量
     *
     * @return
     */
    @GetMapping(value = "getClickAndInquiry")
    public Map getClickAndInquiry() {
        Map<String, Integer> countMap = new HashMap<>();
        int clickCount = clickCountDoctorService.getClickCount(getToken());

        int inquiryCount = inquiryCountDoctorService.getInquiryCount(getToken());
        countMap.put("clickCount", clickCount);
        countMap.put("inquiryCount", inquiryCount);
        return querySuccessResponse(countMap);

    }

    /**
     * 根据医生ID查询 医生 统计信息
     *
     * @param doctorId
     * @return
     */
    @GetMapping(value = "getDoctorCount")
    public Map getDoctorCount(String doctorId) {

        int clickCount = clickCountDoctorService.getClickCount(doctorId);

        int focusCount = focusCountDoctorService.getFocusCount(doctorId);

        int inquiryCount = inquiryCountDoctorService.getInquiryCount(doctorId);


        Map<String, String> countMap = new HashMap<>();
        //数据校验
        clickCount = clickCount >= 0 ? clickCount : 0;
        focusCount = focusCount >= 0 ? focusCount : 0;
        inquiryCount = inquiryCount >= 0 ? inquiryCount : 0;

        //返回对象赋值
        countMap.put("clickCount", String.valueOf(clickCount));
        countMap.put("focusCount", String.valueOf(focusCount));
        countMap.put("inquiryCount", String.valueOf(inquiryCount));

        return querySuccessResponse(countMap);
    }
}

