package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleUrineFlowRateBean;
import com.prostate.stata.entity.ScaleUrineFlowRate;

import java.util.List;

public interface ScaleUrineFlowRateWriteMapper extends BaseWriteMapper<ScaleUrineFlowRate> {

    List<ScaleUrineFlowRateBean> selectByParamss(ScaleUrineFlowRate scaleUrineFlowRate);
}