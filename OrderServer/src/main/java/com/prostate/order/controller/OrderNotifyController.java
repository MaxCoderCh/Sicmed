package com.prostate.order.controller;

import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "order/notify")
public class OrderNotifyController extends BaseController {
    @Autowired
    private OrderInquiryService orderInquiryService;

    /**
     * 支付成功 通知 接口
     */
    @PostMapping(value = "paymentSuccess")
    public String paymentSuccess(HttpServletRequest request) throws Exception {
        // 解析结果存储在HashMap
        Map<String, String> resultMap = new HashMap();
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            resultMap.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        //获取微信 返回 数据
        String return_code = resultMap.get("return_code");
        String out_trade_no = resultMap.get("out_trade_no");
        String transactionId = resultMap.get("transaction_id");
        String orderPrice = resultMap.get("total_fee");
        String openid = resultMap.get("openid");

        //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败
        if (return_code.equals("SUCCESS")) {
            OrderInquiry orderInquiry = orderInquiryService.selectById(out_trade_no);
            orderInquiry.setOrderStatus(OrderConstants.TO_BE_ACCEPTED);
            orderInquiry.setTransactionId(transactionId);
            orderInquiryService.updateSelective(orderInquiry);
            //通知公众号端 支付成功
            thirdServer.pushPaymentSuccessToWechat(openid, transactionId, "图文问诊服务", f2y(orderPrice));
            return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        } else {
            //支付失败的业务逻辑

            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[error]]></return_msg>" + "</xml> ";
        }

    }

    /**
     * 订单完成 通知 接口
     */
    @PostMapping(value = "orderDoneSuccess")
    public String orderDoneSuccess(String orderId) {
        OrderInquiry orderInquiry = orderInquiryService.selectById(orderId);
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        Date updateTime = new Date();
        orderInquiry.setUpdateTime(updateTime);
        int i = orderInquiryService.updateSelective(orderInquiry);
        if (i > 0) {
            return "SUCCESS";
        }
        log.error("订单完成状态修改失败");
        return "ERROR";
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

}
