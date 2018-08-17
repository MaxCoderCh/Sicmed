package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.CheckupRecord;
import com.prostate.assessmen.mapper.pomr.write.CheckupRecordWriteMapper;
import com.prostate.assessmen.mapper.pomr.read.CheckupRecordReadMapper;
import com.prostate.assessmen.service.CheckupRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckupRecordServiceImpl implements CheckupRecordService {
    @Autowired
    private CheckupRecordWriteMapper checkupRecordWriteMapper;

    @Autowired
    private CheckupRecordReadMapper checkupRecordReadMapper;

    @Override
    public int insertSelective(CheckupRecord checkupRecord) {
        return checkupRecordWriteMapper.insertSelective(checkupRecord);
    }

    @Override
    public int deleteById(String id) {
        return checkupRecordWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(CheckupRecord checkupRecord) {
        return 0;
    }

    @Override
    public CheckupRecord selectById(String id) {
        return null;
    }

    @Override
    public List<CheckupRecord> selectByParams(CheckupRecord checkupRecord) {
        return checkupRecordReadMapper.selectByParams(checkupRecord);
    }

    @Override
    public int deleteByImgPath(String imgPath) {
        return checkupRecordWriteMapper.deleteByImgPath(imgPath);
    }
}
