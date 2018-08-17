package com.prostate.stata.controller;

import com.prostate.stata.beans.*;
import com.prostate.stata.entity.*;
import com.prostate.stata.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "scale")
public class ScaleController extends BaseController {

    private final ScaleService scaleService;

    private final ScaleBloodRoutineService scaleBloodRoutineService;

    private final ScaleDigitalRectalService scaleDigitalRectalService;

    private final ScaleExpressedProstaticSecretionService scaleExpressedProstaticSecretionService;

    private final ScaleSpecificAntigenService scaleSpecificAntigenService;

    private final ScaleUltrasonographyBService scaleUltrasonographyBService;

    private final ScaleUrineFlowRateService scaleUrineFlowRateService;

    private final ScaleUrineRoutineService scaleUrineRoutineService;

    @Autowired
    public ScaleController(ScaleService scaleService, ScaleBloodRoutineService scaleBloodRoutineService, ScaleDigitalRectalService scaleDigitalRectalService, ScaleExpressedProstaticSecretionService scaleExpressedProstaticSecretionService, ScaleSpecificAntigenService scaleSpecificAntigenService, ScaleUltrasonographyBService scaleUltrasonographyBService, ScaleUrineFlowRateService scaleUrineFlowRateService, ScaleUrineRoutineService scaleUrineRoutineService) {
        this.scaleService = scaleService;
        this.scaleBloodRoutineService = scaleBloodRoutineService;
        this.scaleDigitalRectalService = scaleDigitalRectalService;
        this.scaleExpressedProstaticSecretionService = scaleExpressedProstaticSecretionService;
        this.scaleSpecificAntigenService = scaleSpecificAntigenService;
        this.scaleUltrasonographyBService = scaleUltrasonographyBService;
        this.scaleUrineFlowRateService = scaleUrineFlowRateService;
        this.scaleUrineRoutineService = scaleUrineRoutineService;
    }

    @PostMapping(value = "getAll")
    public Map getAll() {
        log.info("##########SCALE###########");
        Scale scale = new Scale();
        List<ScaleBean> scaleBeanList = scaleService.selectByParamss(scale);

        //查询结果校验 组装 响应
        if (scaleBeanList != null && scaleBeanList.size() > 0) {
            return querySuccessResponse(scaleBeanList);
        }
        return queryEmptyResponse();
    }


    /**
     * 获取血常规量表
     * @return
     */
    @PostMapping(value = "getBloodRoutine")
    public Map getBloodRoutine(){
        log.info("##########查询血常规量表项###########");
        List<ScaleBloodRoutineBean> scaleBloodRoutineBeanList =  scaleBloodRoutineService.selectByParamss();
        //查询结果校验 组装 响应
        if (scaleBloodRoutineBeanList != null&&scaleBloodRoutineBeanList.size()>0) {
            return querySuccessResponse(scaleBloodRoutineBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取前列腺指检量表
     * @return
     */
    @PostMapping(value = "getDigitalRectal")
    public Map getDigitalRectal(){
        ScaleDigitalRectal scaleDigitalRectal = new ScaleDigitalRectal();
        List<ScaleDigitalRectalBean> scaleDigitalRectalBeanList =  scaleDigitalRectalService.selectByParamss(scaleDigitalRectal);
        //查询结果校验 组装 响应
        if (scaleDigitalRectalBeanList != null&&scaleDigitalRectalBeanList.size()>0) {
            return querySuccessResponse(scaleDigitalRectalBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取前列腺按摩液量表
     * @return
     */
    @PostMapping(value = "getExpressedProstaticSecretion")
    public Map getExpressedProstaticSecretion(){
        ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion = new ScaleExpressedProstaticSecretion();
        List<ScaleExpressedProstaticSecretionBean> scaleExpressedProstaticSecretionBeanList =  scaleExpressedProstaticSecretionService.selectByParamss(scaleExpressedProstaticSecretion);
        //查询结果校验 组装 响应
        if (scaleExpressedProstaticSecretionBeanList != null&&scaleExpressedProstaticSecretionBeanList.size()>0) {
            return querySuccessResponse(scaleExpressedProstaticSecretionBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取特异性抗原量表
     * @return
     */
    @PostMapping(value = "getSpecificAntigen")
    public Map getSpecificAntigen(){
        ScaleSpecificAntigen scaleSpecificAntigen = new ScaleSpecificAntigen();
        List<ScaleSpecificAntigenBean> scaleSpecificAntigenBeanList =  scaleSpecificAntigenService.selectByParamss(scaleSpecificAntigen);
        //查询结果校验 组装 响应
        if (scaleSpecificAntigenBeanList != null&&scaleSpecificAntigenBeanList.size()>0) {
            return querySuccessResponse(scaleSpecificAntigenBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取B超量表
     * @return
     */
    @PostMapping(value = "getUltrasonographyB")
    public Map getUltrasonographyB(){
        ScaleUltrasonographyB scaleUltrasonographyB = new ScaleUltrasonographyB();
        List<ScaleUltrasonographyBBean> scaleUltrasonographyBBeanList =  scaleUltrasonographyBService.selectByParamss(scaleUltrasonographyB);
        //查询结果校验 组装 响应
        if (scaleUltrasonographyBBeanList != null&&scaleUltrasonographyBBeanList.size()>0) {
            return querySuccessResponse(scaleUltrasonographyBBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取尿流率量表
     * @return
     */
    @PostMapping(value = "getUrineFlowRate")
    public Map getUrineFlowRate(){
        ScaleUrineFlowRate scaleUrineFlowRate = new ScaleUrineFlowRate();
        List<ScaleUrineFlowRateBean> scaleUrineFlowRateBeanList =  scaleUrineFlowRateService.selectByParamss(scaleUrineFlowRate);
        //查询结果校验 组装 响应
        if (scaleUrineFlowRateBeanList != null&&scaleUrineFlowRateBeanList.size()>0) {
            return querySuccessResponse(scaleUrineFlowRateBeanList);
        }
        return queryEmptyResponse();
    }

    /**
     * 获取尿常规量表
     * @return
     */
    @PostMapping(value = "getUrineRoutine")
    public Map getUrineRoutine(){
        ScaleUrineRoutine scaleUrineRoutine = new ScaleUrineRoutine();
        List<ScaleUrineRoutineBean> scaleUrineRoutineBeanList =  scaleUrineRoutineService.selectByParamss(scaleUrineRoutine);
        //查询结果校验 组装 响应
        if (scaleUrineRoutineBeanList != null&&scaleUrineRoutineBeanList.size()>0) {
            return querySuccessResponse(scaleUrineRoutineBeanList);
        }
        return queryEmptyResponse();
    }
}
