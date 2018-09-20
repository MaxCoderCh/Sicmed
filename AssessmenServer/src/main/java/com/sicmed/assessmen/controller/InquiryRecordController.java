package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.entity.InquiryRecordConstants;
import com.sicmed.assessmen.service.InquiryRecordService;
import com.sicmed.assessmen.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@Slf4j
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
    public Map addFinal(String inquiryRecordId, String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer, String orderId) throws Exception {

        InquiryRecord inquiryRecord = new InquiryRecord();
        Date updateTime = new Date();
        inquiryRecord.setPatient(patientId);
        inquiryRecord.setPatientArchive(patientArchive);
        inquiryRecord.setInquiryDescription(inquiryDescription);
        inquiryRecord.setInquiryAnswer(inquiryAnswer);
        inquiryRecord.setRecordType(InquiryRecordConstants.FINAL_TYPE);
        inquiryRecord.setRecordStatus(InquiryRecordConstants.FINAL_STATUS);
        inquiryRecord.setUpdateTime(updateTime);
        int i;
        if (StringUtils.isBlank(inquiryRecordId)) {
            i = inquiryRecordService.insertSelective(inquiryRecord);
        } else {
            inquiryRecord.setId(inquiryRecordId);
            i = inquiryRecordService.updateSelective(inquiryRecord);
        }
        if (i > 0) {
            // 通知其他服务订单回复成功
            new Thread(() -> {
                try {
                    Map<String, String> orderInquiry = feignService.notifyOrderServerOrderDoneSuccess(orderId);

                    String doctorId = orderInquiry.get("seller");
                    String buyer = orderInquiry.get("buyer");
                    String orderPrice = orderInquiry.get("orderPrice");
                    String orderType = orderInquiry.get("orderType");

                    feignService.notifyWalletServerAddOrderIncome(orderId, doctorId, orderPrice);
                    feignService.notifyRecordServerAddUserPatientByOrder(doctorId, patientId, orderType);
                    feignService.notifyStatisticServerAddTotleIncome(doctorId, orderPrice);
                    String phoneNumber = feignService.notifyUserServerGetPhoneNumber(doctorId);
                    feignService.notifyThirdServerSendInquiryEndToDoctor(phoneNumber);
                    String openid = feignService.notifyUserServerGetOpenid(buyer);
                    feignService.notifyThirdServerPushOrderSuccessToWechat(openid, orderInquiry.get("transactionId"));

                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                Thread.currentThread().interrupt();
            }).start();

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
