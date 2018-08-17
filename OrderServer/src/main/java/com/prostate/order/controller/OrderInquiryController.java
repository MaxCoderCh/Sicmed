package com.prostate.order.controller;

import com.prostate.order.entity.GroupOutId;
import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 创建订单
     */
    @PostMapping(value = "createOrder")
    public Map createOrder(@Validated({GroupOutId.class}) OrderInquiry orderInquiry) {

        //数据插入对象 赋值
        orderInquiry.setCreateUser(getToken());
        //调用insert 服务 向数据库插入数据
        int result = orderInquiryService.insertSelective(orderInquiry);
        //数据插入结果 校验
        if (result > 0) {
            return insertSuccseeResponse(orderInquiry);
        }
        return insertFailedResponse();
    }


    /**
     * 拒绝订单
     */

    @GetMapping(value = "refuseOrderOrder")
    public Map refuseOrder(String orderId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.IS_REFUSE);

        orderInquiryService.updateSelective(orderInquiry);

        return null;

    }

    /**
     * 接收订单
     */
    @GetMapping(value = "acceptedOrder")
    public Map acceptedOrder(String orderId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ANSWERED);

        orderInquiryService.updateSelective(orderInquiry);

        return null;

    }

    /**
     * 回复订单
     */
    @GetMapping(value = "answerOrder")
    public Map answerOrder(String orderId, String doctorResponse) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setDoctorResponse(doctorResponse);
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);

        orderInquiryService.updateSelective(orderInquiry);

        return null;

    }

    /**
     * 回复订单
     */
    @GetMapping(value = "draftOrder")
    public Map draftOrder(String orderId, String doctorResponse) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setDoctorResponse(doctorResponse);

        orderInquiryService.updateSelective(orderInquiry);

        return null;

    }

    /**
     * 查询待支付的订单
     */
    @GetMapping(value = "getUnpaidOrderList")
    public Map getUnpaidOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setOrderStatus(OrderConstants.TO_BE__PAYMENT);
        orderInquiry.setCreateUser(getToken());
        orderInquiryService.selectByParams(orderInquiry);
        return null;

    }

    /**
     * 查询待接收的 问诊 订单
     */
    @GetMapping(value = "getUnReceiveOrderList")
    public Map getUnReceiveOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderStatus(OrderConstants.INQUIRY_TYPE);
        orderInquiry.setDoctorId(getToken());

        orderInquiryService.selectByParams(orderInquiry);

        return null;

    }

    /**
     * 查询被拒绝的订单
     */
    @GetMapping(value = "getRefuseOrderList")
    public Map getRefuseOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setOrderStatus(OrderConstants.IS_REFUSE);
        orderInquiryService.selectByParams(orderInquiry);
        return null;
    }

    /**
     * 查询已完成的订单
     */
    @GetMapping(value = "getDoneOrderList")
    public Map getDoneOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        orderInquiry.setDoctorId(getToken());
        orderInquiryService.selectByParams(orderInquiry);
        return null;
    }

    /**
     * 查询待补充信息的订单
     */
    @GetMapping(value = "getAddOrderList")
    public Map getAddOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ADD);
        orderInquiryService.selectByParams(orderInquiry);
        return null;
    }




}
