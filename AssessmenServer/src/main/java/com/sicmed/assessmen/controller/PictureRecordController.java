package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.entity.*;
import com.sicmed.assessmen.feignService.FileServer;
import com.sicmed.assessmen.service.*;
import com.sicmed.assessmen.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图片记录 接口
 */
@Slf4j
@RestController
@RequestMapping(value = "record")
public class PictureRecordController extends BaseController {


    private CheckupRecordService checkupRecordService;
    private DiseaseRecordService diseaseRecordService;
    private HospitalRecordService hospitalRecordService;
    private InspectionRecordService inspectionRecordService;

    private FileServer fileServer;

    @Autowired
    public PictureRecordController( CheckupRecordService checkupRecordService, DiseaseRecordService diseaseRecordService, HospitalRecordService hospitalRecordService, InspectionRecordService inspectionRecordService,FileServer fileServer) {
        this.checkupRecordService = checkupRecordService;
        this.diseaseRecordService = diseaseRecordService;
        this.hospitalRecordService = hospitalRecordService;
        this.inspectionRecordService = inspectionRecordService;
        this.fileServer = fileServer;
    }


    /**
     * 添加体检记录
     *
     * @return
     */
    @PostMapping(value = "addCheckupRecord")
    public Map addCheckupRecord(String patientId, String[] recordPaths) {

        if (patientId == null || "".equals(patientId) || recordPaths == null || recordPaths.length <= 0) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(DateUtils.getCurrentDate());

        for (String recordPath : recordPaths) {
            log.info("图片路径=" + recordPath);
            CheckupRecord checkupRecord = new CheckupRecord();
            checkupRecord.setRecordPath(recordPath);
            checkupRecord.setPatientId(patientId);
            checkupRecord.setCreateTime(currentDate);
            checkupRecordService.insertSelective(checkupRecord);
        }
        return insertSuccseeResponse();
    }

    /**
     * 查询体检记录
     *
     * @return
     */
    @PostMapping(value = "getCheckupRecord")
    public Map getCheckupRecord(String patientId, String createDate) {

        if (patientId == null || "".equals(patientId) || createDate == null || "".equals(createDate)) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(createDate);
        CheckupRecord checkupRecord = new CheckupRecord();
        checkupRecord.setCreateTime(currentDate);
        checkupRecord.setPatientId(patientId);

        List<CheckupRecord> checkupRecordList = checkupRecordService.selectByParams(checkupRecord);
        if (checkupRecordList.size() > 0) {
            return querySuccessResponse(checkupRecordList);
        }
        return queryEmptyResponse();
    }

