package com.prostate.record.service.impl;

import com.prostate.record.entity.PatientSticker;
import com.prostate.record.mapper.master.PatientStickerWriteMapper;
import com.prostate.record.mapper.slaver.PatientStickerReadMapper;
import com.prostate.record.service.PatientStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientStickerServiceImpl implements PatientStickerService {

    @Autowired
    private PatientStickerWriteMapper patientStickerWriteMapper;
    @Autowired
    private PatientStickerReadMapper patientStickerReadMapper;

    @Override
    public int insertSelective(PatientSticker patientSticker) {
        return patientStickerWriteMapper.insertSelective(patientSticker);
    }

    @Override
    public int updateSelective(PatientSticker patientSticker) {
        return patientStickerWriteMapper.updateSelective(patientSticker);
    }

    @Override
    public PatientSticker selectById(String id) {
        return patientStickerReadMapper.selectById(id);
    }

    @Override
    public List<PatientSticker> selectByParams(PatientSticker patientSticker) {
        return patientStickerReadMapper.selectByParams(patientSticker);
    }

    @Override
    public int deleteById(String id) {
        return patientStickerWriteMapper.deleteById(id);
    }
}
