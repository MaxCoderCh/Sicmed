package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.entity.InquiryRecordConstants;
import com.sicmed.assessmen.service.InquiryRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "record/inquiry")
public class InquiryRecordController extends BaseController {

    @Autowired
    private InquiryRecordService inquiryRecordService;


    /**
     * 添加草稿
     *
     * @param patientId
     * @param patientArchive
     * @param inquiryDescription
     * @param inquiryAnswer
     * @return
     */
    @PostMapping(value = "addDraft")
    public Map addDraft(String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer) {
        InquiryRecord inquiryRecord = new InquiryRecord();
        inquiryRecord.setPatient(patientId);
        inquiryRecord.setPatientArchive(patientArchive);
        inquiryRecord.setInquiryDescription(inquiryDescription);
        inquiryRecord.setInquiryAnswer(inquiryAnswer);
        inquiryRecord.setRecordType(InquiryRecordConstants.DRAFT_TYPE);
        int i = inquiryRecordService.insertSelective(inquiryRecord);
        if (i > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 添加最终回复
     *
     * @param patientId
     * @param patientArchive
     * @param inquiryDescription
     * @param inquiryAnswer
     * @return
     */
    @PostMapping(value = "addFinal")
    public Map addFinal(String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer) {
        InquiryRecord inquiryRecord = new InquiryRecord();
        inquiryRecord.setPatient(patientId);
        inquiryRecord.setPatientArchive(patientArchive);
        inquiryRecord.setInquiryDescription(inquiryDescription);
        inquiryRecord.setInquiryAnswer(inquiryAnswer);
        inquiryRecord.setRecordType(InquiryRecordConstants.FINAL_TYPE);
        int i = inquiryRecordService.insertSelective(inquiryRecord);
        if (i > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 查询 医生回复
     *
     * @param archive
     * @return
     */
    @GetMapping(value = "getByArchive")
    public Map getByArchive(String archive) {

        if (StringUtils.isBlank(archive)) {
            return emptyParamResponse();
        }
        InquiryRecord inquiryRecord = inquiryRecordService.selectByArchive(archive);

        if (inquiryRecord == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(inquiryRecord);
    }
}