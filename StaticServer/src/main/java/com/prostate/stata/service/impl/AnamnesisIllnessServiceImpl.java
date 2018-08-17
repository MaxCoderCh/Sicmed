package com.prostate.stata.service.impl;

import com.prostate.stata.entity.AnamnesisIllness;
import com.prostate.stata.mapper.master.AnamnesisIllnessWriteMapper;
import com.prostate.stata.mapper.slaver.AnamnesisIllnessReadMapper;
import com.prostate.stata.service.AnamnesisIllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisIllnessServiceImpl implements AnamnesisIllnessService {

    @Autowired
    private AnamnesisIllnessWriteMapper anamnesisIllnessWriteMapper;

    @Autowired
    private AnamnesisIllnessReadMapper anamnesisIllnessReadMapper;

    @Override
    public int insertSelective(AnamnesisIllness anamnesisIllness) {
        return anamnesisIllnessWriteMapper.insertSelective(anamnesisIllness);
    }

    @Override
    public int deleteById(String id) {
        return anamnesisIllnessWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(AnamnesisIllness anamnesisIllness) {
        return anamnesisIllnessWriteMapper.updateSelective(anamnesisIllness);
    }

    @Override
    public AnamnesisIllness selectById(String id) {
        return anamnesisIllnessReadMapper.selectById(id);
    }

    @Override
    public List<AnamnesisIllness> selectByParams(AnamnesisIllness anamnesisIllness) {
        return anamnesisIllnessReadMapper.selectByParams(anamnesisIllness);
    }
}
