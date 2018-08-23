package com.sicmed.archive.controller;

import com.sicmed.archive.entity.MedicalReport;
import com.sicmed.archive.entity.MedicalReportConstants;
import com.sicmed.archive.service.MedicalReportService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "medical/report")
public class MedicalReportController extends BaseController {

    @Autowired
    private MedicalReportService medicalReportService;

    /**
     * 添加 问诊 报告
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addInquiryReport")
    public Map addInquiryReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {
            String reportGroup = RandomStringUtils.randomAlphanumeric(64);
            for (String s : imgUrlArr) {
                MedicalReport medicalReport = new MedicalReport();
                medicalReport.setReportUrl(s);
                medicalReport.setPatientId(patientId);
                medicalReport.setReportGroup(reportGroup);
                medicalReport.setReportType(MedicalReportConstants.INQUIRY_REPORT_TYPE);
                medicalReportService.insertSelective(medicalReport);
            }
            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * 根据分组编号 查询 档案
     * @param groupNumber
     * @return
     */
    @GetMapping(value = "getByGroupNumber")
    public Map getByGroupNumber(String groupNumber) {
        if (StringUtils.isBlank(groupNumber)) {
            return emptyParamResponse();
        }
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setReportGroup(groupNumber);
        List<String> reportUrlList = medicalReportService.selectUrlByParams(medicalReport);
        if (reportUrlList == null || reportUrlList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(reportUrlList);
    }

    /**
     * APP
     */
    @GetMapping(value = "getMedicalReportList")
    public Map getMedicalReportList(String patientId) {
        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setPatientId(patientId);
        Map<String, List<String>> medicalReportGroupList = new LinkedHashMap<>();

        List<MedicalReport> medicalReportList = medicalReportService.selectByParams(medicalReport);
        if (medicalReportList == null || medicalReportList.isEmpty()) {
            return queryEmptyResponse();
        }
        for (MedicalReport report : medicalReportList) {
            List<String> urlList = medicalReportGroupList.get(report.getReportGroup());
            if (urlList == null) {
                urlList = new ArrayList<>();
                medicalReportGroupList.put(report.getReportGroup(), urlList);
            }
            urlList.add(report.getReportUrl());
        }
        return querySuccessResponse(medicalReportGroupList);
    }
}
