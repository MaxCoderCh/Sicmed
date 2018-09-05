package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.Docket;

public interface DocketReadMapper extends BaseReadMapper<Docket>{

    Docket getByParam(Docket docket);
}