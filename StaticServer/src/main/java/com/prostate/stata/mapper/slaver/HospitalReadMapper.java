package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.Hospital;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalReadMapper extends BaseReadMapper<Hospital>{


    @Override
    List<Hospital> selectByParams(Hospital e);
}