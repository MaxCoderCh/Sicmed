package com.prostate.stata.service;

import com.prostate.stata.entity.Docket;

public interface DocketService extends BaseService<Docket> {
    int falseDeleteById(Docket docket);
}
