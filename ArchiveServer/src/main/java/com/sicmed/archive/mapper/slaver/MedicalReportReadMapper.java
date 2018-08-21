package com.sicmed.archive.mapper.slaver;

import com.sicmed.archive.entity.MedicalReport;

import java.util.List;

public interface MedicalReportReadMapper extends BaseReadMapper<MedicalReport> {

    List<String> selectUrlByParams(MedicalReport medicalReport);
}