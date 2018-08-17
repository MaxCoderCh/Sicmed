package com.prostate.stata.service;

import com.prostate.stata.entity.Branch;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface BranchService extends BaseService<Branch> {

    Map<String,String> getBranchJson();

}
