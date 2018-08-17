package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.LablePatient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LablePatientReadMapper extends BaseReadMapper<LablePatient>{

    List<LablePatient> selectByToken(LablePatient lablePatient);
}