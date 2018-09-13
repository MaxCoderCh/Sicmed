package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.entity.InquiryRecordConstants;
import com.sicmed.assessmen.service.InquiryRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
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
    public Map addFinal(String inquiryRecordId, String patientId, String patientArchive, String inquiryDescription, String inquiryAnswer, String orderId) {

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
            String serverResult;
            //调用订单服务 修改订单状态
            serverResult = orderServer.orderDoneSuccess(orderId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用订单服务 修改订单状态 失败!");
            }
            //调用钱包服务 订单收益 存入医生零钱
            serverResult = walletServer.addOrderIncome(orderId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用钱包服务 订单收益 存入医生零钱 失败!");
            }
            //调用档案服务 建立医生-患者关系
            serverResult = recordServer.addUserPatientByOrder(orderId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用档案服务 建立医生-患者关系 失败!");
            }
            //调用统计服务 修改总收益
            serverResult = statisticServer.addTotleIncome(orderId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用统计服务 修改总收益 失败!");
            }
            //调用订单服务 查询订单信息
            Map<String, Object> orderResultMap = orderServer.getOrder(orderId);
            if (!String.valueOf(orderResultMap.get("code")).equals("20000")) {
                log.error("ORDER_NO:" + orderId + "---调用订单服务 查询订单信息 失败!");
            }
            Map<String, String> orderInquiry = (Map<String, String>) orderResultMap.get("result");
            //调用UserServer服务 查询医生手机号
            String appUserId = orderInquiry.get("seller");
            serverResult = userServer.getPhoneNumber(appUserId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用UserServer服务 查询医生手机号 失败!");
            }
            //调用ThirdServer 短信提醒医生订单结束
            serverResult = thirdServer.sendInquiryEndToDoctor(serverResult);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用ThirdServer 短信提醒医生订单结束 失败!");
            }
            //调用UserServer服务 查询weChat用户openid
            String weChatUserId = orderInquiry.get("buyer");
            serverResult = userServer.getOpenid(weChatUserId);
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用UserServer服务 查询weChat用户openid 失败!");
            }
            //调用ThirdServer 提醒公众号 订单结束
            serverResult = thirdServer.pushOrderSuccessToWechat(serverResult, orderInquiry.get("transactionId"), DateFormatUtils.format(updateTime, "yyyy-MM-dd HH:mm:ss"));
            if (serverResult.equals("ERROR")) {
                log.error("ORDER_NO:" + orderId + "---调用ThirdServer 提醒公众号 订单结束 失败!");
            }
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