    /**
     * 删除体检记录
     *
     * @return
     */
    @PostMapping(value = "deleteCheckupRecord")
    public Map deleteCheckupRecord(String imgPath) {

        if (imgPath == null || "".equals(imgPath)) {
            return emptyParamResponse();
        }

        int i = checkupRecordService.deleteByImgPath(imgPath);
        if (i >= 0) {
            fileServer.delete(imgPath);
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse("删除失败:");
    }


    /**
     * 添加病程记录
     *
     * @return
     */
    @PostMapping(value = "addDiseaseRecord")
    public Map addDiseaseRecord(String patientId, String[] recordPaths) {
        if (patientId == null || "".equals(patientId) || recordPaths == null || recordPaths.length <= 0) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(DateUtils.getCurrentDate());
        for (String recordPath : recordPaths) {
            DiseaseRecord diseaseRecord = new DiseaseRecord();
            diseaseRecord.setPatientId(patientId);
            diseaseRecord.setRecordPath(recordPath);
            diseaseRecord.setCreateTime(currentDate);
            diseaseRecordService.insertSelective(diseaseRecord);
        }
        return insertSuccseeResponse();
    }

    /**
     * 查询 病程记录
     *
     * @param patientId
     * @param createDate
     * @return
     */
    @PostMapping(value = "getDiseaseRecord")
    public Map getDiseaseRecord(String patientId, String createDate) {

        if (patientId == null || "".equals(patientId) || createDate == null || "".equals(createDate)) {
            return emptyParamResponse();
        }

        Date currentDate = DateUtils.stringToDate(createDate);
        DiseaseRecord diseaseRecord = new DiseaseRecord();
        diseaseRecord.setCreateTime(currentDate);
        diseaseRecord.setPatientId(patientId);

        List<DiseaseRecord> diseaseRecordList = diseaseRecordService.selectByParams(diseaseRecord);
        if (diseaseRecordList.size() > 0) {
            return querySuccessResponse(diseaseRecordList);
        }
        return queryEmptyResponse();
    }

    /**
     * 删除 病程记录
     *
     */
    @PostMapping(value = "deleteDiseaseRecord")
    public Map deleteDiseaseRecord(String imgPath) {

        if (imgPath == null || "".equals(imgPath)) {
            return emptyParamResponse();
        }
        int i = diseaseRecordService.deleteByImgPath(imgPath);
        if (i >= 0) {
            fileServer.delete(imgPath);
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse("删除失败:");
    }

    /**
     * 添加住院记录
     *
     * @return
     */
    @PostMapping(value = "addHospitalRecord")
    public Map addHospitalRecord(String patientId, String[] recordPaths) {
        if (patientId == null || "".equals(patientId) || recordPaths == null || recordPaths.length <= 0) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(DateUtils.getCurrentDate());
        for (String recordPath : recordPaths) {
            HospitalRecord hospitalRecord = new HospitalRecord();
            hospitalRecord.setPatientId(patientId);
            hospitalRecord.setRecordPath(recordPath);
            hospitalRecord.setCreateTime(currentDate);
            hospitalRecordService.insertSelective(hospitalRecord);
        }
        return insertSuccseeResponse();
    }

    /**
     * 查询 住院记录
     *
     * @param patientId
     * @param createDate
     * @return
     */
    @PostMapping(value = "getHospitalRecord")
    public Map getHospitalRecord(String patientId, String createDate) {

        if (patientId == null || "".equals(patientId) || createDate == null || "".equals(createDate)) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(createDate);
        HospitalRecord hospitalRecord = new HospitalRecord();
        hospitalRecord.setCreateTime(currentDate);
        hospitalRecord.setPatientId(patientId);

        List<HospitalRecord> hospitalRecordList = hospitalRecordService.selectByParams(hospitalRecord);
        if (hospitalRecordList.size() > 0) {
            return querySuccessResponse(hospitalRecordList);
        }
        return queryEmptyResponse();
    }

    /**
     * 删除住院记录
     *
     */
    @PostMapping(value = "deleteHospitalRecord")
    public Map deleteHospitalRecord(String imgPath) {

        if (imgPath == null || "".equals(imgPath)) {
            return emptyParamResponse();
        }
        int i = hospitalRecordService.deleteByImgPath(imgPath);
        if (i >= 0) {
            fileServer.delete(imgPath);
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse("删除失败:");
    }


    /**
     * 添加检验记录
     *
     * @param patientId
     * @param recordPaths
     * @return
     */
    @PostMapping(value = "addInspectionRecord")
    public Map addInspectionRecord(String patientId, String[]  recordPaths) {
        if (patientId == null || "".equals(patientId) || recordPaths == null || recordPaths.length <= 0) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(DateUtils.getCurrentDate());
        for (String recordPath : recordPaths) {
            InspectionRecord inspectionRecord = new InspectionRecord();
            inspectionRecord.setPatientId(patientId);
            inspectionRecord.setRecordPath(recordPath);
            inspectionRecord.setCreateTime(currentDate);
            inspectionRecordService.insertSelective(inspectionRecord);
        }
        return insertSuccseeResponse();
    }

    /**
     * 查询检验记录
     *
     * @return
     */
    @PostMapping(value = "getInspectionRecord")
    public Map getInspectionRecord(String patientId, String createDate) {

        if (patientId == null || "".equals(patientId) || createDate == null || "".equals(createDate)) {
            return emptyParamResponse();
        }
        Date currentDate = DateUtils.stringToDate(createDate);
        InspectionRecord inspectionRecord = new InspectionRecord();
        inspectionRecord.setCreateTime(currentDate);
        inspectionRecord.setPatientId(patientId);

        List<InspectionRecord> inspectionRecordList = inspectionRecordService.selectByParams(inspectionRecord);
        if (inspectionRecordList.size() > 0) {
            return querySuccessResponse(inspectionRecordList);
        }
        return queryEmptyResponse();
    }

    /**
     * 删除检验记录
     *
     */
    @PostMapping(value = "deleteInspectionRecord")
    public Map deleteInspectionRecord(String imgPath) {

        if (imgPath == null || "".equals(imgPath)) {
            return emptyParamResponse();
        }
        int i = inspectionRecordService.deleteByImgPath(imgPath);
        if (i >= 0) {
            fileServer.delete(imgPath);
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse("删除失败:");
    }
}
