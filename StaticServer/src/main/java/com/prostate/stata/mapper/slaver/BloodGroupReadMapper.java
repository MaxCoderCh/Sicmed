package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.BloodGroup;

public interface BloodGroupReadMapper extends BaseReadMapper<BloodGroup> {

    ChNameBean selectChNameById(String id);

}