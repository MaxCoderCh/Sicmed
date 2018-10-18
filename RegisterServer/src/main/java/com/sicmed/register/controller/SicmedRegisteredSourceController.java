package com.sicmed.register.controller;

import com.sicmed.register.base.BaseController;
import com.sicmed.register.cache.redis.RedisSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author: ykbian
 * @Date: 2018/9/19 11:35
 * @Todo:  号源信息控制
 */
@Controller
public class SicmedRegisteredSourceController  extends BaseController {


   @Autowired
   private RedisSerive redisSerive;
    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 11:36
     *@Description:  每日凌晨4时更新号源信息
     *@param:
    */
    @Scheduled(cron="0 0 4 * * ? ")
    public void initialize(){
        //读取每个科室每个类型的号源信息，存储在缓存中
        /**
         *  key = 科室id+医生id
         *  value =从排班信息中获取的号源数据
         */
    }

    /**
     *  测试缓存
     */
   @ResponseBody
   @GetMapping("test")
    public void test(){
        String key1 = "9fb88335bc7d11e89c1500163e000a60"+"f8f0062041cb47508a2ff2d33fc94fe5";
        String v1 = 3+"";
        String key2 = "ce23fae5bbd611e89c1500163e000a60"+"f8f0062041cb47508a2ff2d33fc94fe5";
        String v2 = 3+"";
        redisSerive.set(key1,v1);
        redisSerive.set(key2,v2);
        logger.info("号源信息缓存完成");
    }

}
