package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.ScaleExpressedProstaticSecretion;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScaleExpressedProstaticSecretionBean extends ScaleExpressedProstaticSecretion {

    private List<ScaleExpressedProstaticSecretion> childList;

    public List<ScaleExpressedProstaticSecretion> getChildList() {
        return childList;
    }

    public void setChildList(List<ScaleExpressedProstaticSecretion> childList) {
        this.childList = childList;
    }
}
