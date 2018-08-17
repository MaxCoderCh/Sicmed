package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.Education;

public interface EducationReadMapper extends BaseReadMapper<Education> {
    ChNameBean selectChNameById(String id);

}