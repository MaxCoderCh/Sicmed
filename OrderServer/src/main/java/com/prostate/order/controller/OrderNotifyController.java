package com.prostate.order.controller;

import com.prostate.order.entity.OrderConstants;
import com.prostate.order.entity.OrderInquiry;
import com.prostate.order.service.OrderInquiryService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
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
    public String paymentSuccess(String openid, String transaction_id, HttpServletRequest request) {
        log.info("weChatPaySuccess>>>>>>>>>>>>" + transaction_id + ">>>>>>>>>>>>" + openid);
        try {
            // 解析结果存储在HashMap
            Map<String, String> resultMap = new HashMap<String, String>();
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

            String out_trade_no;
            String return_code;
            log.info(resultMap.toString());
//            {transaction_id=4200000203201809072829110107,
//                    nonce_str=3mRoFi21uWw1zFLoRUKQztGOBAjIF18x,
//                    bank_type=CFT,
//                    openid=oCL2t0fFzCzpvL3WCLy3W1_ggn6w,
//                    sign=7F1C8604A64FEF1A27762948369CD837,
//                    fee_type=CNY,
//                    mch_id=1504779411,
//                    cash_fee=1,
//                    device_info=WEB,
//                    out_trade_no=43c132f4b27211e8a09b68cc6e5c9c74,
//                    appid=wx879a26e37acadb20,
//                    total_fee=1,
//                    trade_type=JSAPI,
//                    result_code=SUCCESS,
//                    time_end=20180907154812,
//                    is_subscribe=Y,
//                    return_code=SUCCESS
//            }

            out_trade_no = resultMap.get("out_trade_no");
            return_code = resultMap.get("return_code");

            //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了. response.getWriter().master(RequestHandler.setXML("SUCCESS", ""));
            if (return_code.equals("SUCCESS")) {
                orderInquiryService.updateOrderSuccess(out_trade_no);
                //支付成功的业务逻辑
                return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                //支付失败的业务逻辑
                return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[error]]></return_msg>" + "</xml> ";
            }
        } catch (Exception e) {
            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[error]]></return_msg>" + "</xml> ";
        }
    }

    /**
     * 订单完成 通知 接口
     */
    @PostMapping(value = "orderDoneSuccess")
    public String orderDoneSuccess(String orderId) {

        OrderInquiry orderInquiry = new OrderInquiry();
        orderInquiry.setId(orderId);
        orderInquiry.setOrderStatus(OrderConstants.IS_DONE);
        int i = orderInquiryService.updateSelective(orderInquiry);
        if (i > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

}
