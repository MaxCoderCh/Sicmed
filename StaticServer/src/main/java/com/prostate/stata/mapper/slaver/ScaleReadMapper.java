package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleBean;
import com.prostate.stata.entity.Scale;

import java.util.List;

public interface ScaleReadMapper extends BaseReadMapper<Scale> {

    List<ScaleBean> selectByParamss(Scale scale);
}