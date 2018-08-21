package com.sicmed.archive.controller;

import com.sicmed.archive.entity.MedicalReport;
import com.sicmed.archive.entity.MedicalReportConstants;
import com.sicmed.archive.service.MedicalReportService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
