package com.prostate.doctor.entity;

import lombok.Data;

import java.util.Date;


@Data
public class WeChatUser {

    private String id;

    private String patientId;

    private String openid;

    private String nickName;

    private String headImgUrl;

    private String accessToken;

    private String refreshToken;

    private Date createTime;

}