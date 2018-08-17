package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ScaleExpressedProstaticSecretionBean;
import com.prostate.stata.entity.ScaleExpressedProstaticSecretion;
import com.prostate.stata.mapper.slaver.ScaleExpressedProstaticSecretionReadMapper;
import com.prostate.stata.service.ScaleExpressedProstaticSecretionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleExpressedProstaticSecretionServiceImpl implements ScaleExpressedProstaticSecretionService {

    @Autowired
    private ScaleExpressedProstaticSecretionReadMapper scaleExpressedProstaticSecretionReadMapper;

    @Override
    public int insertSelective(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion) {
        return 0;
    }

    @Override
    public int updateSelective(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion) {
        return 0;
    }

    @Override
    public ScaleExpressedProstaticSecretion selectById(String id) {
        return null;
    }

    @Override
    public List<ScaleExpressedProstaticSecretion> selectByParams(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'expressedProstaticSecretion'")
    @Override
    public List<ScaleExpressedProstaticSecretionBean> selectByParamss(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion) {
        return scaleExpressedProstaticSecretionReadMapper.selectByParamss(scaleExpressedProstaticSecretion);
    }
}
