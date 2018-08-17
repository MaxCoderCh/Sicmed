package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.DoctorTitle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorTitleReadMapper extends BaseReadMapper<DoctorTitle>{


    DoctorTitle selectById(String id);

    List<DoctorTitle> selectByParams(DoctorTitle e);
}