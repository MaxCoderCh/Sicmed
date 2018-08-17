package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.Profession;

public interface ProfessionReadMapper extends BaseReadMapper<Profession> {
    ChNameBean selectChNameById(String id);

}