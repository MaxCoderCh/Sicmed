package com.sicmed.archive.service;

import com.sicmed.archive.entity.MedicalReport;

import java.util.List;

public interface MedicalReportService extends BaseService<MedicalReport> {
    List<String> selectUrlByParams(MedicalReport medicalReport);

    int deleteByImgPath(String imgPath);

    int deleteReportByGroup(String reportGroup);
}
