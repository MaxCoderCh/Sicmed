package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleUrineRoutineBean;
import com.prostate.stata.entity.ScaleUrineRoutine;

import java.util.List;

public interface ScaleUrineRoutineReadMapper extends BaseReadMapper<ScaleUrineRoutine> {

    List<ScaleUrineRoutineBean> selectByParamss(ScaleUrineRoutine scaleUrineRoutine);
}