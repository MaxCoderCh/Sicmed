package com.sicmed.statistic.service.impl;

import com.sicmed.statistic.feignService.OrderServer;
import com.sicmed.statistic.feignService.RecordServer;
import com.sicmed.statistic.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class FeignServiceImpl implements FeignService {

    @Autowired
    private OrderServer orderServer;
    @Autowired
    private RecordServer recordServer;
    private final static String SUCCESS_CODE = "20000";
    private final static String ERROR_CODE = "ERROR";

    /***************************** OrderServer *****************************/
    /**
     * 根据医生ID 查询 未接受问诊订单数量 服务
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public String OrderServerGetAcceptedOrderCount(String userId) throws Exception {

        String serverResult = orderServer.getAcceptedOrderCount(userId);
        if (ERROR_CODE.equals(serverResult)) {
            log.info("根据USER ID:" + userId + " 查询 未接受问诊订单数量 失败");
            throw new Exception("");
        } else {
            log.info("根据USER ID:" + userId + " 查询 未接受问诊订单数量 成功");
            return serverResult;
        }
    }

    /**
     * 根据医生ID 查询 未接受转诊订单数量 服务
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public String OrderServerGetAcceptedTurnOrderCount(String userId) throws Exception {

        String serverResult = orderServer.getAcceptedTurnOrderCount(userId);
        if (ERROR_CODE.equals(serverResult)) {
            log.info("根据USER ID:" + userId + " 查询 未接受转诊订单数量 失败");
            throw new Exception("");
        } else {
            log.info("根据USER ID:" + userId + " 查询 未接受转诊订单数量 成功");
            return serverResult;
        }
    }

    /***************************** RecordServer *****************************/

    /**
     * 根据医生ID 查询 未接受转诊患者数量 服务
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public String OrderServerGetAcceptedTurnPatientCount(String userId) throws Exception {

        String serverResult = recordServer.getAcceptedTurnPatientCount(userId);
        if (ERROR_CODE.equals(serverResult)) {
            log.info("根据USER ID:" + userId + " 查询 未接受转诊患者数量 失败");
            throw new Exception("");
        } else {
            log.info("根据USER ID:" + userId + " 查询 未接受转诊患者数量 成功");
            return serverResult;
        }
    }
}
