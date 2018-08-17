package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ScaleUrineRoutineBean;
import com.prostate.stata.entity.ScaleUrineRoutine;
import com.prostate.stata.mapper.slaver.ScaleUrineRoutineReadMapper;
import com.prostate.stata.service.ScaleUrineRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleUrineRoutineServiceImpl implements ScaleUrineRoutineService {

    @Autowired
    private ScaleUrineRoutineReadMapper scaleUrineRoutineReadMapper;

    @Override
    public int insertSelective(ScaleUrineRoutine scaleUrineRoutine) {
        return 0;
    }

    @Override
    public int updateSelective(ScaleUrineRoutine scaleUrineRoutine) {
        return 0;
    }

    @Override
    public ScaleUrineRoutine selectById(String id) {
        return null;
    }

    @Override
    public List<ScaleUrineRoutine> selectByParams(ScaleUrineRoutine scaleUrineRoutine) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'urineRoutine'")
    @Override
    public List<ScaleUrineRoutineBean> selectByParamss(ScaleUrineRoutine scaleUrineRoutine) {
        return scaleUrineRoutineReadMapper.selectByParamss(scaleUrineRoutine);
    }
}
