package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.NihCpsiBean;
import com.prostate.stata.entity.NihCpsi;

import java.util.List;

public interface NihCpsiReadMapper extends BaseReadMapper<NihCpsi> {

    List<NihCpsiBean> selectByParamss(NihCpsi nihCpsi);
}