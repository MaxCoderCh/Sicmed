package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.SpecificAntigenReadMapper;
import com.prostate.base.mapper.master.SpecificAntigenWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.SpecificAntigenDO;
import com.prostate.base.service.SpecificAntigenService;



@Service
public class SpecificAntigenServiceImpl implements SpecificAntigenService {

	@Autowired
	private SpecificAntigenWriteMapper specificAntigenWriteMapper;

	@Autowired
	private SpecificAntigenReadMapper specificAntigenReadMapper;
	@Override
	public List<SpecificAntigenDO> getByParentId(String id) {
		return specificAntigenReadMapper.getByParentId(id);
	}

	@Override
	public SpecificAntigenDO get(String id){
		return specificAntigenReadMapper.get(id);
	}

	@Override
	public Tree<SpecificAntigenDO> getTree() {
		//声明一个二维列表，和二维数组一样。第一级是列表结构，第二级是树形结构
		List<Tree<SpecificAntigenDO>> trees = new ArrayList<Tree<SpecificAntigenDO>>();
		//查询所有的信息存入列表中。
		List<SpecificAntigenDO> specificAntigenDOS =
				specificAntigenReadMapper.list(new HashMap<String,Object>(16));
		for (SpecificAntigenDO specificAntigenDO : specificAntigenDOS) {
			//声明树形结构
			Tree<SpecificAntigenDO> tree = new Tree<SpecificAntigenDO>();
			//每个树末节点的id
			tree.setId(specificAntigenDO.getId().toString());
			//每一个末节点的上级节点
			tree.setParentId(specificAntigenDO.getParentId().toString());
			//每个节点的文字信息
			tree.setText(specificAntigenDO.getScaleTitle());
			Map<String, Object> state = new HashMap<>(16);

			state.put("opened", true);
			tree.setState(state);
			//每一个树形结构都存入列表
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<SpecificAntigenDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<SpecificAntigenDO> list(Map<String, Object> map){
		return specificAntigenReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specificAntigenReadMapper.count(map);
	}
	
	@Override
	public int save(SpecificAntigenDO specificAntigen){
		return specificAntigenWriteMapper.save(specificAntigen);
	}
	
	@Override
	public int update(SpecificAntigenDO specificAntigen){
		return specificAntigenWriteMapper.update(specificAntigen);
	}
	
	@Override
	public int remove(String id){
		return specificAntigenWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return specificAntigenWriteMapper.batchRemove(ids);
	}
	
}
