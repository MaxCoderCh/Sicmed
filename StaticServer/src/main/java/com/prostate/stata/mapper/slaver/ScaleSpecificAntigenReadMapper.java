package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleSpecificAntigenBean;
import com.prostate.stata.entity.ScaleSpecificAntigen;

import java.util.List;

public interface ScaleSpecificAntigenReadMapper extends BaseReadMapper<ScaleSpecificAntigen> {

    List<ScaleSpecificAntigenBean> selectByParamss(ScaleSpecificAntigen scaleSpecificAntigen);
}