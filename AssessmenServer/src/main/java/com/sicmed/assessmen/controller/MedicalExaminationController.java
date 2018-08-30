package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.beans.MedicalExaminationBean;
import com.sicmed.assessmen.cache.redis.RedisSerive;
import com.sicmed.assessmen.entity.ProstaticMedicalExamination;
import com.sicmed.assessmen.service.ProstaticMedicalExaminationService;
import com.sicmed.assessmen.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * 医学 检查 检验 接口
 */
@Slf4j
@RestController
@RequestMapping(value = "medicalExamination")
public class MedicalExaminationController extends BaseController {

    @Autowired
    private ProstaticMedicalExaminationService prostaticMedicalExaminationService;

    /**
     * WeChat 添加化验单解读结果
     *
     * @return
     */
    @RequestMapping(value = "add")
    public Map add(@Valid ProstaticMedicalExamination prostaticMedicalExamination, String patientAge, String prostaticMedicalExaminationId) {


        prostaticMedicalExamination.setPatientId(getToken());

        //记录ID校验
        boolean t = StringUtils.isNotBlank(prostaticMedicalExaminationId);

        Date cds = DateUtils.stringToDate(DateUtils.getCurrentDate());

        prostaticMedicalExamination.setCreateTime(cds);

        ProstaticMedicalExamination checkProstaticMedicalExamination = prostaticMedicalExaminationService.selectByCreateTimeAndPatientId(prostaticMedicalExamination);

        if (checkProstaticMedicalExamination != null && !t) {
            return insertRepeatResponse("今日已添加过化验单解读");
        }

        int i = 0;
        List<Integer> bloodRoutineList = new LinkedList<>();
        List<Integer> digitalRectalList = new LinkedList<>();
        List<Integer> expressedProstaticSecretionList = new LinkedList<>();
        List<Integer> specificAntigenList = new LinkedList<>();
        List<Integer> ultrasonographyBList = new LinkedList<>();
        List<Integer> urineFlowRateList = new LinkedList<>();
        List<Integer> urineRoutineList = new LinkedList<>();

        //客户端答案解读
        if (prostaticMedicalExamination.getBloodRoutineAnswer() != null && !"".equals(prostaticMedicalExamination.getBloodRoutineAnswer())) {
            bloodRoutineList = BloodRoutineUtils.getBloodRoutineScores(prostaticMedicalExamination.getBloodRoutineAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getDigitalRectalAnswer() != null && !"".equals(prostaticMedicalExamination.getDigitalRectalAnswer())) {
            digitalRectalList = DigitalRectalUtils.getDigitalRectalList(prostaticMedicalExamination.getDigitalRectalAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getExpressedProstaticSecretionAnswer() != null && !"".equals(prostaticMedicalExamination.getExpressedProstaticSecretionAnswer())) {
            expressedProstaticSecretionList = ExpressedProstaticSecretionUtils.getExpressedProstaticSecretionList(prostaticMedicalExamination.getExpressedProstaticSecretionAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getSpecificAntigenAnswer() != null && !"".equals(prostaticMedicalExamination.getSpecificAntigenAnswer())) {
            specificAntigenList = SpecificAntigenUtils.getSpecificAntigenList(prostaticMedicalExamination.getSpecificAntigenAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUltrasonographyBAnswer() != null && !"".equals(prostaticMedicalExamination.getUltrasonographyBAnswer())) {
            ultrasonographyBList = UltrasonographyBUtils.getUltrasonographyBList(prostaticMedicalExamination.getUltrasonographyBAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUrineFlowRateAnswer() != null && !"".equals(prostaticMedicalExamination.getUrineFlowRateAnswer())) {
            urineFlowRateList = UrineFlowRateUtils.getUrineFlowRateList(prostaticMedicalExamination.getUrineFlowRateAnswer());
            i++;
        }
        if (prostaticMedicalExamination.getUrineRoutineAnswer() != null && !"".equals(prostaticMedicalExamination.getUrineRoutineAnswer())) {
            urineRoutineList = UrineRoutineUtils.getUrineRoutineList(prostaticMedicalExamination.getUrineRoutineAnswer());
            i++;
        }
        //
        if (i == 0) {
            return emptyParamResponse();
        }


        //获取患者年龄
        int age = Integer.parseInt(patientAge);

        //添加异常检查项提示
        ProstateMedicalExaminationUtils.setAnomalyItem(bloodRoutineList, digitalRectalList, expressedProstaticSecretionList, specificAntigenList, ultrasonographyBList, urineFlowRateList, urineRoutineList, prostaticMedicalExamination);

        //根据年龄计算化验单解读提示
        if (age < 50) {
            //计算前列腺炎症风险
            ProstateMedicalExaminationUtils.setProstatitis(bloodRoutineList, expressedProstaticSecretionList, ultrasonographyBList, urineRoutineList, prostaticMedicalExamination);
        } else if (age >= 50) {
            //计算前列腺增生风险
            ProstateMedicalExaminationUtils.setBPHRemark(bloodRoutineList, digitalRectalList, expressedProstaticSecretionList, specificAntigenList, ultrasonographyBList, urineFlowRateList, urineRoutineList, prostaticMedicalExamination);
        }
        if (t) {
            prostaticMedicalExamination.setId(prostaticMedicalExaminationId);
            prostaticMedicalExaminationService.updateSelective(prostaticMedicalExamination);
            return updateSuccseeResponse(prostaticMedicalExamination);
        } else {
            prostaticMedicalExaminationService.insertSelective(prostaticMedicalExamination);
            return insertSuccseeResponse(prostaticMedicalExamination);
        }
    }

    /**
     * WeChat 根据患者 token 查询 化验单解读记录
     */
    @GetMapping(value = "getByPatientId")
    public LinkedHashMap getByPatientId() {

        ProstaticMedicalExamination prostaticMedicalExamination = new ProstaticMedicalExamination();

        prostaticMedicalExamination.setPatientId(getToken());


        List<ProstaticMedicalExamination> prostaticMedicalExaminationList = prostaticMedicalExaminationService.selectByParams(prostaticMedicalExamination);
        if (prostaticMedicalExaminationList == null || prostaticMedicalExaminationList.size() == 0) {
            return queryEmptyResponse();
        }

        LinkedHashMap<String, LinkedHashMap<String, List<ProstaticMedicalExamination>>> mp = prostaticMedicalExaminationOrder(prostaticMedicalExaminationList);

        return querySuccessResponse(mp);
    }


    /**
     * WeChat 根据患者 和日期 查询 化验单解读结果
     *
     * @return
     */
    @PostMapping(value = "getByPatientAndDate")
    public LinkedHashMap getByPatientAndData(String patientId, String createDate) {
        ProstaticMedicalExamination prostaticMedicalExamination = new ProstaticMedicalExamination();

        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }
        if (StringUtils.isBlank(createDate)) {
            createDate = DateUtils.getCurrentDate();
        }
        prostaticMedicalExamination.setPatientId(patientId);
        prostaticMedicalExamination.setCreateTime(DateUtils.stringToDate(createDate));

        prostaticMedicalExamination = prostaticMedicalExaminationService.selectByPatientAndData(prostaticMedicalExamination);

        if (prostaticMedicalExamination == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(prostaticMedicalExamination);
    }


    /**
     *  @version：指定版本信息。
     *  @since: 指定最早出现在哪个版本
     *  @author：MaxCoder
     *  @see：用于指定参考的内容。
     *  @docroot：表示产生文档的根路径。
     *  @deprecated：不推荐使用的方法。
     *  @param：方法的参数类型。
     *  @return：方法的返回类型。
     *  @exception：抛出的异常。
     *  @throws：抛出的异常，和exception同义
     */


    /**
     * 化验单解读结果集 按日期排序
     *
     * @param prostaticMedicalExaminationList
     * @return
     */
    private static LinkedHashMap prostaticMedicalExaminationOrder(List<ProstaticMedicalExamination> prostaticMedicalExaminationList) {
        LinkedHashMap<String, LinkedHashMap<String, List<ProstaticMedicalExamination>>> mp = new LinkedHashMap<>();

        for (ProstaticMedicalExamination medicalExamination : prostaticMedicalExaminationList) {
            Date date = medicalExamination.getCreateTime();
            String year = String.valueOf(date.getYear() + 1900);
            String month = String.valueOf(date.getMonth() + 1);
            LinkedHashMap<String, List<ProstaticMedicalExamination>> y = mp.get(year);
            if (y != null) {
                List<ProstaticMedicalExamination> m = y.get(month);
                if (m != null) {
                    m.add(medicalExamination);
                } else {
                    List<ProstaticMedicalExamination> l = new LinkedList<>();
                    l.add(medicalExamination);
                    y.put(month, l);
                }
            } else {
                LinkedHashMap<String, List<ProstaticMedicalExamination>> s = new LinkedHashMap<>();
                List<ProstaticMedicalExamination> l = new LinkedList<>();
                l.add(medicalExamination);
                s.put(month, l);
                mp.put(year, s);
            }

        }
        return mp;
    }


    /**
     * APP 根据患者Id 查询 化验单解读记录
     */
    @GetMapping(value = "getExaminationListByPatient")
    public LinkedHashMap getExaminationListByPatient(String patientId) {
        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }
        ProstaticMedicalExamination prostaticMedicalExamination = new ProstaticMedicalExamination();

        prostaticMedicalExamination.setPatientId(patientId);

        List<MedicalExaminationBean> medicalExaminationBeanList = prostaticMedicalExaminationService.queryPageByParams(prostaticMedicalExamination);
        if (medicalExaminationBeanList == null || medicalExaminationBeanList.size() == 0) {
            return queryEmptyResponse();
        }

        return querySuccessResponse(medicalExaminationBeanList);
    }


    /**
     * APP 根据 ID 查询 化验单解读结果
     *
     * @return
     */
    @GetMapping(value = "getById")
    public Map getById(String id) {

        if (StringUtils.isBlank(id)) {
            return emptyParamResponse();
        }
        ProstaticMedicalExamination prostaticMedicalExamination = prostaticMedicalExaminationService.selectById(id);

        if (prostaticMedicalExamination == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(prostaticMedicalExamination);
    }
}
