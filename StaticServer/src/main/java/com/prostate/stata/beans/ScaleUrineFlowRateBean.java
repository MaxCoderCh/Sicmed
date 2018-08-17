package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleUrineFlowRate;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleUrineFlowRateBean extends ScaleUrineFlowRate {

    private List<ScaleUrineFlowRate> childList;

    public List<ScaleUrineFlowRate> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleUrineFlowRate> childList) {
        this.childList = childList;
    }
}
