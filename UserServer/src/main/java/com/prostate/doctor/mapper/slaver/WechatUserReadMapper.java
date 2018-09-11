package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.WeChatUser;

public interface WeChatUserReadMapper extends BaseReadMapper<WeChatUser>{

    WeChatUser selectByOpenid(String openid);

    String getOpenidById(String userId);
}