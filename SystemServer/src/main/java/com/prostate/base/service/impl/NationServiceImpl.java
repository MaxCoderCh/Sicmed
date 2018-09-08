package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.NationReadMapper;
import com.prostate.base.mapper.master.NationWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.NationDO;
import com.prostate.base.service.NationService;



@Service
public class NationServiceImpl implements NationService {

	@Autowired
	private NationWriteMapper nationWriteMapper;

	@Autowired
	private NationReadMapper nationReadMapper;

	@Override
	public NationDO get(String id){
		return nationReadMapper.get(id);
	}

	@Override
	public NationDO getByName(String nationName) {
		return nationReadMapper.getByName(nationName);
	}
//
//	@Override
//	public int batchUpdate(String[] ids) {
//		return nationDao.batchUpdate(ids);
//	}

	@Override
	public NationDO getByNumber(String nationNumber) {
		return nationReadMapper.getByNumber(nationNumber);
	}

	@Override
	public List<NationDO> list(Map<String, Object> map){
		return nationReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return nationReadMapper.count(map);
	}

	@Override
	public int save(NationDO nation){
		return nationWriteMapper.save(nation);
	}

	@Override
	public int update(NationDO nation){
		return nationWriteMapper.update(nation);
	}

	@Override
	public int remove(String id){
		return nationWriteMapper.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return nationWriteMapper.batchRemove(ids);
	}

}
