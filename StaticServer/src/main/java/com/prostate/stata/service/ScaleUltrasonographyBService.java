package com.prostate.stata.service;

import com.prostate.stata.beans.ScaleUltrasonographyBBean;
import com.prostate.stata.entity.ScaleUltrasonographyB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScaleUltrasonographyBService extends BaseService<ScaleUltrasonographyB> {
    List<ScaleUltrasonographyBBean> selectByParamss(ScaleUltrasonographyB scaleUltrasonographyB);
}
