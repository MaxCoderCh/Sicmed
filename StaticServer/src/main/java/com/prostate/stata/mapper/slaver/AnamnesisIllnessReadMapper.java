package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisIllness;

public interface AnamnesisIllnessReadMapper extends BaseReadMapper<AnamnesisIllness> {
    ChNameBean selectChNameById(String id);

}