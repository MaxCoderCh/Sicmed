package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.InspectionRecord;
import com.prostate.assessmen.mapper.pomr.write.InspectionRecordWriteMapper;
import com.prostate.assessmen.mapper.pomr.read.InspectionRecordReadMapper;
import com.prostate.assessmen.service.InspectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionRecordServiceImpl implements InspectionRecordService {

    @Autowired
    private InspectionRecordWriteMapper inspectionRecordWriteMapper;

    @Autowired
    private InspectionRecordReadMapper inspectionRecordReadMapper;

    @Override
    public int insertSelective(InspectionRecord inspectionRecord) {
        return inspectionRecordWriteMapper.insertSelective(inspectionRecord);
    }

    @Override
    public int deleteById(String id) {
        return inspectionRecordWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(InspectionRecord inspectionRecord) {
        return 0;
    }

    @Override
    public InspectionRecord selectById(String id) {
        return null;
    }

    @Override
    public List<InspectionRecord> selectByParams(InspectionRecord inspectionRecord) {
        return inspectionRecordReadMapper.selectByParams(inspectionRecord);
    }

    @Override
    public int deleteByImgPath(String imgPath) {
        return inspectionRecordWriteMapper.deleteByImgPath(imgPath);
    }
}
