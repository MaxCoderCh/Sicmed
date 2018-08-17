package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.Scale;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleBean extends Scale {

    private List<Scale> childList;

    public List<Scale> getChildList() {
        return childList;
    }

    public void setChildList(List<Scale> childList) {
        this.childList = childList;
    }
}
