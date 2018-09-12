package com.prostate.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.prostate.http.service.HttpClientService;
import com.prostate.http.service.impl.HttpClientServiceImpl;
import com.prostate.oauth.service.WeChatOauthService;
import com.prostate.push.entity.TemplateEntity;
import com.prostate.push.entity.WechatTemplateEntity;
import com.prostate.redis.RedisSerive;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "push/weChat")
public class WeChatPushController {


    @Autowired
    private WeChatOauthService weChatOauthService;

    @Autowired
    private RedisSerive redisSerive;

    @Autowired
    private HttpClientService httpClientService;

    private final String SEND_TEMPLAYE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";


    /**
     * 订单支付成功通知
     *
     * @param openid
     * @param orderId
     * @param goodsInfo
     * @param orderPrice
     */
    @PostMapping(value = "pushPaymentSuccessToWechat")
    public String pushPaymentSuccessToWechat(String openid, String orderId, String goodsInfo, String orderPrice) {
        WechatTemplateEntity wechatTemplate = new WechatTemplateEntity();
        wechatTemplate.setTemplate_id("7TNKTZLm4GF9iZp8u0Oh-lifn9WLa6o4pt_JqH1lRE0");
        wechatTemplate.setTouser(openid);
//        wechatTemplate.setUrl("http://music.163.com/#/song?id=27867140");

        String accessToken = redisSerive.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            accessToken = weChatOauthService.getAccessToken();
            redisSerive.setAccessToken(accessToken);
        }

        Map<String, TemplateEntity> m = new HashMap();
        TemplateEntity first = new TemplateEntity();
        first.setColor("#000000");
        first.setValue("您的订单已支付成功！");
        m.put("first", first);

        TemplateEntity keyword1 = new TemplateEntity();
        keyword1.setColor("#000000");
        keyword1.setValue(orderId);
        m.put("keyword1", keyword1);

        TemplateEntity keyword2 = new TemplateEntity();
        keyword2.setColor("#000000");
        keyword2.setValue(goodsInfo);
        m.put("keyword2", keyword2);

        TemplateEntity keyword3 = new TemplateEntity();
        keyword3.setColor("#000000");
        keyword3.setValue(orderPrice + " 元");
        m.put("keyword3", keyword3);

        TemplateEntity remark = new TemplateEntity();
        remark.setColor("#000000");
        remark.setValue("成功支付图文问诊费用，请耐心等待医生回复后，可通过栗子医学微信公众号查看问诊结果。祝您早日康复，有问题请致电010-63786220。");
        m.put("remark", remark);
        wechatTemplate.setData(m);
        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);

        String jsonString = JSONObject.toJSONString(wechatTemplate);

        String string = httpClientService.HttpPostWithJson(requestUrl, jsonString);

        Map<String, Object> resultMap = JSONObject.parseObject(string);

        String errcode = String.valueOf(resultMap.get("errcode"));

        if ("0".equals(errcode)){
            return "SUCCESS";
        }
        return "FAILED";

    }

    /**
     *     订单完成通知
     * GU4ICsPq4J_4HlkqELASgURzisDwMsL2yqAb3othnjI
     *
     * {{first.DATA}}
     订单编号：{{keyword1.DATA}}
     完成时间：{{keyword2.DATA}}
     {{remark.DATA}}
     */
    /**
     * 订单完成通知
     *
     * @param openid
     * @param orderId
     */
    @PostMapping(value = "pushOrderSuccessToWechat")
    public String pushOrderSuccessToWechat(String openid, String orderId, String finishTime) {
        WechatTemplateEntity wechatTemplate = new WechatTemplateEntity();
        wechatTemplate.setTemplate_id("GU4ICsPq4J_4HlkqELASgURzisDwMsL2yqAb3othnjI");
        wechatTemplate.setTouser(openid);
        //wechatTemplate.setUrl("http://music.163.com/#/song?id=27867140");

        String accessToken = redisSerive.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            accessToken = weChatOauthService.getAccessToken();
            redisSerive.setAccessToken(accessToken);
        }

        Map<String, TemplateEntity> m = new HashMap();
        TemplateEntity first = new TemplateEntity();
        first.setColor("#000000");
        first.setValue("您的问诊订单已成功！");
        m.put("first", first);

        TemplateEntity keyword1 = new TemplateEntity();
        keyword1.setColor("#000000");
        keyword1.setValue(orderId);
        m.put("keyword1", keyword1);

        TemplateEntity keyword2 = new TemplateEntity();
        keyword2.setColor("#000000");
        keyword2.setValue(finishTime);
        m.put("keyword2", keyword2);


        TemplateEntity remark = new TemplateEntity();
        remark.setColor("#000000");
        remark.setValue("感谢您的使用！");
        m.put("remark", remark);
        wechatTemplate.setData(m);

        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);

        String jsonString = JSONObject.toJSONString(wechatTemplate);
        String string = httpClientService.HttpPostWithJson(requestUrl, jsonString);

        Map<String, Object> resultMap = JSONObject.parseObject(string);

        String errcode = String.valueOf(resultMap.get("errcode"));

        if ("0".equals(errcode)){
            return "SUCCESS";
        }
        return "FAILED";

    }

    /**
     * 退款通知
     *
     * @param openid
     * @param refundInfo
     * @param refundPrice
     */
    @PostMapping(value = "pushOrderFailedToWechat")
    public String pushOrderFailedToWechat(String openid, String refundInfo, String refundPrice) {
        WechatTemplateEntity wechatTemplate = new WechatTemplateEntity();
        wechatTemplate.setTemplate_id("cIOkqBPcDYLlK60dICF5ZhDh2tiJNB9yllRfA5Iy-kQ");
        wechatTemplate.setTouser(openid);
        //wechatTemplate.setUrl("http://music.163.com/#/song?id=27867140");

        String accessToken = redisSerive.getAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            accessToken = weChatOauthService.getAccessToken();
            redisSerive.setAccessToken(accessToken);
        }

        Map<String, TemplateEntity> m = new HashMap();
        TemplateEntity first = new TemplateEntity();
        first.setColor("#000000");
        first.setValue("您好，您问诊申请未成功，已退款。");
        m.put("first", first);

        TemplateEntity keyword1 = new TemplateEntity();
        keyword1.setColor("#000000");
        keyword1.setValue(refundInfo);
        m.put("reason", keyword1);

        TemplateEntity keyword2 = new TemplateEntity();
        keyword2.setColor("#000000");
        keyword2.setValue(refundPrice + " 元");
        m.put("refund", keyword2);


        TemplateEntity remark = new TemplateEntity();
        remark.setColor("#000000");
        remark.setValue("如有疑问，请致电010-63786220联系我们，或回复M来了解详情。");
        m.put("remark", remark);
        wechatTemplate.setData(m);

        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);

        String jsonString = JSONObject.toJSONString(wechatTemplate);

        String string = httpClientService.HttpPostWithJson(requestUrl, jsonString);

        Map<String, Object> resultMap = JSONObject.parseObject(string);

        String errcode = String.valueOf(resultMap.get("errcode"));

        if ("0".equals(errcode)){
            return "SUCCESS";
        }
        return "FAILED";

    }


}
