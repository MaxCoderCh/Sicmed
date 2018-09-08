package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.BloodRoutineReadMapper;
import com.prostate.base.mapper.master.BloodRoutineWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.BloodRoutineDO;
import com.prostate.base.service.BloodRoutineService;



@Service
public class BloodRoutineServiceImpl implements BloodRoutineService {

	@Autowired
	private BloodRoutineWriteMapper bloodRoutineWriteMapper;

	@Autowired
	private BloodRoutineReadMapper bloodRoutineReadMapper;

	@Override
	public Tree<BloodRoutineDO> getTree() {
		List<Tree<BloodRoutineDO>> trees = new ArrayList<Tree<BloodRoutineDO>>();
		List<BloodRoutineDO> bloodRoutineDOS = bloodRoutineReadMapper.list(new HashMap<String,Object>(16));

		for (BloodRoutineDO bloodRoutineDO : bloodRoutineDOS) {
			Tree<BloodRoutineDO> tree = new Tree<BloodRoutineDO>();
			tree.setId(bloodRoutineDO.getId().toString());
			tree.setParentId(bloodRoutineDO.getParentId().toString());
			tree.setText(bloodRoutineDO.getScaleTitle());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<BloodRoutineDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public BloodRoutineDO getByNumber(String deptNumber) {
		return bloodRoutineReadMapper.getByNumber(deptNumber);
	}

	@Override
	public BloodRoutineDO getByName(String deptName) {
		return bloodRoutineReadMapper.getByName(deptName);
	}

	@Override
	public BloodRoutineDO get(String id){
		return bloodRoutineReadMapper.get(id);
	}
	
	@Override
	public List<BloodRoutineDO> list(Map<String, Object> map){
		return bloodRoutineReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bloodRoutineReadMapper.count(map);
	}
	
	@Override
	public int save(BloodRoutineDO bloodRoutine){
		return bloodRoutineWriteMapper.save(bloodRoutine);
	}
	
	@Override
	public int update(BloodRoutineDO bloodRoutine){
		return bloodRoutineWriteMapper.update(bloodRoutine);
	}
	
	@Override
	public int remove(String id){
		return bloodRoutineWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return bloodRoutineWriteMapper.batchRemove(ids);
	}
	
}
