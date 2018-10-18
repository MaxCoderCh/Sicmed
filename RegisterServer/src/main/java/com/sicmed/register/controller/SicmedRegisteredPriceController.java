package com.sicmed.register.controller;

import com.sicmed.register.base.BaseController;
import com.sicmed.register.base.Constant;
import com.sicmed.register.base.util.AppParameterSimulation;
import com.sicmed.register.bean.CountBean;
import com.sicmed.register.bean.RegisteredBean;
import com.sicmed.register.entity.GroupID;
import com.sicmed.register.entity.SicmedPatient;
import com.sicmed.register.entity.SicmedRegistered;
import com.sicmed.register.service.SicmedRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sicmed.register.base.util.DateTimeUtil;
import com.sicmed.register.service.SicmedPatientService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 9:24
 * @Todo:   患者缴费相关
 */
@Controller
@RequestMapping("/ehis/registeredPrice")
public class SicmedRegisteredPriceController extends BaseController{


    @Autowired
    private SicmedRegisteredService sicmedRegisteredService;

    @Autowired
    private SicmedPatientService sicmedPatientService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:25
     *@Description:  患者缴费
     *@param:
    */
    @ResponseBody
    @PostMapping("patientPay")
    public Map patientPay(@Validated(GroupID.class)RegisteredBean registeredBean){
        //修改患者的缴费状态为已经缴费
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredBean.getId());
        if (sicmedRegistered == null){
            return updateFailedResponse();
        }
        sicmedRegistered.setPriceStatus(Constant.PRICE_EXCHARGE);
        sicmedRegistered.setChargeUser(getToken());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
            return updateSuccseeResponse();
        }
       return updateFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:26
     *@Description: 患者退费====改变其缴费状态，不删除挂号信息
     *@param:
    */
    @ResponseBody
    @PostMapping("patientRefund")
    public Map patientRefund(@Validated(GroupID.class) RegisteredBean registeredBean){
        //修改患者的缴费状态为退费
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredBean.getId());
        if (sicmedRegistered == null){
            return queryEmptyResponse();
        }
        sicmedRegistered.setPriceStatus(Constant.PRICE_RETURN);
        sicmedRegistered.setRefundUser(getToken());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 14:25
     *@Description:  查询所有待缴费的患者信息(未缴费，正常挂号和改号)
     *@param:
    */
    @ResponseBody
    @GetMapping("patientNotPay")
    public Map patientNotPay(String beginTime, String endTime){
        //创建一个返回对象
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建一个查询对象
        SicmedRegistered sicmedRegistered = new SicmedRegistered();

        //如果查询时间为空号的话，则默认查询当天的数据
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getDayEndDate();
        if(beginTime != "" && beginTime !=null){
            startDate = DateTimeUtil.getDate(beginTime);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endTime)){
            endDate1 = DateTimeUtil.getDate(endTime);
        }
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setPriceStatus(Constant.PRICE_NOT_PAY);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.patientNotPay(sicmedRegistered);
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean registeredBean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
           /**
            *  此处的医生id和科室id可以直接替换成具体的信息
            */
           //患者个人信息部分
            registeredBean.setPatientSex(sicmedPatient.getPatientSex());
            registeredBean.setPatientAge(sicmedPatient.getPatientAge());
            registeredBean.setPatientNumber(sicmedPatient.getPatientNumber());
            registeredBean.setPatientName(sicmedPatient.getPatientName());
            registeredBean.setPatientCard(sicmedPatient.getPatientCard());
            registeredBean.setPatientAddress(sicmedPatient.getPatientAddress());
           // 挂号信息部分
            registeredBean.setId(registered.getId());
            registeredBean.setBranchId(registered.getBranchId());
            registeredBean.setDoctorId(registered.getDoctorId());
            registeredBean.setRegisteredTypeId(registered.getRegistrationType());
            registeredBean.setRegisteredPrice(registered.getRegisteredPrice());
            registeredBean.setPayType(registered.getPayType());
            registeredBeans.add(registeredBean);
        }
        return querySuccessResponse(registeredBeans);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 17:48
     *@Description:  查询所有已经缴费的患者======和上面的待缴费的患者列表可以合并为一个接口
     *@param:
    */
    @ResponseBody
    @GetMapping("patientIsPay")
    public Map patientIsPay(String beginTime, String endTime){
        //创建一个返回对象
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建一个查询对象
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        //如果查询时间为空号的话，则默认查询当天的数据
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getDayEndDate();
        if(beginTime != "" && beginTime !=null){
            startDate = DateTimeUtil.getDate(beginTime);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endTime)){
            endDate1 = DateTimeUtil.getDate(endTime);
        }
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setPriceStatus(Constant.PRICE_EXCHARGE);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.patientNotPay(sicmedRegistered);
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean registeredBean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            /**
             *  此处的医生id和科室id可以直接替换成具体的信息
             */
            //患者个人信息部分
            registeredBean.setPatientSex(sicmedPatient.getPatientSex());
            registeredBean.setPatientAge(sicmedPatient.getPatientAge());
            registeredBean.setPatientNumber(sicmedPatient.getPatientNumber());
            registeredBean.setPatientName(sicmedPatient.getPatientName());
            registeredBean.setPatientCard(sicmedPatient.getPatientCard());
            registeredBean.setPatientAddress(sicmedPatient.getPatientAddress());
            // 挂号信息部分
            registeredBean.setId(registered.getId());
            registeredBean.setBranchId(registered.getBranchId());
            registeredBean.setDoctorId(registered.getDoctorId());
            registeredBean.setRegisteredTypeId(registered.getRegistrationType());
            registeredBean.setRegisteredPrice(registered.getRegisteredPrice());
            registeredBean.setPayType(registered.getPayType());
            registeredBeans.add(registeredBean);
        }
        return querySuccessResponse(registeredBeans);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 18:09
     *@Description:  查询缴费（退费）记录
     *@param:
    */
    @ResponseBody
    @GetMapping("priceRecord")
    public Map priceRecord(String status,String beginTime, String endTime){
        //创建一个返回对象(带统计属性)
        CountBean countBean = new CountBean();
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        double money = 0.00;
        // 判断查询条件
        //创建一个查询对象
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        //如果查询时间为空号的话，则默认查询当天的数据
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getDayEndDate();
        if(beginTime != "" && beginTime !=null){
            startDate = DateTimeUtil.getDate(beginTime);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endTime)){
            endDate1 = DateTimeUtil.getDate(endTime);
        }
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        // 查询缴费信息
        if (status.equals(Constant.PRICE_EXCHARGE)){
            sicmedRegistered.setPriceStatus(status);
        }else if (status.equals(Constant.PRICE_RETURN)){
            sicmedRegistered.setPriceStatus(status);
        }else {
            return queryEmptyResponse();
        }
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.selectByParams(sicmedRegistered);
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean registeredBean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            //患者个人信息部分
            registeredBean.setPatientSex(sicmedPatient.getPatientSex());
            registeredBean.setPatientAge(sicmedPatient.getPatientAge());
            registeredBean.setPatientNumber(sicmedPatient.getPatientNumber());
            registeredBean.setPatientName(sicmedPatient.getPatientName());
            registeredBean.setPatientCard(sicmedPatient.getPatientCard());
            registeredBean.setPatientAddress(sicmedPatient.getPatientAddress());
            // 挂号信息部分
            registeredBean.setId(registered.getId());
            registeredBean.setBranchId(registered.getBranchId());
            registeredBean.setDoctorId(registered.getDoctorId());
            registeredBean.setRegisteredTypeId(registered.getRegistrationType());
            registeredBean.setRegisteredPrice(registered.getRegisteredPrice());

            // 累计金额
            money = Double.parseDouble(registered.getRegisteredPrice())+money;
            registeredBeans.add(registeredBean);
        }
        countBean.setRegisteredBeans(registeredBeans);
        countBean.setTotalPeople(sicmedRegistereds.size()+"");
        countBean.setTotalMoney(money+"");
        return querySuccessResponse(countBean);
    }
}
