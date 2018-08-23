package com.prostate.record.controller;

import com.prostate.record.entity.PatientSticker;
import com.prostate.record.service.PatientStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "patient/sticker")
public class PatientStickerController extends BaseController {

    @Autowired
    private PatientStickerService patientStickerService;

    /**
     * APP 患者 标签 添加
     * @param patientId
     * @param stickers
     * @return
     */
    @PostMapping(value = "add")
    public Map add(String patientId, String[] stickers) {

        PatientSticker patientSticker;

        for (String sticker : stickers) {
            patientSticker = new PatientSticker();
            patientSticker.setPatientId(patientId);
            patientSticker.setStickerId(sticker);
            patientSticker.setCreateUser(getToken());

            patientStickerService.insertSelective(patientSticker);
        }

        return insertSuccseeResponse();

    }

    /**
     * APP 患者标签 查询
     * @param patientId
     * @return
     */
    @GetMapping(value = "get")
    public Map get(String patientId) {

        PatientSticker patientSticker = new PatientSticker();

        patientSticker.setPatientId(patientId);
        patientSticker.setCreateUser(getToken());

        List<PatientSticker> patientStickerList = patientStickerService.selectByParams(patientSticker);

        if (patientStickerList == null || patientStickerList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(patientStickerList);

    }

    /**
     * APP 患者 标签 删除
     * @param id
     * @return
     */
    @PostMapping(value = "remove")
    public Map remove(String id) {

        int i = patientStickerService.deleteById(id);
        if (i > 0) {

            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }
}
