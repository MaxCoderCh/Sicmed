package com.sicmed.register.controller;

import com.sicmed.register.base.BaseController;
import com.sicmed.register.base.Constant;
import com.sicmed.register.base.util.AppParameterSimulation;
import com.sicmed.register.bean.CountBean;
import com.sicmed.register.bean.RegisteredBean;
import com.sicmed.register.cache.redis.RedisSerive;
import com.sicmed.register.entity.*;
import com.sicmed.register.service.SicmedNumService;
import com.sicmed.register.service.SicmedPatientService;
import com.sicmed.register.service.SicmedRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.sicmed.register.base.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/9/17 18:57
 * @Todo:   患者挂号控制
 */
@Controller
@RequestMapping("/ehis/sicmedRegistered")
public class SicmedRegisteredController  extends BaseController {


    @Autowired
    private SicmedRegisteredService sicmedRegisteredService;

    @Autowired
    private SicmedPatientService  sicmedPatientService;

    @Autowired
    private SicmedNumService sicmedNumService;

    @Autowired
    private RedisSerive redisSerive;

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/17 18:59
     *@Description: 挂号
     *@param:
    */
    @ResponseBody
    @PostMapping("insert")
    public Map insert(@Validated(GroupWithoutId.class) RegisteredBean registeredBean){
        //号源不足的话直接返回
        //redis中的key是科室id+医生信息id
        String key = registeredBean.getBranchId()+registeredBean.getDoctorId();
        String valueStr = redisSerive.get(key);
        // 不存在号源信息
        if (valueStr == null){
            logger.info("号源信息不存在");
            return insertFailedResponse();
        }
        // 号源信息存在，但是号源不足
        int value = Integer.parseInt(valueStr);
        if ( value< 1){
            logger.info("挂号，但是号源不足");
            return insertFailedResponse();
        }
        //根据身份证号码查询患者信息，没有的话创建患者
        /**
         *   此处有bug  关于身份证号的
         */
        SicmedPatient sicmedPatient = sicmedPatientService.selectByCard(registeredBean.getPatientCard());
        String patientId ;
        if (sicmedPatient != null){
            patientId = sicmedPatient.getId();
        }else {
            SicmedPatient patient = new SicmedPatient();
            patient.setPatientTel(registeredBean.getPatientTel());
            patient.setPatientCard(registeredBean.getPatientCard());
//            ===========================患者编号生成开始================
//            当前系统时间+每日的挂号顺序
//            患者编号 getDateStr
            StringBuffer patientNumber = new StringBuffer() ;
            String str = DateTimeUtil.getDateStr();
            patientNumber.append(str);
            SicmedNum sno = sicmedNumService.selectById(Constant.PATIENT_NUM_ID);

            Date date = sno.getUpdateDate();
            //如果是当日第一个患者，则编号为0001，否则直接加一
            if(DateTimeUtil.getDayBeginDate(date)){
                sno.setNumber("1");
            }
            String s = sno.getNumber();
            // 不够四位数的话前面加"0"
            int i = 4 - s.length();
            for (int j = 0; j < i; j++) {
                patientNumber.append(0);
            }
            patientNumber.append(s);
            Integer integer = Integer.parseInt(s);
            integer = integer +1;
            sno.setNumber(integer.toString());
            sno.setUpdateDate(new Date());
            sicmedNumService.updateSelective(sno);
//            ===========================患者编号生成结束================

            patient.setPatientNumber(patientNumber.toString());
            patient.setPatientName(registeredBean.getPatientName());
            patient.setPatientAddress(registeredBean.getPatientAddress());
            patient.setPatientSex(registeredBean.getPatientSex());
            patient.setPatientAge(registeredBean.getPatientAge());
            patient.setMedicareNo(registeredBean.getMedicareNo());
            patient.setMedicareType(registeredBean.getMedicareType());
            patient.setDelFlag(Constant.DEL_FLAG_USABLE);
            patient.setCreateDate(new Date());
            patient.setCreateUser(getToken());
            if (sicmedPatientService.insertSelective(patient) < 1){
                return insertFailedResponse();
            }
            SicmedPatient sp = sicmedPatientService.selectByCard(registeredBean.getPatientCard());
            patientId = sp.getId();
        }
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        // 患者信息
        sicmedRegistered.setPatientId(patientId);
        //科室信息
        sicmedRegistered.setBranchId(registeredBean.getBranchId());
        // 挂号类型
        sicmedRegistered.setRegistrationType(registeredBean.getRegisteredTypeId());
        // 医生信息
        sicmedRegistered.setDoctorId(registeredBean.getDoctorId());
        // 挂号费
        sicmedRegistered.setRegisteredPrice(registeredBean.getRegisteredPrice());
        //缴费类型
        sicmedRegistered.setPayType(registeredBean.getPayType());
        //删除状态
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        // 获取创建者信息
        sicmedRegistered.setCreateDate(new Date());
        sicmedRegistered.setCreateUser(getToken());
        //未缴费
        sicmedRegistered.setPriceStatus(Constant.PRICE_NOT_PAY);
        //正常挂号
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        //就诊时间-----当前时间到今日24时

        sicmedRegistered.setRegisteredBeginDate(new Date());
        sicmedRegistered.setRegisteredEndDate(DateTimeUtil.getCureEndDate());
        if (sicmedRegisteredService.insertSelective(sicmedRegistered) > 0){
            // 号源信息减一
            redisSerive.set(key,(value-1)+"");
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:30
     *@Description: 患者改号
     *@param:
    */
    @ResponseBody
    @PostMapping("registeredChange")
    public Map registeredChange(@Validated({GroupWithoutId.class, GroupID.class}) RegisteredBean registeredBean){
        /**
         *  从缓存中查询剩余号源数量 ，如果号源数量不足，则改号失败
         */
        String key = registeredBean.getBranchId()+registeredBean.getDoctorId();
        String valueStr = redisSerive.get(key);
        // 不存在号源信息
        if (valueStr == null){
            logger.info("号源信息不存在");
            return updateFailedResponse();
        }
        // 号源信息存在，但是号源不足
        int value = Integer.parseInt(valueStr);
        if ( value< 1){
            logger.info("改号，号源不足！");
            return updateFailedResponse();
        }
//         将信息的挂号信息赋值给原来的挂号数据
//        这里只能是从数据库中根据原来的id查询信息，直接new对象的话会丢失信息
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredBean.getId());
        //号源信息数据key
        String key1 = sicmedRegistered.getBranchId()+sicmedRegistered.getDoctorId();
        sicmedRegistered.setBranchId(registeredBean.getBranchId());
        sicmedRegistered.setDoctorId(registeredBean.getDoctorId());
        sicmedRegistered.setRegisteredPrice(registeredBean.getRegisteredPrice());
        sicmedRegistered.setRegistrationType(registeredBean.getRegisteredTypeId());
        sicmedRegistered.setPayType(registeredBean.getPayType());
        sicmedRegistered.setUpdateDate(new Date());
        sicmedRegistered.setUpdateUser(getToken());
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_EXCHANGE);
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
           //根据id找到之前的科室信息和医生信息
            String valueStr1 = redisSerive.get(key1);
            // 不存在号源信息
            if (valueStr1 == null){
                logger.info("号源信息不存在");
                return insertFailedResponse();
            }
            // 号源信息存在，但是号源不足
            int value1 = Integer.parseInt(valueStr1);
            /**
             *  新的科室号源减一  之前的科室号源加一
             */
            redisSerive.set(key,(value-1)+"");
            redisSerive.set(key1,(value1+1)+"");
            logger.info("改号成功");
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 9:35
     *@Description:  患者退号
     *@param:
    */
    @ResponseBody
    @PostMapping("registeredRefund")
    public Map registeredRefund(@Validated(GroupID.class) RegisteredBean registeredBean){

        // 改变挂号信息中的挂号状态，
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredBean.getId());
        //找不到这个挂号信息的话直接返回
        if (sicmedRegistered == null){
           return deleteFailedResponse();
        }
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_DISUSABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_RETURN);
        sicmedRegistered.setDeleteUser(getToken());
        sicmedRegistered.setDeleteDate(new Date());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0){
            /**
             *   退出的号源信息加一
             */
            String key = sicmedRegistered.getBranchId()+sicmedRegistered.getDoctorId();
            // 不存在号源信息
            String valueStr = redisSerive.get(key);
            if (valueStr == null){
                logger.info("号源信息不存在");
                return insertFailedResponse();
            }
            // 号源信息存在，但是号源不足
            int value = Integer.parseInt(valueStr);
            redisSerive.set(key,(value+1)+"");
            logger.info("患者退号");
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }



    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 10:28
     *@Description: 可以改号的患者列表
     *@param:
    */
    @ResponseBody
    @GetMapping("allChangePatientes")
    public Map allChangePatientes(){
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建查询条件
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        List<SicmedRegistered> registereds = sicmedRegisteredService.selectByParams2(sicmedRegistered);
        if (registereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:registereds) {
            RegisteredBean bean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            bean.setId(registered.getId());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setPayType(registered.getPayType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 10:31
     *@Description: 可以退号的患者列表
     *@param:
    */
    @ResponseBody
    @GetMapping("allRefundPatientes")
    public Map allRefundPatientes(){
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        //创建查询条件
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
        List<SicmedRegistered> registereds = sicmedRegisteredService.selectByParams2(sicmedRegistered);
        if (registereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:registereds) {
            RegisteredBean bean = new RegisteredBean();
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            // 以下依次为科室，医生，挂号状态（正常，改号等），id,挂号类型，缴费类型,挂号费
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setId(registered.getId());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setPayType(registered.getPayType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 13:11
     *@Description: 按照患者编号查询患者的挂号信息
     *@param:
    */
    @ResponseBody
    @GetMapping("selectByPatientNO")
    public Map selectByPatientNO(String patientNumber){
        //创建返回对象
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        // 根据患者编号查询患者信息
        SicmedPatient sicmedPatient = sicmedPatientService.selectByNumber(patientNumber);
        //患者信息为空
        if (sicmedPatient == null){
            return queryEmptyResponse();
        }
        // 根据患者id查询挂号信息
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setPatientId(sicmedPatient.getId());
        sicmedRegistered.setDelFlag(Constant.DEL_FLAG_USABLE);
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.selectByParams(sicmedRegistered);
        // 患者的挂号信息为空
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            //挂号部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setDoctorId(registered.getDoctorId());
            bean.setBranchId(registered.getBranchId());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setPayType(registered.getPayType());
            bean.setId(registered.getId());
            // 患者信息部分
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            registeredBeans.add(bean);
        }
        return querySuccessResponse(registeredBeans);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/20 11:23
     *@Description: 根据id查询挂号信息
     *@param:
     */
    @ResponseBody
    @GetMapping("selectById")
    public Map selectById(String registeredId){
        RegisteredBean registeredBean = new RegisteredBean();
        SicmedRegistered sicmedRegistered = sicmedRegisteredService.selectById(registeredId);
        if (sicmedRegistered == null){
            return queryEmptyResponse();
        }
        SicmedPatient sicmedPatient = sicmedPatientService.selectById(sicmedRegistered.getPatientId());
        // 挂号信息
        registeredBean.setRegisteredPrice(sicmedRegistered.getRegisteredPrice());
        registeredBean.setRegisteredTypeId(sicmedRegistered.getRegistrationType());
        registeredBean.setRegisteredStatus(sicmedRegistered.getRegisteredStatus());
        registeredBean.setId(sicmedRegistered.getId());
        registeredBean.setDoctorId(sicmedRegistered.getDoctorId());
        registeredBean.setBranchId(sicmedRegistered.getBranchId());
        registeredBean.setPayType(sicmedRegistered.getPayType());
        //患者个人信息部分
        registeredBean.setPatientAddress(sicmedPatient.getPatientAddress());
        registeredBean.setPatientCard(sicmedPatient.getPatientCard());
        registeredBean.setPatientSex(sicmedPatient.getPatientSex());
        registeredBean.setPatientAge(sicmedPatient.getPatientAge());
        registeredBean.setPatientTel(sicmedPatient.getPatientTel());
        registeredBean.setPatientName(sicmedPatient.getPatientName());
        registeredBean.setPatientNumber(sicmedPatient.getPatientNumber());
        return querySuccessResponse(registeredBean);
    }




    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 13:43
     *@Description: 查询挂号记录(0 正常 1改号   2退号   3正常结束)
     *@param:
     */
    @ResponseBody
    @GetMapping("registeredRecord")
    public Map registeredRecord(String status,String beginDate,String endDate){
        //创建返回对象
        CountBean countBean = new CountBean();
        double totalMoney = 0.00;
        List<RegisteredBean> registeredBeans  = new ArrayList<>();
        // 默认查询的时间范围是当天0时到现在
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getTime();
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        if(beginDate != "" && beginDate !=null){
            startDate = DateTimeUtil.getDate(beginDate);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endDate)){
            endDate1 = DateTimeUtil.getDate(endDate);
        }
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        // 给查询条件赋值
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        List<SicmedRegistered> sicmedRegistereds = null;
        switch (Integer.parseInt(status)){
            // 挂号
            case 0:
                sicmedRegistered.setCreateUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_CURING);
                sicmedRegistereds = sicmedRegisteredService.registerRecord(sicmedRegistered);
                break;
            // 改号
            case 1:
                sicmedRegistered.setUpdateUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_EXCHANGE);
                sicmedRegistereds = sicmedRegisteredService.exchangeRecord(sicmedRegistered);
                break;
            // 退号
            case 2:
                sicmedRegistered.setDeleteUser(getToken());
                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_RETURN);
                sicmedRegistereds = sicmedRegisteredService.returnRecord(sicmedRegistered);
                break;
            // 正常结束====
//            case 3:
//                sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_OVER);
            default:
                break;
        }
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        //遍历挂号信息
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            // 根据挂号信息查询出患者信息
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            //患者信息部分
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            //挂号信息部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setPayType(registered.getPayType());
            bean.setId(registered.getId());
            registeredBeans.add(bean);

            // 统计金额信息
            totalMoney = totalMoney+Double.parseDouble(registered.getRegisteredPrice());
        }
        // 三种情况分别统计，因此有多少条挂号记录，就有多少人数
        countBean.setTotalPeople(sicmedRegistereds.size()+"");
        countBean.setTotalMoney(totalMoney+"");
        countBean.setRegisteredBeans(registeredBeans);
        return querySuccessResponse(countBean);
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:49
     *@Description:  医生查询今日安排的患者列表
     *@param:
    */
    @ResponseBody
    @GetMapping("myPatients")
    public Map myPatients(String branch,String registeredType,String doctor,String beginDate,String endDate){
        CountBean countBean = new CountBean();
       //查询条件   已经缴费  科室和医生信息相符
        List<RegisteredBean> registeredBeans = new ArrayList<>();
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        sicmedRegistered.setPriceStatus(Constant.PRICE_EXCHARGE);
        Date startDate = DateTimeUtil.getDayBeginDate();
        Date endDate1 = DateTimeUtil.getDayEndDate();
        // 如果前端的起始时间不为空，则按照前端的起始时间查询
        if(beginDate != "" && beginDate !=null){
            startDate = DateTimeUtil.getDate(beginDate);
        }
        // 截止时间不为空的话  按照前端的时间进行查询
        if(!AppParameterSimulation.isEmpty(endDate)){
            endDate1 = DateTimeUtil.getDate(endDate);
        }
        /**
         *   这里的科室和医生信息应该根据当前登录信息进行获取
         */
        sicmedRegistered.setDoctorId(doctor);
        sicmedRegistered.setBranchId(branch);
        sicmedRegistered.setRegistrationType(registeredType);
        sicmedRegistered.setRegisteredBeginDate(startDate);
        sicmedRegistered.setRegisteredEndDate(endDate1);
        // 这里查询到的是今日本科室相关权限下的所有患者信息，不分是否已经就诊
        List<SicmedRegistered> sicmedRegistereds = sicmedRegisteredService.selectByParams(sicmedRegistered);
        // 只查询已经就诊结束的挂号信息
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_OVER);
        List<SicmedRegistered> registeredsOver = sicmedRegisteredService.selectByParams(sicmedRegistered);
        if (sicmedRegistereds.isEmpty()){
            return queryEmptyResponse();
        }
        //遍历挂号信息
        for (SicmedRegistered registered:sicmedRegistereds) {
            RegisteredBean bean = new RegisteredBean();
            // 根据挂号信息查询出患者信息
            SicmedPatient sicmedPatient = sicmedPatientService.selectById(registered.getPatientId());
            //患者信息部分
            bean.setPatientNumber(sicmedPatient.getPatientNumber());
            bean.setPatientName(sicmedPatient.getPatientName());
            bean.setPatientTel(sicmedPatient.getPatientTel());
            bean.setPatientAddress(sicmedPatient.getPatientAddress());
            bean.setPatientAge(sicmedPatient.getPatientAge());
            bean.setPatientSex(sicmedPatient.getPatientSex());
            bean.setPatientCard(sicmedPatient.getPatientCard());
            //挂号信息部分
            bean.setRegisteredStatus(registered.getRegisteredStatus());
            bean.setRegisteredTypeId(registered.getRegistrationType());
            bean.setRegisteredPrice(registered.getRegisteredPrice());
            bean.setBranchId(registered.getBranchId());
            bean.setDoctorId(registered.getDoctorId());
            bean.setId(registered.getId());
            bean.setPayType(registered.getPayType());
            registeredBeans.add(bean);
        }
        countBean.setRegisteredBeans(registeredBeans);
        countBean.setIsCrue(registeredsOver.size()+"");
        countBean.setPatientes(sicmedRegistereds.size()+"");
        return querySuccessResponse(countBean);
    }




    /**
     *@Author:      ykbian
     *@date_time:   2018/9/19 15:51
     *@Description: 医生看完病以后结束挂号流程
     *@param:
    */
    @ResponseBody
    @PostMapping("finishCure")
    public Map finishCure(@Validated(GroupID.class) RegisteredBean registeredBean){

        //获取医生信息
        SicmedRegistered sicmedRegistered = new SicmedRegistered();
        //只有派给本科室的订单，医生才有权限结束治疗
        /**
         *  此处有问题， setDoctorId  应该是经过医生的id获取其挂号等级
         */
        sicmedRegistered.setDoctorId(getToken());
        sicmedRegistered.setRegisteredStatus(Constant.PATIENT_REGISTERED_OVER);
        sicmedRegistered.setId(registeredBean.getId());
        if (sicmedRegisteredService.updateSelective(sicmedRegistered) > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }
}
