package com.prostate.stata.service.impl;

import com.prostate.stata.beans.ScaleBloodRoutineBean;
import com.prostate.stata.entity.ScaleBloodRoutine;
import com.prostate.stata.mapper.slaver.ScaleBloodRoutineReadMapper;
import com.prostate.stata.service.ScaleBloodRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleBloodRoutineServiceImpl implements ScaleBloodRoutineService{

    @Autowired
    private ScaleBloodRoutineReadMapper scaleBloodRoutineReadMapper;

    @Override
    public int insertSelective(ScaleBloodRoutine scaleBloodRoutine) {
        return 0;
    }

    @Override
    public int updateSelective(ScaleBloodRoutine scaleBloodRoutine) {
        return 0;
    }

    @Override
    public ScaleBloodRoutine selectById(String id) {
        return scaleBloodRoutineReadMapper.selectById(id);
    }

    @Override
    public List<ScaleBloodRoutine> selectByParams(ScaleBloodRoutine scaleBloodRoutine) {
        return scaleBloodRoutineReadMapper.selectByParams(scaleBloodRoutine);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'bloodRoutine'")
    @Override
    public List<ScaleBloodRoutineBean> selectByParamss() {
        return scaleBloodRoutineReadMapper.selectByParamss();
    }
}
