package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.WeChatUser;
import com.prostate.doctor.mapper.master.WeChatUserWriteMapper;
import com.prostate.doctor.mapper.slaver.WeChatUserReadMapper;
import com.prostate.doctor.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeChatUserServiceImpl implements WeChatUserService {

    @Autowired
    private WeChatUserWriteMapper weChatUserWriteMapper;
    @Autowired
    private WeChatUserReadMapper weChatUserReadMapper;

    @Override
    public int insertSelective(WeChatUser wechatUser) {
        return weChatUserWriteMapper.insertSelective(wechatUser);
    }

    @Override
    public int updateSelective(WeChatUser wechatUser) {
        return weChatUserWriteMapper.updateSelective(wechatUser);
    }

    @Override
    public WeChatUser selectById(String id) {
        return weChatUserReadMapper.selectById(id);
    }

    @Override
    public List<WeChatUser> selectByParams(WeChatUser wechatUser) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public WeChatUser selectByOpenid(String openid) {
        return weChatUserReadMapper.selectByOpenid(openid);
    }

    @Override
    public int insertSelectiveById(WeChatUser wechatUser) {
        return weChatUserWriteMapper.insertSelectiveById(wechatUser);
    }

    @Override
    public String getOpenidById(String userId) {
        return weChatUserReadMapper.getOpenidById(userId);
    }
}
