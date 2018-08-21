package com.sicmed.archive.service.impl;

import com.sicmed.archive.entity.MedicalReport;
import com.sicmed.archive.mapper.master.MedicalReportWriteMapper;
import com.sicmed.archive.mapper.slaver.MedicalReportReadMapper;
import com.sicmed.archive.service.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    @Autowired
    private MedicalReportWriteMapper medicalReportWriteMapper;

    @Autowired
    private MedicalReportReadMapper medicalReportReadMapper;

    @Override
    public int insertSelective(MedicalReport medicalReport) {
        return medicalReportWriteMapper.insertSelective(medicalReport);
    }

    @Override
    public int updateSelective(MedicalReport medicalReport) {
        return medicalReportWriteMapper.updateSelective(medicalReport);
    }

    @Override
    public MedicalReport selectById(String id) {
        return medicalReportReadMapper.selectById(id);
    }

    @Override
    public List<MedicalReport> selectByParams(MedicalReport medicalReport) {
        return medicalReportReadMapper.selectByParams(medicalReport);
    }

    @Override
    public int deleteById(String id) {
        return medicalReportWriteMapper.deleteById(id);
    }

    @Override
    public List<String> selectUrlByParams(MedicalReport medicalReport) {
        return medicalReportReadMapper.selectUrlByParams(medicalReport);
    }
}
