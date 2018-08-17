package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisEatingDrug;

public interface AnamnesisEatingDrugReadMapper extends BaseReadMapper<AnamnesisEatingDrug> {
    ChNameBean selectChNameById(String id);

}