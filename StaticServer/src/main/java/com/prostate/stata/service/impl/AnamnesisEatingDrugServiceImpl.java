package com.prostate.stata.service.impl;

import com.prostate.stata.entity.AnamnesisEatingDrug;
import com.prostate.stata.mapper.master.AnamnesisEatingDrugWriteMapper;
import com.prostate.stata.mapper.slaver.AnamnesisEatingDrugReadMapper;
import com.prostate.stata.service.AnamnesisEatingDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisEatingDrugServiceImpl implements AnamnesisEatingDrugService {

    @Autowired
    private AnamnesisEatingDrugWriteMapper anamnesisEatingDrugWriteMapper;

    @Autowired
    private AnamnesisEatingDrugReadMapper anamnesisEatingDrugReadMapper;

    @Override
    public int insertSelective(AnamnesisEatingDrug anamnesisEatingDrug) {
        return anamnesisEatingDrugWriteMapper.insertSelective(anamnesisEatingDrug);
    }

    @Override
    public int deleteById(String id) {
        return anamnesisEatingDrugWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(AnamnesisEatingDrug anamnesisEatingDrug) {
        return anamnesisEatingDrugWriteMapper.updateSelective(anamnesisEatingDrug);
    }

    @Override
    public AnamnesisEatingDrug selectById(String id) {
        return anamnesisEatingDrugReadMapper.selectById(id);
    }

    @Override
    public List<AnamnesisEatingDrug> selectByParams(AnamnesisEatingDrug anamnesisEatingDrug) {
        return anamnesisEatingDrugReadMapper.selectByParams(anamnesisEatingDrug);
    }
}
