package com.prostate.stata.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.stata.entity.Ipss;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpssBean extends Ipss {

    private List<Ipss> childList;

    public List<Ipss> getChildList() {
        return childList;
    }

    public void setChildList(List<Ipss> childList) {
        this.childList = childList;
    }
}
