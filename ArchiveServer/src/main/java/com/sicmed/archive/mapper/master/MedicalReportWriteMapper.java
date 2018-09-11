package com.sicmed.archive.mapper.master;

import com.sicmed.archive.entity.MedicalReport;

public interface MedicalReportWriteMapper extends BaseWriteMapper<MedicalReport>{

    int deleteByImgPath(String imgPath);

    int deleteReportByGroup(String reportGroup);
}