package com.prostate.order.controller;

import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 问诊订单
 */
@Slf4j
@RestController
@RequestMapping(value = "order/inquiry")
public class OrderInquiryController extends BaseController {

    private final OrderInquiryService orderInquiryService;

    @Autowired
    public OrderInquiryController(OrderInquiryService orderInquiryService) {
        this.orderInquiryService = orderInquiryService;
    }


    /**
     * 1.创建订单
     */
    @PostMapping(value = "createOrder")
    public Map createOrder(String doctorId, String patientId, String goodsId,String orderDescription,String patientArchive) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(doctorId);
        orderInquiry.setPatient(patientId);
        orderInquiry.setBuyer(getToken());
        orderInquiry.setSeller(doctorId);
        orderInquiry.setGoods(goodsId);
        orderInquiry.setCreateUser(getToken());
        orderInquiry.setOrderType(OrderConstants.INQUIRY_TYPE);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_PAYMENT);
        orderInquiry.setOrderDescription("ORDER_DESCRIPTION");
        orderInquiry.setPatientArchive("PATIENT_ARCHIVE");
        orderInquiry.setOrderNumber("ORDER_NUMBER");

        //调用insert 服务 向数据库插入数据
        int result = orderInquiryService.insertSelective(orderInquiry);
        //数据插入结果 校验
        if (result > 0) {
            return insertSuccseeResponse(orderInquiry.getId());
        }
        return insertFailedResponse();
    }


}
