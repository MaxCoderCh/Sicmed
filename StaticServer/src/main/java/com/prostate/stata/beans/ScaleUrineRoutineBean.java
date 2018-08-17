package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleUrineRoutine;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleUrineRoutineBean extends ScaleUrineRoutine {

    private List<ScaleUrineRoutine> childList;

    public List<ScaleUrineRoutine> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleUrineRoutine> childList) {
        this.childList = childList;
    }
}
