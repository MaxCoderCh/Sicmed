package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleBean;
import com.prostate.stata.entity.Scale;

import java.util.List;

public interface ScaleWriteMapper extends BaseWriteMapper<Scale> {

    List<ScaleBean> selectByParamss(Scale scale);
}