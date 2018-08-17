package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleUltrasonographyB;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleUltrasonographyBBean extends ScaleUltrasonographyB {

    private List<ScaleUltrasonographyB> childList;

    public List<ScaleUltrasonographyB> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleUltrasonographyB> childList) {
        this.childList = childList;
    }
}
