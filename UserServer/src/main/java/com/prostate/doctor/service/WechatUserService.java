package com.prostate.doctor.service;

import com.prostate.doctor.entity.WeChatUser;
import org.springframework.stereotype.Service;

@Service
public interface WeChatUserService extends BaseService<WeChatUser>{
    WeChatUser selectByOpenid(String openid);

    int insertSelectiveById(WeChatUser wechatUser);

    String getOpenidById(String userId);
}
