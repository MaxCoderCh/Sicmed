package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.ScaleExpressedProstaticSecretionBean;
import com.prostate.stata.entity.ScaleExpressedProstaticSecretion;

import java.util.List;

public interface ScaleExpressedProstaticSecretionWriteMapper extends BaseWriteMapper<ScaleExpressedProstaticSecretion> {

    List<ScaleExpressedProstaticSecretionBean> selectByParamss(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion);
}