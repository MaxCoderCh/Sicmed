package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleUrineFlowRateBean;
import com.prostate.stata.entity.ScaleUrineFlowRate;

import java.util.List;

public interface ScaleUrineFlowRateReadMapper extends BaseReadMapper<ScaleUrineFlowRate> {

    List<ScaleUrineFlowRateBean> selectByParamss(ScaleUrineFlowRate scaleUrineFlowRate);
}