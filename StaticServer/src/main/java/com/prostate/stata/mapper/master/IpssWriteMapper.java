package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.IpssBean;
import com.prostate.stata.entity.Ipss;

import java.util.List;

public interface IpssWriteMapper extends BaseWriteMapper<Ipss> {

    List<IpssBean> selectByParamss(Ipss ipss);
}