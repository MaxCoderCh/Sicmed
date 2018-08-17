package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleExpressedProstaticSecretionBean;
import com.prostate.stata.entity.ScaleExpressedProstaticSecretion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleExpressedProstaticSecretionService extends BaseService<ScaleExpressedProstaticSecretion> {
    List<ScaleExpressedProstaticSecretionBean> selectByParamss(ScaleExpressedProstaticSecretion scaleExpressedProstaticSecretion);
}
