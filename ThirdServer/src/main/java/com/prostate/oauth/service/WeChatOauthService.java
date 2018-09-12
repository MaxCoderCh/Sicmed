package com.prostate.oauth.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeChatOauthService {

    /**
     * 公众号AppId
     */
    public static final String APP_ID = "wx879a26e37acadb20";
    /**
     * 公众号AppSecret
     */
    public static final String APP_SECRET = "cb2189ca646ed2e83069443e5444a71e";

    /**
     * 微信 Oauth2 授权 获取 access_token
     */
    public Map<String, Object> getAccessToken(String code) {
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        urlStr.append(APP_ID);
        urlStr.append("&secret=");
        urlStr.append(APP_SECRET);
        urlStr.append("&code=");
        urlStr.append(code);
        urlStr.append("&grant_type=authorization_code");

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        return JSONObject.parseObject(responseStr);
    }

    /**
     * 微信 Oauth2 授权 刷新 access_token
     * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
     */
    public Map<String, Object> refreshAccessToken(String accessToken) {

        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=");
        urlStr.append(APP_ID);
        urlStr.append("&grant_type=refresh_token&refresh_token=");
        urlStr.append(accessToken);

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        return JSONObject.parseObject(responseStr);

    }

    /**
     * 微信 Oauth2 授权 获取 用户信息
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    public Map<String, Object> getUserInfo(String accessToken, String openid) {
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        urlStr.append(accessToken);
        urlStr.append("&openid=");
        urlStr.append(openid);
        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        return JSONObject.parseObject(responseStr);
    }

    /**
     * 微信 Oauth2 授权 检验 access_token 是否有效
     * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
     */
    public Map<String, Object> checkAccessToken(String accessToken, String openid) {
        StringBuffer urlStr = new StringBuffer();
        urlStr.append("https://api.weixin.qq.com/sns/auth?access_token=");
        urlStr.append(accessToken);
        urlStr.append("&openid=");
        urlStr.append(openid);

        String responseStr = HttpsConnectionUtils.conn(urlStr.toString());
        return JSONObject.parseObject(responseStr);
    }

    /**
     * 获取公众号 的 AccessToken
     */
    public String getAccessToken() {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
        String responseStr = HttpsConnectionUtils.conn(urlStr);
        Map<String, Object> resultMap = JSONObject.parseObject(responseStr);
        String accessToken = String.valueOf(resultMap.get("access_token"));
        return accessToken;
    }
}
