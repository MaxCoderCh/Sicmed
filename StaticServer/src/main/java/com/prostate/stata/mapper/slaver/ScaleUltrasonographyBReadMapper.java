package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleUltrasonographyBBean;
import com.prostate.stata.entity.ScaleUltrasonographyB;

import java.util.List;

public interface ScaleUltrasonographyBReadMapper extends BaseReadMapper<ScaleUltrasonographyB> {

    List<ScaleUltrasonographyBBean> selectByParamss(ScaleUltrasonographyB scaleUltrasonographyB);
}