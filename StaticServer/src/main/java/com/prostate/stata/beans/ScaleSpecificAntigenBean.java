package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleSpecificAntigen;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleSpecificAntigenBean extends ScaleSpecificAntigen {

    private List<ScaleSpecificAntigen> childList;

    public List<ScaleSpecificAntigen> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleSpecificAntigen> childList) {
        this.childList = childList;
    }
}
