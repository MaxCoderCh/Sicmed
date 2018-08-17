package com.prostate.stata.service.impl;

import com.prostate.stata.entity.BloodGroup;
import com.prostate.stata.mapper.master.BloodGroupWriteMapper;
import com.prostate.stata.mapper.slaver.BloodGroupReadMapper;
import com.prostate.stata.service.BloodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodGroupServiceImpl implements BloodGroupService {
    @Autowired
    private BloodGroupWriteMapper bloodGroupWriteMapper;

    @Autowired
    private BloodGroupReadMapper bloodGroupReadMapper;

    @Override
    public int insertSelective(BloodGroup bloodGroup) {
        return bloodGroupWriteMapper.insertSelective(bloodGroup);
    }

    @Override
    public int deleteById(String id) {
        return bloodGroupWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(BloodGroup bloodGroup) {
        return bloodGroupWriteMapper.updateSelective(bloodGroup);
    }

    @Override
    public BloodGroup selectById(String id) {
        return bloodGroupReadMapper.selectById(id);
    }

    @Override
    public List<BloodGroup> selectByParams(BloodGroup bloodGroup) {
        return bloodGroupReadMapper.selectByParams(bloodGroup);
    }
}
