package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.CityReadMapper;
import com.prostate.base.mapper.master.CityWriteMapper;
import com.prostate.base.service.CityService;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.CityDO;


@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityWriteMapper cityWriteMapper;

	@Autowired
	private CityReadMapper cityReadMapper;


	@Override
	public CityDO get(String id){
		return cityReadMapper.get(id);
	}

	@Override
	public CityDO getParent(String id) {
		return cityReadMapper.getParent(id);
	}

	@Override
	public List<CityDO> getChild(Map<String, Object> map) {
		return cityReadMapper.getChild(map);
	}

	@Override
	public List<CityDO> list(Map<String, Object> map){
		return cityReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return cityReadMapper.count(map);
	}

	@Override
	public int save(CityDO city){
		return cityWriteMapper.save(city);
	}

	@Override
	public int update(CityDO city){
		return cityWriteMapper.update(city);
	}

	@Override
	public int remove(String id){
		return cityWriteMapper.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return cityWriteMapper.batchRemove(ids);
	}

	@Override
	public Tree<CityDO> getTree() {
		List<Tree<CityDO>> trees = new ArrayList<Tree<CityDO>>();
		List<CityDO> sysDepts = cityReadMapper.list(new HashMap<String,Object>(16));

		for (CityDO sysDept : sysDepts) {
			Tree<CityDO> tree = new Tree<CityDO>();
			tree.setId(sysDept.getId().toString());
			tree.setParentId(sysDept.getParentCityId().toString());
			tree.setText(sysDept.getCityName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<CityDO> t = BuildTree.build(trees);

		System.out.println("=============================================================");
		System.out.println(t);
		return t;
	}
}
