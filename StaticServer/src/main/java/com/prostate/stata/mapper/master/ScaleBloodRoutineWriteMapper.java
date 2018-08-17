package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleBloodRoutineBean;
import com.prostate.stata.entity.ScaleBloodRoutine;

import java.util.List;

public interface ScaleBloodRoutineWriteMapper extends BaseWriteMapper<ScaleBloodRoutine> {

    List<ScaleBloodRoutineBean> selectByParamss();
}