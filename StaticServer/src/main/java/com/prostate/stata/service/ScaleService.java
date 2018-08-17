package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleBean;
import com.prostate.stata.entity.Scale;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ScaleService extends BaseService<Scale>{

    List<ScaleBean> selectByParamss(Scale scale);
}
