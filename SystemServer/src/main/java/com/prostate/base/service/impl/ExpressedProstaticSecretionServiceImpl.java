package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.ExpressedProstaticSecretionReadMapper;
import com.prostate.base.mapper.master.ExpressedProstaticSecretionWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.ExpressedProstaticSecretionDO;
import com.prostate.base.service.ExpressedProstaticSecretionService;



@Service
public class ExpressedProstaticSecretionServiceImpl implements ExpressedProstaticSecretionService {
	@Autowired
	private ExpressedProstaticSecretionWriteMapper expressedProstaticSecretionWriteMapper;
	@Autowired
	private ExpressedProstaticSecretionReadMapper expressedProstaticSecretionReadMapper;


	@Override
	public ExpressedProstaticSecretionDO get(String id){
		return expressedProstaticSecretionReadMapper.get(id);
	}
	
	@Override
	public List<ExpressedProstaticSecretionDO> list(Map<String, Object> map){
		return expressedProstaticSecretionReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return expressedProstaticSecretionReadMapper.count(map);
	}
	
	@Override
	public int save(ExpressedProstaticSecretionDO expressedProstaticSecretion){
		return expressedProstaticSecretionWriteMapper.save(expressedProstaticSecretion);
	}
	
	@Override
	public int update(ExpressedProstaticSecretionDO expressedProstaticSecretion){
		return expressedProstaticSecretionWriteMapper.update(expressedProstaticSecretion);
	}
	
	@Override
	public int remove(String id){
		return expressedProstaticSecretionWriteMapper.remove(id);
	}

	@Override
	public Tree<ExpressedProstaticSecretionDO> getTree() {
		//声明一个二维列表，和二维数组一样。第一级是列表结构，第二级是树形结构
		List<Tree<ExpressedProstaticSecretionDO>> trees = new ArrayList<Tree<ExpressedProstaticSecretionDO>>();
		//查询所有的信息存入列表中。
		List<ExpressedProstaticSecretionDO> bloodRoutineDOS =
				expressedProstaticSecretionReadMapper.list(new HashMap<String,Object>(16));
		for (ExpressedProstaticSecretionDO bloodRoutineDO : bloodRoutineDOS) {
			//声明树形结构
			Tree<ExpressedProstaticSecretionDO> tree = new Tree<ExpressedProstaticSecretionDO>();
			//每个树末节点的id
			tree.setId(bloodRoutineDO.getId().toString());
			//每一个末节点的上级节点
			tree.setParentId(bloodRoutineDO.getParentId().toString());
			//每个节点的文字信息
			tree.setText(bloodRoutineDO.getScaleTitle());
			Map<String, Object> state = new HashMap<>(16);

			state.put("opened", true);
			tree.setState(state);
			//每一个树形结构都存入列表
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<ExpressedProstaticSecretionDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public int batchRemove(String[] ids){
		return expressedProstaticSecretionWriteMapper.batchRemove(ids);
	}
	
}
