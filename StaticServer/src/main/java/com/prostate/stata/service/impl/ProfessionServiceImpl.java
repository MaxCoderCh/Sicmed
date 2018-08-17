package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Profession;
import com.prostate.stata.mapper.slaver.ProfessionReadMapper;
import com.prostate.stata.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionReadMapper professionReadMapper;
    @Override
    public int insertSelective(Profession profession) {
        return 0;
    }

    @Override
    public int updateSelective(Profession profession) {
        return 0;
    }

    @Override
    public Profession selectById(String id) {
        return professionReadMapper.selectById(id);
    }

    @Override
    public List<Profession> selectByParams(Profession profession) {
        return professionReadMapper.selectByParams(profession);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
