package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.IpssBean;
import com.prostate.stata.entity.Ipss;

import java.util.List;

public interface IpssReadMapper extends BaseReadMapper<Ipss> {

    List<IpssBean> selectByParamss(Ipss ipss);
}