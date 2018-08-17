package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ScaleExpressedProstaticSecretionBean;
import com.prostate.stata.entity.ScaleExpressedProstaticSecretion;

import java.util.List;

public interface ScaleExpressedProstaticSecretionReadMapper extends BaseReadMapper<ScaleExpressedProstaticSecretion> {

    List<ScaleExpressedProstaticSecretionBean> selectByParamss(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion);
}