package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.NihCpsiBean;
import com.prostate.stata.entity.NihCpsi;

import java.util.List;

public interface NihCpsiWriteMapper extends BaseWriteMapper<NihCpsi> {

    List<NihCpsiBean> selectByParamss(NihCpsi nihCpsi);
}