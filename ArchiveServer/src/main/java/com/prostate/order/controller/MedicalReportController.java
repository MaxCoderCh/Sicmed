package com.prostate.order.controller;

import com.prostate.order.entity.MedicalReport;
import com.prostate.order.entity.MedicalReportConstants;
import com.prostate.order.service.MedicalReportService;
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

    @PostMapping(value = "addInquiryReport")
    public Map addInquiryReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {
            String reportGroup = "";
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
