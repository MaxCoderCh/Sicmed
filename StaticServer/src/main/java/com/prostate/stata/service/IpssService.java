package com.prostate.stata.service;

import com.prostate.stata.beans.IpssBean;
import com.prostate.stata.entity.Ipss;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpssService extends BaseService<Ipss>{

    List<IpssBean> selectByParamss(Ipss ipss);
}
