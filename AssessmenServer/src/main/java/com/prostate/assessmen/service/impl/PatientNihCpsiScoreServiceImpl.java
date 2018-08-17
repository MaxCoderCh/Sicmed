package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.beans.NihCpsiScoreBean;
import com.prostate.assessmen.entity.PatientNihCpsiScore;
import com.prostate.assessmen.mapper.pra.write.PatientNihCpsiScoreWriteMapper;
import com.prostate.assessmen.mapper.pra.read.PatientNihCpsiScoreReadMapper;
import com.prostate.assessmen.service.PatientNihCpsiScoreService;
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
