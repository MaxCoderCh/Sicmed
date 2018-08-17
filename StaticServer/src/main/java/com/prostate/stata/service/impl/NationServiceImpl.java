package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Nation;
import com.prostate.stata.mapper.slaver.NationReadMapper;
import com.prostate.stata.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServiceImpl implements NationService {

    @Autowired
    private NationReadMapper nationReadMapper;

    @Override
    public int insertSelective(Nation nation) {
        return 0;
    }

    @Override
    public int updateSelective(Nation nation) {
        return 0;
    }

    @Override
    public Nation selectById(String id) {
        return nationReadMapper.selectById(id);
    }

    @Override
    public List<Nation> selectByParams(Nation nation) {
        return nationReadMapper.selectByParams(nation);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

}
