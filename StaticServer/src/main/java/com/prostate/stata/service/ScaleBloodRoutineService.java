package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleBloodRoutineBean;
import com.prostate.stata.entity.ScaleBloodRoutine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleBloodRoutineService extends BaseService<ScaleBloodRoutine> {

    List<ScaleBloodRoutineBean> selectByParamss();

}
