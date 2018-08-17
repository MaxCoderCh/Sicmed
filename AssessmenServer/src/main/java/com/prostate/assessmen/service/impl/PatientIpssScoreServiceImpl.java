package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.beans.IpssScoreBean;
import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.mapper.pra.write.PatientIpssScoreWriteMapper;
import com.prostate.assessmen.mapper.pra.read.PatientIpssScoreReadMapper;
import com.prostate.assessmen.service.PatientIpssScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientIpssScoreServiceImpl implements PatientIpssScoreService {
    @Autowired
    private PatientIpssScoreWriteMapper patientIpssScoreWriteMapper;

    @Autowired
    private PatientIpssScoreReadMapper patientIpssScoreReadMapper;

    @Override
    public int insertSelectiveById(PatientIpssScore patientIpssScore) {
        return patientIpssScoreWriteMapper.insertSelectiveById(patientIpssScore);
    }

    @Override
    public int insertSelective(PatientIpssScore patientIpssScore) {
        return patientIpssScoreWriteMapper.insertSelective(patientIpssScore);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(PatientIpssScore patientIpssScore) {
        return patientIpssScoreWriteMapper.updateSelective(patientIpssScore);
    }

    @Override
    public PatientIpssScore selectById(String id) {
        return patientIpssScoreReadMapper.selectById(id);
    }

    @Override
    public List<PatientIpssScore> selectByParams(PatientIpssScore patientIpssScore) {
        return null;
    }

    @Override
    public PatientIpssScore selectByCreateTimeAndPatientId(PatientIpssScore patientIpssScore) {
        return patientIpssScoreReadMapper.selectByCreateTimeAndPatientId(patientIpssScore);
    }

    @Override
    public IpssScoreBean getById(String ipssScoreId) {
        return patientIpssScoreReadMapper.getById(ipssScoreId);
    }
}
