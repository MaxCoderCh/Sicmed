package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.entity.InquiryRecordConstants;
import com.sicmed.assessmen.feignService.OrderServer;
import com.sicmed.assessmen.feignService.RecordServer;
import com.sicmed.assessmen.feignService.WalletServer;
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
    public Map addDraft(String inquiryRecordId, String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer) {

        InquiryRecord inquiryRecord = new InquiryRecord();
        inquiryRecord.setPatient(patientId);
        inquiryRecord.setPatientArchive(patientArchive);
        inquiryRecord.setInquiryDescription(inquiryDescription);
        inquiryRecord.setInquiryAnswer(inquiryAnswer);
        inquiryRecord.setRecordType(InquiryRecordConstants.DRAFT_TYPE);
        inquiryRecord.setRecordStatus(InquiryRecordConstants.DRAFT_STATUS);

        int i;
        if (StringUtils.isBlank(inquiryRecordId)) {
            i = inquiryRecordService.insertSelective(inquiryRecord);
        } else {
            inquiryRecord.setId(inquiryRecordId);
            i = inquiryRecordService.updateSelective(inquiryRecord);
        }
        if (i > 0) {
            return insertSuccseeResponse(inquiryRecord.getId());
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
    public Map addFinal(String inquiryRecordId, String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer, String orderId) {

        InquiryRecord inquiryRecord = new InquiryRecord();

        inquiryRecord.setPatient(patientId);
        inquiryRecord.setPatientArchive(patientArchive);
        inquiryRecord.setInquiryDescription(inquiryDescription);
        inquiryRecord.setInquiryAnswer(inquiryAnswer);
        inquiryRecord.setRecordType(InquiryRecordConstants.FINAL_TYPE);
        inquiryRecord.setRecordStatus(InquiryRecordConstants.FINAL_STATUS);

        int i;
        if (StringUtils.isBlank(inquiryRecordId)) {
            i = inquiryRecordService.insertSelective(inquiryRecord);
        } else {
            inquiryRecord.setId(inquiryRecordId);
            i = inquiryRecordService.updateSelective(inquiryRecord);
        }
        if (i > 0) {
            //调用订单服务 修改 订单状态
            orderServer.orderDoneSuccess(orderId);
            //调用钱包服务 订单收益 存入医生零钱
            walletServer.addOrderIncome(orderId);
            //调用档案服务 建立医生 患者关系
            recordServer.addUserPatientByOrder(orderId);

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
