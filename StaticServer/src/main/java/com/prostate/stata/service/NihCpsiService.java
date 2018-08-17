package com.prostate.stata.service;

import com.prostate.stata.beans.NihCpsiBean;
import com.prostate.stata.entity.NihCpsi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NihCpsiService extends BaseService<NihCpsi> {
    List<NihCpsiBean> selectByParamss(NihCpsi nihCpsi);
}
