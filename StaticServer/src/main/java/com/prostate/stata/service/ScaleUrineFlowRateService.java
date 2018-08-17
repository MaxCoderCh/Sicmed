package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleUrineFlowRateBean;
import com.prostate.stata.entity.ScaleUrineFlowRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleUrineFlowRateService extends BaseService<ScaleUrineFlowRate> {
    List<ScaleUrineFlowRateBean> selectByParamss(ScaleUrineFlowRate scaleUrineFlowRate);
}
