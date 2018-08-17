package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleUrineRoutineBean;
import com.prostate.stata.entity.ScaleUrineRoutine;

import java.util.List;

public interface ScaleUrineRoutineWriteMapper extends BaseWriteMapper<ScaleUrineRoutine> {

    List<ScaleUrineRoutineBean> selectByParamss(ScaleUrineRoutine scaleUrineRoutine);
}