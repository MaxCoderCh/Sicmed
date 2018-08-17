package com.prostate.stata.service.impl;

import com.prostate.stata.entity.AnamnesisSurgicalHistory;
import com.prostate.stata.mapper.master.AnamnesisSurgicalHistoryWriteMapper;
import com.prostate.stata.mapper.slaver.AnamnesisSurgicalHistoryReadMapper;
import com.prostate.stata.service.AnamnesisSurgicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisSurgicalHistoryServiceImpl implements AnamnesisSurgicalHistoryService {
    @Autowired
    private AnamnesisSurgicalHistoryWriteMapper anamnesisSurgicalHistoryWriteMapper;

    @Autowired
    private AnamnesisSurgicalHistoryReadMapper anamnesisSurgicalHistoryReadMapper;

    @Override
    public int insertSelective(AnamnesisSurgicalHistory anamnesisSurgicalHistory) {
        return anamnesisSurgicalHistoryWriteMapper.insertSelective(anamnesisSurgicalHistory);
    }

    @Override
    public int deleteById(String id) {
        return anamnesisSurgicalHistoryWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(AnamnesisSurgicalHistory anamnesisSurgicalHistory) {
        return anamnesisSurgicalHistoryWriteMapper.updateSelective(anamnesisSurgicalHistory);
    }

    @Override
    public AnamnesisSurgicalHistory selectById(String id) {
        return anamnesisSurgicalHistoryReadMapper.selectById(id);
    }

    @Override
    public List<AnamnesisSurgicalHistory> selectByParams(AnamnesisSurgicalHistory anamnesisSurgicalHistory) {
        return anamnesisSurgicalHistoryReadMapper.selectByParams(anamnesisSurgicalHistory);
    }
}
