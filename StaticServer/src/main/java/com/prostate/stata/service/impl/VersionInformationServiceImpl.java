package com.prostate.stata.service.impl;


import com.prostate.stata.entity.VersionInformationDO;
import com.prostate.stata.mapper.master.VersionInformationWriteMapper;
import com.prostate.stata.mapper.slaver.VersionInformationReadMapper;
import com.prostate.stata.service.VersionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
