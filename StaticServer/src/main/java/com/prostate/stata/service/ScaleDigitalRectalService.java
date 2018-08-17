package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleDigitalRectalBean;
import com.prostate.stata.entity.ScaleDigitalRectal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleDigitalRectalService extends BaseService<ScaleDigitalRectal> {

    List<ScaleDigitalRectalBean> selectByParamss(ScaleDigitalRectal scaleDigitalRectal);
}
