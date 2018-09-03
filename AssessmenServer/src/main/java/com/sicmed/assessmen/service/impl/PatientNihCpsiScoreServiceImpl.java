package com.sicmed.assessmen.service.impl;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;
import com.sicmed.assessmen.mapper.master.PatientNihCpsiScoreWriteMapper;
import com.sicmed.assessmen.mapper.slaver.PatientNihCpsiScoreReadMapper;
import com.sicmed.assessmen.service.PatientNihCpsiScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientNihCpsiScoreServiceImpl implements PatientNihCpsiScoreService {

    @Autowired
    private PatientNihCpsiScoreWriteMapper patientNihCpsiScoreWriteMapper;

    @Autowired
    private PatientNihCpsiScoreReadMapper patientNihCpsiScoreReadMapper;

    @Override
    public int insertSelectiveById(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihCpsiScoreWriteMapper.insertSelectiveById(patientNihNpsiScore);
    }

    @Override
    public int insertSelective(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihCpsiScoreWriteMapper.insertSelective(patientNihNpsiScore);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihCpsiScoreWriteMapper.updateSelective(patientNihNpsiScore);
    }

    @Override
    public PatientNihCpsiScore selectById(String id) {
        return patientNihCpsiScoreReadMapper.selectById(id);
    }

    @Override
    public List<PatientNihCpsiScore> selectByParams(PatientNihCpsiScore patientNihNpsiScore) {
        return null;
    }

    @Override
    public PatientNihCpsiScore selectByCreateTimeAndPatientId(PatientNihCpsiScore patientNihNpsiScore) {
        return patientNihCpsiScoreReadMapper.selectByCreateTimeAndPatientId(patientNihNpsiScore);
    }

    @Override
    public NihCpsiScoreBean getById(String nihCpsiScoreId) {
        return patientNihCpsiScoreReadMapper.getById(nihCpsiScoreId);
    }
}
