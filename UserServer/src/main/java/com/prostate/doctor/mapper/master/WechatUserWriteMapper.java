package com.prostate.doctor.mapper.master;

import com.prostate.doctor.entity.WeChatUser;

public interface WeChatUserWriteMapper extends BaseWriteMapper<WeChatUser>{

    int insertSelectiveById(WeChatUser wechatUser);
}