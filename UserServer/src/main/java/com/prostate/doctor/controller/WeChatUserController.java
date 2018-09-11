package com.prostate.doctor.controller;

import com.prostate.doctor.service.WeChatUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "user/weChat")
public class WeChatUserController extends BaseController{

    @Autowired
    private WeChatUserService weChatUserService;

    /**
     * Provider
     */
    @GetMapping(value = "getOpenid")
    public Map getOpenid(String userId) {

        String phoneNumber = weChatUserService.getOpenidById(userId);

        if (StringUtils.isBlank(phoneNumber)) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(phoneNumber);
    }
}
