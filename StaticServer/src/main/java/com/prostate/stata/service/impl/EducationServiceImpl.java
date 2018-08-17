package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Education;
import com.prostate.stata.mapper.master.EducationWriteMapper;
import com.prostate.stata.mapper.slaver.EducationReadMapper;
import com.prostate.stata.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationWriteMapper educationWriteMapper;

    @Autowired
    private EducationReadMapper educationReadMapper;
    @Override
    public int insertSelective(Education education) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(Education education) {
        return 0;
    }

    @Override
    public Education selectById(String id) {
        return educationReadMapper.selectById(id);
    }

    @Override
    public List<Education> selectByParams(Education education) {
        return educationReadMapper.selectByParams(education);
    }
}
