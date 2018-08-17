package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleDigitalRectal;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleDigitalRectalBean extends ScaleDigitalRectal{

    private List<ScaleDigitalRectal> childList;

    public List<ScaleDigitalRectal> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleDigitalRectal> childList) {
        this.childList = childList;
    }
}
