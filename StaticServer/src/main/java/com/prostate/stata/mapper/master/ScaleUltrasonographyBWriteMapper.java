package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleUltrasonographyBBean;
import com.prostate.stata.entity.ScaleUltrasonographyB;

import java.util.List;

public interface ScaleUltrasonographyBWriteMapper extends BaseWriteMapper<ScaleUltrasonographyB> {

    List<ScaleUltrasonographyBBean> selectByParamss(ScaleUltrasonographyB scaleUltrasonographyB);
}