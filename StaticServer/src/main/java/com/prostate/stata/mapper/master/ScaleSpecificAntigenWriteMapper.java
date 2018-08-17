package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleSpecificAntigenBean;
import com.prostate.stata.entity.ScaleSpecificAntigen;

import java.util.List;

public interface ScaleSpecificAntigenWriteMapper extends BaseWriteMapper<ScaleSpecificAntigen> {

    List<ScaleSpecificAntigenBean> selectByParamss(ScaleSpecificAntigen scaleSpecificAntigen);
}