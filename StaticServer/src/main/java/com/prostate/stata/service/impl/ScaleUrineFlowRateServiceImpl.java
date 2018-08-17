package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ScaleUrineFlowRateBean;
import com.prostate.stata.entity.ScaleUrineFlowRate;
import com.prostate.stata.mapper.slaver.ScaleUrineFlowRateReadMapper;
import com.prostate.stata.service.ScaleUrineFlowRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleUrineFlowRateServiceImpl implements ScaleUrineFlowRateService {

    @Autowired
    private ScaleUrineFlowRateReadMapper scaleUrineFlowRateReadMapper;

    @Override
    public int insertSelective(ScaleUrineFlowRate scaleUrineFlowRate) {
        return 0;
    }

    @Override
    public int updateSelective(ScaleUrineFlowRate scaleUrineFlowRate) {
        return 0;
    }

    @Override
    public ScaleUrineFlowRate selectById(String id) {
        return null;
    }

    @Override
    public List<ScaleUrineFlowRate> selectByParams(ScaleUrineFlowRate scaleUrineFlowRate) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'urineFlowRate'")
    @Override
    public List<ScaleUrineFlowRateBean> selectByParamss(ScaleUrineFlowRate scaleUrineFlowRate) {
        return scaleUrineFlowRateReadMapper.selectByParamss(scaleUrineFlowRate);
    }
}
