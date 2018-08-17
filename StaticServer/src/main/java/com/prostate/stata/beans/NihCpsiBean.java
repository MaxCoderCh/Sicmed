package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.NihCpsi;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NihCpsiBean extends NihCpsi {

    public List<NihCpsi> childList;

    public List<NihCpsi> getChildList() {
        return childList;
    }

    public void setChildList(List<NihCpsi> childList) {
        this.childList = childList;
    }
}
