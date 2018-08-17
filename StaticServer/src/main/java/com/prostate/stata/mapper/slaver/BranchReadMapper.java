package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.Branch;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BranchReadMapper extends BaseReadMapper<Branch>{


    List<Branch> selectByParams(Branch e);
}
