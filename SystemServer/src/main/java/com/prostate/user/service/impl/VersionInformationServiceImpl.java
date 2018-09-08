package com.prostate.user.service.impl;

import com.prostate.user.mapper.slaver.VersionInformationReadMapper;
import com.prostate.user.mapper.master.VersionInformationWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.user.entity.VersionInformationDO;
import com.prostate.user.service.VersionInformationService;



@Service
public class VersionInformationServiceImpl implements VersionInformationService {

	@Autowired
	private VersionInformationReadMapper versionInformationReadMapper;

	@Autowired
	private VersionInformationWriteMapper versionInformationWriteMapper;

	@Override
	public VersionInformationDO getByVersionNumber(String versionNumber) {
		return versionInformationReadMapper.getByVersionNumber(versionNumber);
	}

	@Override
	public VersionInformationDO get(String id){
		return versionInformationReadMapper.get(id);
	}
	
	@Override
	public List<VersionInformationDO> list(Map<String, Object> map){
		return versionInformationReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return versionInformationReadMapper.count(map);
	}
	
	@Override
	public int save(VersionInformationDO information){
		return versionInformationWriteMapper.save(information);
	}

	@Override
	public int update(VersionInformationDO information){
		return versionInformationWriteMapper.update(information);
	}

	@Override
	public int remove(String id){
		return versionInformationWriteMapper.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return versionInformationWriteMapper.batchRemove(ids);
	}
	
}
