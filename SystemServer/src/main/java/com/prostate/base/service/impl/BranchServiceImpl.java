package com.prostate.base.service.impl;

import com.prostate.base.domain.BranchDO;
import com.prostate.base.mapper.slaver.BranchReadMapper;
import com.prostate.base.mapper.master.BranchWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.service.BranchService;



@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchWriteMapper deptWriteMapper;

	@Autowired
	private BranchReadMapper deptReadMapper;


	@Override
	public BranchDO getByName(String deptName) {
		return deptReadMapper.getByName(deptName);
	}

	@Override
	public BranchDO get(String id){
		return deptReadMapper.get(id);
	}

	@Override
	public List<BranchDO> list(Map<String, Object> map){
		return deptReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return deptReadMapper.count(map);
	}

	@Override
	public int save(BranchDO dept){
		return deptWriteMapper.save(dept);
	}

	@Override
	public int update(BranchDO dept){
		return deptWriteMapper.update(dept);
	}

	@Override
	public int remove(String id){
		return deptWriteMapper.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return deptWriteMapper.batchRemove(ids);
	}

	@Override
	public Tree<BranchDO> getTree() {
		List<Tree<BranchDO>> trees = new ArrayList<Tree<BranchDO>>();
		List<BranchDO> sysDepts = deptReadMapper.list(new HashMap<String,Object>(16));

		for (BranchDO sysDept : sysDepts) {
			Tree<BranchDO> tree = new Tree<BranchDO>();
			tree.setId(sysDept.getId().toString());
			tree.setParentId(sysDept.getParentBranchId().toString());
			tree.setText(sysDept.getBranchName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<BranchDO> t = BuildTree.build(trees);
		return t;
	}
//
//	@Override
//	public List<BranchDO> listByName(String name) {
//		return null;
//	}

}
