package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleDigitalRectalBean;
import com.prostate.stata.entity.ScaleDigitalRectal;

import java.util.List;

public interface ScaleDigitalRectalReadMapper extends BaseReadMapper<ScaleDigitalRectal> {

    List<ScaleDigitalRectalBean> selectByParamss(ScaleDigitalRectal scaleDigitalRectal);
}