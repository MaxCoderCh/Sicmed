package com.prostate.stata.controller;

import com.prostate.stata.beans.IpssBean;
import com.prostate.stata.entity.Ipss;
import com.prostate.stata.service.IpssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "ipss")
public class IpssController extends BaseController{

    @Autowired
    private IpssService ipssService;

    @PostMapping(value = "getAll")
    public Map getAll(){
        log.info("##########查询IPSS评估项列表###########");
        Ipss ipss = new Ipss();
        List<IpssBean> ipssBeanList =  ipssService.selectByParamss(ipss);
        //查询结果校验 组装 响应
        log.info(ipssBeanList.toString());
        if (ipssBeanList != null||ipssBeanList.size()>0) {
            return querySuccessResponse(ipssBeanList);
        }
        return queryEmptyResponse();
    }

}
