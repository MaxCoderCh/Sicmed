package com.prostate.stata.service.impl;

import com.prostate.stata.beans.NihCpsiBean;
import com.prostate.stata.entity.NihCpsi;
import com.prostate.stata.mapper.slaver.NihCpsiReadMapper;
import com.prostate.stata.service.NihCpsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NihCpsiServiceImpl implements NihCpsiService {

    @Autowired
    private NihCpsiReadMapper nihCpsiReadMapper;

    @Override
    public int insertSelective(NihCpsi nihCpsi) {
        return 0;
    }

    @Override
    public int updateSelective(NihCpsi nihCpsi) {
        return 0;
    }

    @Override
    public NihCpsi selectById(String id) {
        return null;
    }

    @Override
    public List<NihCpsi> selectByParams(NihCpsi nihCpsi) {
        return nihCpsiReadMapper.selectByParams(nihCpsi);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'nihCpsi'")
    @Override
    public List<NihCpsiBean> selectByParamss(NihCpsi nihCpsi) {
        return nihCpsiReadMapper.selectByParamss(nihCpsi);
    }
}
