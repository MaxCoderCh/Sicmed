package com.prostate.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.WeChatUser;
import com.prostate.doctor.service.WeChatOauthService;
import com.prostate.doctor.service.WeChatUserService;
import com.prostate.doctor.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "weChat")
public class WeChatLoginController extends BaseController {

    @Autowired
    private WeChatOauthService weChatOauthService;

    @Autowired
    private WeChatUserService wechatUserService;

    @Autowired
    private RedisSerive redisSerive;


    /**
     * 微信端获取用户信息
     *
     * @return
     */
    @GetMapping(value = "getUserInfo")
    public Map getPatientDetailByToken() {
        WeChatUser wechatUser = redisSerive.getWechatUser();
        return querySuccessResponse(wechatUser);
    }

    /**
     * 微信端 获取 医患关系 绑定 二维码 (过期时间5分钟)
     *
     * @return
     */
    @GetMapping(value = "getQRCode")
    public Map getQRCode() {

        String cacheId = RandomStringUtils.randomNumeric(6);

        redisSerive.insert(cacheId, getToken(), 60 * 5);

        return querySuccessResponse(cacheId);
    }


    @RequestMapping(value = "oauth")
    public Map redirect(String code) {

        if (code == null || "".equals(code)) {
            return emptyParamResponse();
        }
        //获取ACCESS_TOKEN
        Map<String, Object> resultMap = weChatOauthService.getAccessToken(code);
        String refreshToken = String.valueOf(resultMap.get("refresh_token"));
        //刷新ACCESS_TOKEN
        resultMap = weChatOauthService.refreshAccessToken(refreshToken);

        String accessToken = String.valueOf(resultMap.get("access_token"));
        String openid = String.valueOf(resultMap.get("openid"));

        //获取 用户信息
        Map<String, Object> wechatUserInfoMap = weChatOauthService.getUserInfo(accessToken,openid);

        //保存用户信息
        WeChatUser wechatUser = wechatUserService.selectByOpenid(openid);
        if (wechatUser != null) {
            String nickname = filterEmoji(String.valueOf(wechatUserInfoMap.get("nickname")));
            wechatUser.setNickName(nickname);
            wechatUser.setHeadImgUrl(String.valueOf(wechatUserInfoMap.get("headimgurl")));
            wechatUser.setAccessToken(accessToken);
            wechatUser.setRefreshToken(refreshToken);
            wechatUserService.updateSelective(wechatUser);
            //shiro 登陆授权
            String token = wechatUser.getId();
            JSONObject.toJSONString(wechatUser);
            redisSerive.insert(token, JSONObject.toJSONString(wechatUser));
            return insertSuccseeResponse(token);
//                response.sendRedirect("http://www.sicmed.cn:6601/chestnut/index.html?" + token);
        }
        wechatUser = new WeChatUser();
        wechatUser.setOpenid(openid);
        String nickname = filterEmoji(String.valueOf(wechatUserInfoMap.get("nickname")));
        wechatUser.setNickName(nickname);
        wechatUser.setHeadImgUrl(String.valueOf(wechatUserInfoMap.get("headimgurl")));
        wechatUser.setAccessToken(accessToken);
        wechatUser.setRefreshToken(refreshToken);
        log.info(wechatUser.toString());
        wechatUserService.insertSelective(wechatUser);
        //shiro 登陆授权
        String token = wechatUser.getId();
        JSONObject.toJSONString(wechatUser);
        redisSerive.insert(token, JSONObject.toJSONString(wechatUser));
        return insertSuccseeResponse(token);

    }

    @PostMapping(value = "appAdd")
    public Map appAdd(String jsonStr) {

        Map<String, Object> wechatUserInfoMap = (Map<String, Object>) JsonUtils.jsonToObj(jsonStr);

        //保存用户信息
        String openid = wechatUserInfoMap.get("openid").toString();
        WeChatUser wechatUser = wechatUserService.selectByOpenid(openid);
        if (wechatUser != null) {
            return insertSuccseeResponse(wechatUser);
        }
        wechatUser = new WeChatUser();
        wechatUser.setOpenid(openid);
        String nickname = filterEmoji(wechatUserInfoMap.get("nickname").toString());
        wechatUser.setNickName(nickname);
        wechatUser.setId(getToken());
        wechatUser.setHeadImgUrl(wechatUserInfoMap.get("headimgurl").toString());
        wechatUserService.insertSelectiveById(wechatUser);
        return insertSuccseeResponse(wechatUser);

    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

}