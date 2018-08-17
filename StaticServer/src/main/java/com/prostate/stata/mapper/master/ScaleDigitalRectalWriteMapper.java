package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleDigitalRectalBean;
import com.prostate.stata.entity.ScaleDigitalRectal;

import java.util.List;

public interface ScaleDigitalRectalWriteMapper extends BaseWriteMapper<ScaleDigitalRectal> {

    List<ScaleDigitalRectalBean> selectByParamss(ScaleDigitalRectal scaleDigitalRectal);
}