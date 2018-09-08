package com.prostate.system.service.impl;

import com.prostate.system.mapper.slaver.DeptReadMapper;
import com.prostate.system.mapper.master.DeptWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import com.prostate.system.domain.DeptDO;
import com.prostate.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptWriteMapper deptWriteMapper;

	@Autowired
	private DeptReadMapper deptReadMapper;

	@Override
	public DeptDO get(Long deptId){
		return deptReadMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return deptReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return deptReadMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		return deptWriteMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		return deptWriteMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return deptWriteMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return deptWriteMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = deptReadMapper.list(new HashMap<String,Object>(16));

		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		System.out.println(".////////////////////////////////////");
		System.out.println(trees.get(0));
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = deptReadMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

}
