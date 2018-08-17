package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.HospitalRecord;
import com.prostate.assessmen.mapper.pomr.write.HospitalRecordWriteMapper;
import com.prostate.assessmen.mapper.pomr.read.HospitalRecordReadMapper;
import com.prostate.assessmen.service.HospitalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalRecordServiceImpl implements HospitalRecordService {
    @Autowired
    private HospitalRecordWriteMapper hospitalRecordWriteMapper;

    @Autowired
    private HospitalRecordReadMapper hospitalRecordReadMapper;
    @Override
    public int insertSelective(HospitalRecord hospitalRecord) {
        return hospitalRecordWriteMapper.insertSelective(hospitalRecord);
    }

    @Override
    public int deleteById(String id) {
        return hospitalRecordWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(HospitalRecord hospitalRecord) {
        return 0;
    }

    @Override
    public HospitalRecord selectById(String id) {
        return null;
    }

    @Override
    public List<HospitalRecord> selectByParams(HospitalRecord hospitalRecord) {
        return hospitalRecordReadMapper.selectByParams(hospitalRecord);
    }

    @Override
    public int deleteByImgPath(String imgPath) {
        return hospitalRecordWriteMapper.deleteByImgPath(imgPath);
    }
}
