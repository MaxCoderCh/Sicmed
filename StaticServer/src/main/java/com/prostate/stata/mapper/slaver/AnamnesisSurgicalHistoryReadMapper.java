package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisSurgicalHistory;

public interface AnamnesisSurgicalHistoryReadMapper extends BaseReadMapper<AnamnesisSurgicalHistory> {
    ChNameBean selectChNameById(String id);

}