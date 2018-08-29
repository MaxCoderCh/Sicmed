package com.prostate.pay.wxpay.sdk;


import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyWeChatPayConfig extends WXPayConfig {

    private byte[] certData;

    private String appid;
    private String mchid;
    private String key;

    public MyWeChatPayConfig(String appid, String mchid, String key, String certPath) throws Exception {
        this.appid = appid;
        this.mchid = mchid;
        this.key = key;
        ClassPathResource resource = new ClassPathResource("cert/200b180e881611e8a09b68cc6e5c9c74.p12");
        InputStream certStream = resource.getInputStream();

        this.certData = new byte[certStream.available()];
        certStream.read(this.certData);
        certStream.close();


    }

    @Override
    public String getAppID() {
        return appid;
    }

    @Override
    public String getMchID() {
        return mchid;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
