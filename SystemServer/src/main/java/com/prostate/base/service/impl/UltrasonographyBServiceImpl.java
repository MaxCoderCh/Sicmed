package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.UltrasonographyBReadMapper;
import com.prostate.base.mapper.master.UltrasonographyBWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.UltrasonographyBDO;
import com.prostate.base.service.UltrasonographyBService;



@Service
public class UltrasonographyBServiceImpl implements UltrasonographyBService {

	@Autowired
	private UltrasonographyBWriteMapper ultrasonographyBWriteMapper;

	@Autowired
	private UltrasonographyBReadMapper ultrasonographyBReadMapper;
	
	@Override
	public UltrasonographyBDO get(String id) {
		return ultrasonographyBReadMapper.get(id);
	}

	@Override
	public List<UltrasonographyBDO> list(Map<String, Object> map) {
		return ultrasonographyBReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int save(UltrasonographyBDO ultrasonographyBDO) {
		return ultrasonographyBWriteMapper.save(ultrasonographyBDO);
	}

	@Override
	public int update(UltrasonographyBDO ultrasonographyBDO) {
		return ultrasonographyBWriteMapper.update(ultrasonographyBDO);
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
	public Tree<UltrasonographyBDO> getTree() {
		List<Tree<UltrasonographyBDO>> trees = new ArrayList<Tree<UltrasonographyBDO>>();
		List<UltrasonographyBDO> urineFlowRateDOS = ultrasonographyBReadMapper.getTree(new HashMap<String,Object>(16));

		for (UltrasonographyBDO urineFlowRateDO : urineFlowRateDOS) {
			Tree<UltrasonographyBDO> tree = new Tree<UltrasonographyBDO>();
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
		Tree<UltrasonographyBDO> t = BuildTree.build(trees);
		return t;
	}
}
