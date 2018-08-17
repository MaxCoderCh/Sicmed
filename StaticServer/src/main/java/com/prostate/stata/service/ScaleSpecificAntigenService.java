package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleSpecificAntigenBean;
import com.prostate.stata.entity.ScaleSpecificAntigen;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleSpecificAntigenService extends BaseService<ScaleSpecificAntigen> {
    List<ScaleSpecificAntigenBean> selectByParamss(ScaleSpecificAntigen scaleSpecificAntigen);
}
