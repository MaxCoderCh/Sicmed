package com.prostate.stata.controller;

import com.prostate.stata.entity.Docket;
import com.prostate.stata.entity.DocketConstant;
import com.prostate.stata.service.DocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "docket")
public class DocketController extends BaseController {

    @Autowired
    private DocketService docketService;


    /**
     * 添加 患者标签
     *
     * @param docketName
     * @return
     */
    @PostMapping(value = "addPatientDocket")
    public Map addPatientDocket(String docketName) {
        Docket docket = new Docket();
        docket.setDocketName(docketName);
        docket.setDocketType(DocketConstant.DOCKET_PATIENT);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        int i = docketService.insertSelective(docket);
        if (i > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 添加 价格标签
     *
     * @param docketName
     * @return
     */
    @PostMapping(value = "addPriceDocket")
    public Map addPriceDocket(String docketName) {
        Docket docket = new Docket();
        docket.setDocketName(docketName);
        docket.setDocketType(DocketConstant.DOCKET_PRICE);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        int i = docketService.insertSelective(docket);
        if (i > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 添加 问诊标签
     *
     * @param docketName
     * @return
     */
    @PostMapping(value = "addInquiryDocket")
    public Map addInquiryDocket(String docketName) {
        Docket docket = new Docket();
        docket.setDocketName(docketName);
        docket.setDocketType(DocketConstant.DOCKET_INQUIRY);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        int i = docketService.insertSelective(docket);
        if (i > 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 查询 患者标签 列表
     *
     * @return
     */
    @PostMapping(value = "getPatientDocketList")
    public Map getPatientDocketList() {
        Docket docket = new Docket();
        docket.setDocketType(DocketConstant.DOCKET_PATIENT);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        List<Docket> docketList = docketService.selectByParams(docket);
        if (docketList == null || docketList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(docketList);
    }

    /**
     * 查询 价格标签 列表
     *
     * @return
     */
    @PostMapping(value = "getPriceDocketList")
    public Map getPriceDocketList() {
        Docket docket = new Docket();
        docket.setDocketType(DocketConstant.DOCKET_PRICE);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        List<Docket> docketList = docketService.selectByParams(docket);
        if (docketList == null || docketList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(docketList);
    }

    /**
     * 查询 问诊标签 列表
     *
     * @return
     */
    @PostMapping(value = "getInquiryDocketList")
    public Map getInquiryDocketList() {
        Docket docket = new Docket();
        docket.setDocketType(DocketConstant.DOCKET_INQUIRY);
        docket.setDocketStatus(DocketConstant.USABLE);
        docket.setCreateUser(getToken());
        List<Docket> docketList = docketService.selectByParams(docket);
        if (docketList == null || docketList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(docketList);
    }


}
