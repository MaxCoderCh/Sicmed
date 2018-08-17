package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleUrineRoutineBean;
import com.prostate.stata.entity.ScaleUrineRoutine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleUrineRoutineService extends BaseService<ScaleUrineRoutine> {
    List<ScaleUrineRoutineBean> selectByParamss(ScaleUrineRoutine scaleUrineRoutine);
}
