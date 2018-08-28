package com.prostate.stata.mapper.master;

import com.prostate.stata.entity.Docket;

public interface DocketWriteMapper extends BaseWriteMapper<Docket>{

    int falseDeleteById(Docket docket);
}