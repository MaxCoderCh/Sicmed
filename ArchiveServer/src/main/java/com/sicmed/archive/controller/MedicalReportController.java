package com.sicmed.archive.controller;

import com.sicmed.archive.entity.MedicalReport;
import com.sicmed.archive.entity.MedicalReportConstants;
import com.sicmed.archive.service.MedicalReportService;
import com.sicmed.archive.util.DateFormatUtils;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping(value = "medical/report")
public class MedicalReportController extends BaseController {

    @Autowired
    private MedicalReportService medicalReportService;

    /**
     * WeChat 添加 问诊 报告
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addInquiryReport")
    public Map addInquiryReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {

            String reportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.INQUIRY_REPORT_TYPE);

            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * WeChat 修改 问诊 报告
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "updateInquiryReport")
    public Map addInquiryReport(String[] imgUrlArr, String patientId,String reportGroup) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {
            String newReportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.INQUIRY_REPORT_TYPE);

            medicalReportService.deleteReportByGroup(reportGroup);

            return insertSuccseeResponse(newReportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * PAD 添加 体检 报告
     *
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addCheckupReport")
    public Map addCheckupReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {

            String reportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.CHECKUP_REPORT_TYPE);

            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * PAD 添加 病程记录 报告
     *
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addDiseaseReport")
    public Map addDisease(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {

            String reportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.DISEASE_REPORT_TYPE);

            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * PAD 添加 住院 报告
     *
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addHospitalReport")
    public Map addHospitalReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {

            String reportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.HOSPITAL_REPORT_TYPE);

            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    /**
     * PAD 添加 住院 报告
     *
     * @param imgUrlArr
     * @param patientId
     * @return
     */
    @PostMapping(value = "addInspectionReport")
    public Map addInspectionReport(String[] imgUrlArr, String patientId) {
        if (imgUrlArr != null && imgUrlArr.length > 0) {

            String reportGroup = this.addReport(imgUrlArr, patientId, MedicalReportConstants.INSPECTION_REPORT_TYPE);

            return insertSuccseeResponse(reportGroup);
        }
        return emptyParamResponse();
    }

    private String addReport(String[] imgUrlArr, String patientId, String reportType) {
        String reportGroup = RandomStringUtils.randomAlphanumeric(64);
        for (String s : imgUrlArr) {
            MedicalReport medicalReport = new MedicalReport();
            medicalReport.setReportUrl(s);
            medicalReport.setPatientId(patientId);
            medicalReport.setReportGroup(reportGroup);
            medicalReport.setReportType(reportType);
            medicalReportService.insertSelective(medicalReport);
        }
        return reportGroup;
    }

    /**
     * APP WeChat 根据分组编号 查询 档案
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
     * APP 查询 问诊报告 列表
     */
    @GetMapping(value = "getMedicalReportList")
    public Map getMedicalReportList(String patientId) {
        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }
        MedicalReport medicalReport = new MedicalReport();
        medicalReport.setPatientId(patientId);
        medicalReport.setReportType(MedicalReportConstants.INQUIRY_REPORT_TYPE);
        Map<String, List<String>> medicalReportGroupMap = new LinkedHashMap<>();

        List<MedicalReport> medicalReportList = medicalReportService.selectByParams(medicalReport);
        if (medicalReportList == null || medicalReportList.isEmpty()) {
            return queryEmptyResponse();
        }
        for (MedicalReport report : medicalReportList) {

            List<String> urlList = medicalReportGroupMap.get(DateFormatUtils.dateToStr(report.getCreateTime()));
            if (urlList == null) {
                urlList = new ArrayList<>();
                medicalReportGroupMap.put(DateFormatUtils.dateToStr(report.getCreateTime()), urlList);
            }
            urlList.add(report.getReportUrl());
        }
        return querySuccessResponse(medicalReportGroupMap,medicalReportGroupMap.size());
    }

    /**
     * WeChat 删除 问诊 报告
     *
     * @return
     */
    @PostMapping(value = "deleteInquiryReport")
    public Map deleteInquiryReport(String imgPath) {
        if (StringUtils.isNotBlank(imgPath)) {

            int i = medicalReportService.deleteByImgPath(imgPath);
            if (i >= 0) {
                try {
                    feginService.notifyThirdServerDeleteFile(imgPath);
                } catch (Exception e) {
                    log.error("调用 ThirdServer 删除文件:" + imgPath + "失败!");
                    return deleteFailedResponse();
                }
                return deleteSuccseeResponse();
            }
            return deleteFailedResponse();
        }
        return emptyParamResponse();
    }
}
