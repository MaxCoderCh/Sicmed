package com.prostate.order.controller;

import com.prostate.order.beans.OrderInquiryBean;
import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.feignService.RecordServer;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
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
        int orderPriceInt = Integer.parseInt(orderPrice.replace(".",""));
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
        orderInquiry.setOrderPrice(String.valueOf(orderPriceInt));

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
     * 公众号 查询 待支付订单 列表
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
            return querySuccessResponse(orderBeanBuilderForWeChat(orderInquiryList));
        }
        return queryEmptyResponse();

    }

    /**
     * 公众号 查询  进行中订单 列表
     *
     * @return
     */
    @GetMapping(value = "getProgressOrderList")
    public Map getProgressOrderList() {
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setBuyer(getToken());

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryProgressByParams(orderInquiry);
        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForWeChat(orderInquiryList));
        }
        return queryEmptyResponse();
    }


    /**
     * 公众号 修改 未支付订单
     *
     * @param orderId
     * @param doctorId
     * @param patientId
     * @param goodsId
     * @param orderDescription
     * @param patientArchive
     * @param orderPrice
     * @return
     */
    @PostMapping(value = "updateOrder")
    public Map updateOrder(String orderId, String doctorId, String patientId, String goodsId, String orderDescription, String patientArchive, String orderPrice) {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setId(orderId);
        orderInquiry.setDoctor(doctorId);
        orderInquiry.setPatient(patientId);
        orderInquiry.setSeller(doctorId);
        orderInquiry.setGoods(goodsId);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);
        orderInquiry.setOrderDescription(orderDescription);
        orderInquiry.setPatientArchive(patientArchive);
        orderInquiry.setOrderNumber("ORDER_NUMBER");
        orderInquiry.setOrderPrice(orderPrice);

        int result = orderInquiryService.updateSelective(orderInquiry);
        //数据插入结果 校验
        if (result > 0) {
            return updateSuccseeResponse(orderInquiry.getId());
        }
        return updateFailedResponse();
    }

    /**
     * 公众号 删除订单
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
     * 公众号 查询 已完成 订单 列表
     *
     * @return
     */
    @GetMapping(value = "getDoneOrderList")
    public Map getDoneOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setBuyer(getToken());

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryDoneByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForWeChat(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 待接受 问诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAcceptedOrderList")
    public Map getAcceptedOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 待回复 问诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAnsweredOrderList")
    public Map getAnsweredOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ANSWERED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 已完成 问诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getIsDoneOrderList")
    public Map getIsDoneOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 被拒绝 问诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getBeRejectedOrderList")
    public Map getBeRejectedOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.BE_REJECTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 全部 问诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAllOrderList")
    public Map getAllOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }



    /**
     * APP 查询 待接受 转诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAcceptedTurnOrderList")
    public Map getAcceptedTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 待回复 转诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAnsweredTurnOrderList")
    public Map getAnsweredTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ANSWERED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 已完成 转诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getIsDoneTurnOrderList")
    public Map getIsDoneTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 被拒绝 转诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getBeRejectedTurnOrderList")
    public Map getBeRejectedTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderStatus(OrderConstants.BE_REJECTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }

    /**
     * APP 查询 全部 转诊订单 列表
     *
     * @return
     */
    @GetMapping(value = "getAllTurnOrderList")
    public Map getAllTurnOrderList() {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(getToken());
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);

        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForApp(orderInquiryList));
        }
        return queryEmptyResponse();
    }


    /**
     * APP 接受 问诊订单
     *
     * @return
     */
    @PostMapping(value = "acceptedOrder")
    public Map acceptedOrder(String orderId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ANSWERED);
        orderInquiry.setDoctor(getToken());

        int i = orderInquiryService.updateSelective(orderInquiry);

        if (i > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     * APP 拒绝 问诊订单
     *
     * @return
     */
    @PostMapping(value = "rejectedOrder")
    public Map rejectedOrder(String orderId) {


        OrderInquiry orderInquiry = orderInquiryService.selectById(orderId);
        orderInquiry.setOrderStatus(OrderConstants.BE_REJECTED);
        int i = orderInquiryService.updateSelective(orderInquiry);
        String orderPrice = orderInquiry.getOrderPrice();
        if (i > 0) {
            new Thread(() -> {
                try {
                    feignService.ThirdServerRefund(orderInquiry.getTransactionId(), orderPrice);
                    String openid = feignService.UserServerGetOpenid(orderInquiry.getBuyer());
                    feignService.ThirdServerPushOrderFailedToWechat(openid, "医生拒绝了您的问诊申请!", f2y(orderPrice));
                } catch (Exception e) {
                    log.error("订单被拒绝 支线业务 执行 失败");
                    Thread.currentThread().interrupt();
                }
                Thread.currentThread().interrupt();
            }).start();
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     * APP  转诊订单
     *
     * @return
     */
    @PostMapping(value = "turnOrder")
    public Map turnOrder(String orderId, String doctorId) {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setDoctor(doctorId);
        orderInquiry.setSeller(doctorId);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        int i = orderInquiryService.updateSelective(orderInquiry);

        if (i > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }


    /**
     * APP 根据患者ID查询订单 列表
     *
     * @return
     */
    @GetMapping(value = "getOrderListByPatient")
    public Map getOrderListByPatient(String patientId) {
        if (StringUtils.isBlank(patientId)){
            return emptyParamResponse();
        }
        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setPatient(patientId);
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);

        List<OrderInquiry> orderInquiryList = orderInquiryService.queryByParams(orderInquiry);
        if (orderInquiryList != null && orderInquiryList.size() > 0) {
            return querySuccessResponse(orderBeanBuilderForWeChat(orderInquiryList));
        }
        return queryEmptyResponse();

    }


    /**
     * 公众号 订单列表 数据 构建
     *
     * @param orderInquiryList
     * @return
     */

    private List<OrderInquiryBean> orderBeanBuilderForWeChat(List<OrderInquiry> orderInquiryList) {

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

    /**
     * APP 订单列表 数据 构建
     *
     * @param orderInquiryList
     * @return
     */

    private List<OrderInquiryBean> orderBeanBuilderForApp(List<OrderInquiry> orderInquiryList) {

        List<OrderInquiryBean> orderInquiryBeanList = new ArrayList<>();
        StringBuilder patientIds = new StringBuilder();
        int i = 0;
        for (OrderInquiry inquiry : orderInquiryList) {

            OrderInquiryBean orderInquiryBean = new OrderInquiryBean();
            orderInquiryBean.setId(inquiry.getId());
            orderInquiryBean.setOrderStatus(inquiry.getOrderStatus());
            orderInquiryBean.setOrderDescription(inquiry.getOrderDescription());
            orderInquiryBean.setPatientId(inquiry.getPatient());
            orderInquiryBean.setCreateTime(inquiry.getCreateTime());
            orderInquiryBean.setOrderPrice(f2y(inquiry.getOrderPrice()));

            orderInquiryBeanList.add(orderInquiryBean);
            if (i != 0) {
                patientIds.append(",");
            }
            patientIds.append(inquiry.getPatient());
            i++;
        }
        Map<String, Object> patientResult = recordServer.getPatientListByIds(patientIds.toString());
        for (OrderInquiryBean orderInquiryBean : orderInquiryBeanList) {
            List<LinkedHashMap<String, String>> weChatPatientBeanList = (List<LinkedHashMap<String, String>>) patientResult.get("result");
            for (LinkedHashMap<String, String> patientLinkedHashMap : weChatPatientBeanList) {
                if (patientLinkedHashMap.get("id").equals(orderInquiryBean.getPatientId())) {
                    orderInquiryBean.setPatientSex(patientLinkedHashMap.get("patientSex"));
                    orderInquiryBean.setPatientAge(patientLinkedHashMap.get("patientAge"));
                }
            }
        }
        return orderInquiryBeanList;
    }

    public static String f2y(String balance) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isBlank(balance)) {
            stringBuffer.append("0.00");
        } else if (balance.length() > 2) {
            stringBuffer.append(balance);
            stringBuffer.insert(stringBuffer.length() - 2, ".");
        } else if (balance.length() == 2) {
            stringBuffer.append("0.");
            stringBuffer.append(balance);
        } else if (balance.length() == 1) {
            stringBuffer.append("0.0");
            stringBuffer.append(balance);
        }
        return stringBuffer.toString();
    }

    /**
     * StatisticServer 查询 待接受 问诊订单 数量
     */
    @GetMapping(value = "getAcceptedOrderCount")
    public String getAcceptedOrderCount(String userId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(userId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_INQUIRY_TYPE);

        String acceptedOrderCount = orderInquiryService.countByParams(orderInquiry);

        if (StringUtils.isBlank(acceptedOrderCount)) {
            return "0";
        }
        return acceptedOrderCount;
    }

    /**
     * StatisticServer 查询 待接受 问诊订单 数量
     */
    @GetMapping(value = "getAcceptedTurnOrderCount")
    public String getAcceptedTurnOrderCount(String userId) {

        OrderInquiry orderInquiry = new OrderInquiry();

        orderInquiry.setDoctor(userId);
        orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
        orderInquiry.setOrderType(OrderConstants.PICTURE_TURN_TYPE);

        String acceptedTurnOrderCount = orderInquiryService.countByParams(orderInquiry);

        if (StringUtils.isBlank(acceptedTurnOrderCount)) {
            return "0";
        }
        return acceptedTurnOrderCount;
    }

}
