package com.prostate.assessmen.service.impl;

import com.prostate.assessmen.entity.PatientScaleScore;
import com.prostate.assessmen.mapper.pra.write.ScaleScoreWriteMapper;
import com.prostate.assessmen.mapper.pra.read.ScaleScoreReadMapper;
import com.prostate.assessmen.service.ScaleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleScoreServiceImpl implements ScaleScoreService {

    @Autowired
    private ScaleScoreWriteMapper scaleScoreWriteMapper;

    @Autowired
    private ScaleScoreReadMapper scaleScoreReadMapper;

    @Override
    public int insertSelectiveById(PatientScaleScore scaleScore) {
        return scaleScoreWriteMapper.insertSelectiveById(scaleScore);
    }

    @Override
    public int insertSelective(PatientScaleScore scaleScore) {
        return scaleScoreWriteMapper.insertSelective(scaleScore);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(PatientScaleScore scaleScore) {
        return scaleScoreWriteMapper.updateSelective(scaleScore);
    }

    @Override
    public PatientScaleScore selectById(String id) {
        return scaleScoreReadMapper.selectById(id);
    }

    @Override
    public List<PatientScaleScore> selectByParams(PatientScaleScore scaleScore) {
        return scaleScoreReadMapper.selectByParams(scaleScore);
    }
}
