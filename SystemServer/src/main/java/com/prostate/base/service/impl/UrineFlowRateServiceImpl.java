package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.UrineFlowRateReadMapper;
import com.prostate.base.mapper.master.UrineFlowRateWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.UrineFlowRateDO;
import com.prostate.base.service.UrineFlowRateService;



@Service
public class UrineFlowRateServiceImpl implements UrineFlowRateService {

	@Autowired
	private UrineFlowRateWriteMapper urineFlowRateWriteMapper;

	@Autowired
	private UrineFlowRateReadMapper urineFlowRateReadMapper;
	
	@Override
	public UrineFlowRateDO get(String id) {
		return urineFlowRateReadMapper.get(id);
	}

	@Override
	public List<UrineFlowRateDO> list(Map<String, Object> map) {
		return urineFlowRateReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int save(UrineFlowRateDO urineFlowRateDO) {
		return urineFlowRateWriteMapper.save(urineFlowRateDO);
	}

	@Override
	public int update(UrineFlowRateDO urineFlowRateDO) {
		return urineFlowRateWriteMapper.update(urineFlowRateDO);
	}

	@Override
	public int remove(String id) {
		return 0;
	}

	@Override
	public int batchRemove(String[] ids) {
		return 0;
	}

	@Override
	public String[] listParentId() {
		return new String[0];
	}
/*
	@Override
	public int getDeptUserNumber(String id) {
		return 0;
	}*/

	@Override
	public Tree<UrineFlowRateDO> getTree() {
		List<Tree<UrineFlowRateDO>> trees = new ArrayList<Tree<UrineFlowRateDO>>();
		List<UrineFlowRateDO> urineFlowRateDOS = urineFlowRateReadMapper.getTree(new HashMap<String,Object>(16));

		for (UrineFlowRateDO urineFlowRateDO : urineFlowRateDOS) {
			Tree<UrineFlowRateDO> tree = new Tree<UrineFlowRateDO>();
			tree.setId(urineFlowRateDO.getId().toString());
			if (urineFlowRateDO.getParentId()==null||urineFlowRateDO.getParentId().equals("")){

			}else {
				tree.setParentId(urineFlowRateDO.getParentId().toString());
			}

			tree.setText(urineFlowRateDO.getScaleTitle());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<UrineFlowRateDO> t = BuildTree.build(trees);
		return t;
	}
}
