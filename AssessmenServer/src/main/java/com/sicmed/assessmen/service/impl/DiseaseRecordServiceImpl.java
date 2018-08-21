package com.sicmed.assessmen.service.impl;

import com.sicmed.assessmen.entity.DiseaseRecord;
import com.sicmed.assessmen.mapper.pomr.write.DiseaseRecordWriteMapper;
import com.sicmed.assessmen.mapper.pomr.read.DiseaseRecordReadMapper;
import com.sicmed.assessmen.service.DiseaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseRecordServiceImpl implements DiseaseRecordService {
    @Autowired
    private DiseaseRecordWriteMapper diseaseRecordWriteMapper;

    @Autowired
    private DiseaseRecordReadMapper diseaseRecordReadMapper;

    @Override
    public int insertSelective(DiseaseRecord diseaseRecord) {
        return diseaseRecordWriteMapper.insertSelective(diseaseRecord);
    }

    @Override
    public int deleteById(String id) {
        return diseaseRecordWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(DiseaseRecord diseaseRecord) {
        return 0;
    }

    @Override
    public DiseaseRecord selectById(String id) {
        return null;
    }

    @Override
    public List<DiseaseRecord> selectByParams(DiseaseRecord diseaseRecord) {
        return diseaseRecordReadMapper.selectByParams(diseaseRecord);
    }

    @Override
    public int deleteByImgPath(String imgPath) {
        return diseaseRecordWriteMapper.deleteByImgPath(imgPath);
    }
}
