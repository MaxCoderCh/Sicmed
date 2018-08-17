package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisAllergyDrug;

public interface AnamnesisAllergyDrugReadMapper extends BaseReadMapper<AnamnesisAllergyDrug> {

    ChNameBean selectChNameById(String id);
}