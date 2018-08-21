package com.prostate.order.controller;

import com.prostate.order.beans.OrderInquiryBean;
import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.feignService.RecordServer;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    private RecordServer recordServer;
    @Autowired
    public OrderInquiryController(OrderInquiryService orderInquiryService) {
        this.orderInquiryService = orderInquiryService;
    }


    /**
     * 1.创建订单
     */
    @PostMapping(value = "createOrder")
    public Map createOrder(String doctorId, String patientId, String goodsId, String orderDescription, String patientArchive, String orderPrice) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(doctorId);
        orderInquiry.setPatient(patientId);
        orderInquiry.setBuyer(getToken());
        orderInquiry.setSeller(doctorId);
        orderInquiry.setGoods(goodsId);
        orderInquiry.setCreateUser(getToken());
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_PAYMENT);
        orderInquiry.setOrderDescription(orderDescription);
        orderInquiry.setPatientArchive(patientArchive);
        orderInquiry.setOrderNumber("ORDER_NUMBER");
        orderInquiry.setOrderPrice(orderPrice);

        //调用insert 服务 向数据库插入数据
        int result = orderInquiryService.insertSelective(orderInquiry);
        //数据插入结果 校验
        if (result > 0) {
            return insertSuccseeResponse(orderInquiry.getId());
        }
        return insertFailedResponse();
    }

    /**
     * 2.查询订单
     */
    @PostMapping(value = "getOrder")
    public Map getOrder(String orderId) {

        OrderInquiry orderInquiry = orderInquiryService.selectById(orderId);
        if (orderInquiry == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(orderInquiry);
    }

    /**
     * 3.查询订单
     */
    @PostMapping(value = "paymentSuccess")
    public Map paymentSuccess() {


        return null;
    }

    /**
     * 查询待支付订单 列表
     *
     * @return
     */
    @GetMapping(value = "getPayOrderList")
    public Map getPayOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setBuyer(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_PAYMENT);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);
        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilder(orderInquiryList));
        }
        return queryEmptyResponse();

    }

    /**
     * 查询  进行中 订单 列表
     *
     * @return
     */
    @GetMapping(value = "getProgressOrderList")
    public Map getProgressOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setBuyer(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ANSWERED);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);
        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilder(orderInquiryList));
        }
        return queryEmptyResponse();
    }


    @PostMapping(value = "updateOrder")
    public Map updateOrder(String orderId, String orderDescription, String patientArchive) {

        return null;
    }

    /**
     * 删除订单
     */
    @PostMapping(value = "removeOrder")
    public Map updateOrder(String orderId) {
        int i = orderInquiryService.deleteById(orderId);

        if (i > 0) {
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }

    /**
     * 查询 已完成 订单 列表
     *
     * @return
     */
    @GetMapping(value = "getDoneOrderList")
    public Map getDoneOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setBuyer(getToken());
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilder(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    private List<OrderInquiryBean> orderBeanBuilder(List<OrderInquiry> orderInquiryList) {

        List<OrderInquiryBean> orderInquiryBeanList = new ArrayList<>();
        for (OrderInquiry inquiry : orderInquiryList) {
            Map<String, Object> patientResult = recordServer.getPatientListById(getToken());
            List<LinkedHashMap<String, String>> weChatPatientBeanList = (List<LinkedHashMap<String, String>>) patientResult.get("result");
            OrderInquiryBean orderInquiryBean = new OrderInquiryBean();

            for (LinkedHashMap<String, String> patientLinkedHashMap : weChatPatientBeanList) {
                if (patientLinkedHashMap.get("id").equals(inquiry.getPatient())) {
                    orderInquiryBean.setPatientName(patientLinkedHashMap.get("patientName"));
                    orderInquiryBean.setPatientSex(patientLinkedHashMap.get("patientSex"));
                    orderInquiryBean.setPatientAge(patientLinkedHashMap.get("patientAge"));
                }
            }

            orderInquiryBean.setId(inquiry.getId());
            orderInquiryBean.setOrderStatus(inquiry.getOrderStatus());
            orderInquiryBean.setOrderType(inquiry.getOrderType());
            orderInquiryBean.setOrderDescription(inquiry.getOrderDescription());

            orderInquiryBeanList.add(orderInquiryBean);
        }
        return orderInquiryBeanList;
    }

}
