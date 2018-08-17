package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleBloodRoutine;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleBloodRoutineBean extends ScaleBloodRoutine {

    private List<ScaleBloodRoutine> childList;

    public List<ScaleBloodRoutine> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleBloodRoutine> childList) {
        this.childList = childList;
    }
}
