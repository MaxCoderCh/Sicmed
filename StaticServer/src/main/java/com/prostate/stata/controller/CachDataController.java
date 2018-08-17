package com.prostate.stata.controller;


import com.prostate.stata.entity.LablePatient;
import com.prostate.stata.entity.LablePrice;
import com.prostate.stata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "cache/static")
public class CachDataController extends BaseController {

    @Autowired
    private LableInquiryService lableInquiryService;     //问诊类型标签缓查询能

    @Autowired
    private HospitalService hospitalService; //医院列表查询功能

    @Autowired
    private BranchService branchService; //科室列表查询功能

    @Autowired
    private DoctorTitleService doctorTitleService; //职称列表查询功能

    @Autowired
    private LablePatientService lablePatientService; //患者标签查询功能

    @Autowired
    private LablePriceService lablePriceService; //问诊价格标签查询功能

    @Autowired
    private CityService cityService; //查询城市



    //IPSS量表查询功能

    //NIHCPSI 量表查询功能

    //化验单解读量表查询功能


    /**
     * 查询问诊类型标签
     * @return
     */
    @GetMapping(value = "getlableInquiryJson")
    public Map<String,Object> lableInquiryJson(){

        Map<String,String> map = lableInquiryService.getLableInquiryJson();
        if (map == null || map.size() == 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/6 10:27
     *@Description:  查询医院信息
     *@param:
    */
    @GetMapping(value = "getHospitalJson")
    public Map<String,Object> hospitalJson(){

        Map<String,String> map = hospitalService.getHospitalJson();
        if (map==null || map.size()== 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/6 10:27
     *@Description:  查询职称信息
     *@param:
     */
    @GetMapping(value = "getDoctorTitleJson")
    public Map<String,Object> doctorTitleJson(){

        Map<String,String> map = doctorTitleService.getDoctorTitleJson();
        if (map==null || map.size()== 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/6 13:00
     *@Description:  患者标签查询
     *@param:
    */
    @GetMapping(value = "getLablePatientJson")
    public Map<String,Object> lablePatientJson(String token){
        LablePatient lablePatient = new LablePatient();
        lablePatient.setCreateUser(token);
        Map<String,String> map = lablePatientService.getDoctorTitleJson(lablePatient);
        //map.isEmpty    判断的话会报空指针异常
        if (map==null || map.size()== 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/6 15:31
     *@Description:  价格标签查询
     *@param:
    */
    @GetMapping(value = "getLablePriceJson")
    public Map<String,Object> lablePriceJson(String token){
        LablePrice lablePrice = new LablePrice();
        lablePrice.setCreateUser(token);
        Map<String,String> map = lablePriceService.getLablePriceJson(lablePrice);
        if (map==null || map.size()== 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }


    @GetMapping(value = "getBranchServiceJson")
    public Map<String,Object> branchServiceJson(){
        Map<String,String> map = branchService.getBranchJson();
        if (map==null || map.size()== 0){
            return queryEmptyResponse();
        }
        return querySuccessResponse(map);
    }
}
