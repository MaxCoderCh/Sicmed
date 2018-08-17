package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.Nation;

public interface NationReadMapper extends BaseReadMapper<Nation> {
    ChNameBean selectChNameById(String id);

}