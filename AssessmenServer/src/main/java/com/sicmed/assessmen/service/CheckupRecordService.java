package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.CheckupRecord;
import org.springframework.stereotype.Service;

@Service
public interface CheckupRecordService extends BaseService<CheckupRecord>{
    int deleteByImgPath(String imgPath);
}
