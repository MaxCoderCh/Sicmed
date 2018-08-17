package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisAllergyDrug;
import com.prostate.stata.mapper.master.AnamnesisAllergyDrugWriteMapper;
import com.prostate.stata.mapper.slaver.AnamnesisAllergyDrugReadMapper;
import com.prostate.stata.service.AnamnesisAllergyDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisAllergyDrugServiceImpl implements AnamnesisAllergyDrugService {

    @Autowired
    private AnamnesisAllergyDrugWriteMapper anamnesisAllergyDrugWriteMapper;

    @Autowired
    private AnamnesisAllergyDrugReadMapper anamnesisAllergyDrugReadMapper;

    @Override
    public int insertSelective(AnamnesisAllergyDrug anamnesisAllergyDrug) {
        return anamnesisAllergyDrugWriteMapper.insertSelective(anamnesisAllergyDrug);
    }

    @Override
    public int deleteById(String id) {
        return anamnesisAllergyDrugWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(AnamnesisAllergyDrug anamnesisAllergyDrug) {
        return anamnesisAllergyDrugWriteMapper.updateSelective(anamnesisAllergyDrug);
    }


    @Override
    public AnamnesisAllergyDrug selectById(String id) {
        return anamnesisAllergyDrugReadMapper.selectById(id);
    }

    @Override
    public List<AnamnesisAllergyDrug> selectByParams(AnamnesisAllergyDrug anamnesisAllergyDrug) {
        return anamnesisAllergyDrugReadMapper.selectByParams(anamnesisAllergyDrug);
    }

}
