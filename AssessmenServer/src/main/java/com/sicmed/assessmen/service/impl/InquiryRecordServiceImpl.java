package com.sicmed.assessmen.service.impl;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.mapper.pra.read.InquiryRecordReadMapper;
import com.sicmed.assessmen.mapper.pra.write.InquiryRecordWriteMapper;
import com.sicmed.assessmen.service.InquiryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryRecordServiceImpl implements InquiryRecordService {

    @Autowired
    private InquiryRecordWriteMapper inquiryRecordWriteMapper;

    @Autowired
    private InquiryRecordReadMapper inquiryRecordReadMapper;

    @Override
    public int insertSelective(InquiryRecord inquiryRecord) {
        return inquiryRecordWriteMapper.insertSelective((inquiryRecord));
    }

    @Override
    public int updateSelective(InquiryRecord inquiryRecord) {
        return inquiryRecordWriteMapper.updateSelective(inquiryRecord);
    }

    @Override
    public InquiryRecord selectById(String id) {
        return inquiryRecordReadMapper.selectById(id);
    }

    @Override
    public List<InquiryRecord> selectByParams(InquiryRecord inquiryRecord) {
        return inquiryRecordReadMapper.selectByParams(inquiryRecord);
    }

    @Override
    public int deleteById(String id) {
        return inquiryRecordWriteMapper.deleteById(id);
    }

    @Override
    public InquiryRecord selectByArchive(String archive) {
        return inquiryRecordReadMapper.selectByArchive(archive);
    }
}
