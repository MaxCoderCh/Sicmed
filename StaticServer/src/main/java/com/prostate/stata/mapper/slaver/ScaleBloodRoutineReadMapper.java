package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleBloodRoutineBean;
import com.prostate.stata.entity.ScaleBloodRoutine;

import java.util.List;

public interface ScaleBloodRoutineReadMapper extends BaseReadMapper<ScaleBloodRoutine> {

    List<ScaleBloodRoutineBean> selectByParamss();
}