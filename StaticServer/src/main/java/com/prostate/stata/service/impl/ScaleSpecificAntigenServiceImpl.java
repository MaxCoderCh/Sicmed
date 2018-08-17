package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ScaleSpecificAntigenBean;
import com.prostate.stata.entity.ScaleSpecificAntigen;
import com.prostate.stata.mapper.slaver.ScaleSpecificAntigenReadMapper;
import com.prostate.stata.service.ScaleSpecificAntigenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleSpecificAntigenServiceImpl implements ScaleSpecificAntigenService {

    @Autowired
    private ScaleSpecificAntigenReadMapper scaleSpecificAntigenReadMapper;

    @Override
    public int insertSelective(ScaleSpecificAntigen scaleSpecificAntigen) {
        return 0;
    }

    @Override
    public int updateSelective(ScaleSpecificAntigen scaleSpecificAntigen) {
        return 0;
    }

    @Override
    public ScaleSpecificAntigen selectById(String id) {
        return null;
    }

    @Override
    public List<ScaleSpecificAntigen> selectByParams(ScaleSpecificAntigen scaleSpecificAntigen) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'specificAntigen'")
    @Override
    public List<ScaleSpecificAntigenBean> selectByParamss(ScaleSpecificAntigen scaleSpecificAntigen) {
        return scaleSpecificAntigenReadMapper.selectByParamss(scaleSpecificAntigen);
    }
}
