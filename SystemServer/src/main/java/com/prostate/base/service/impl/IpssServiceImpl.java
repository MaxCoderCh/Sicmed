package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.IpssManagerReadMapper;
import com.prostate.base.mapper.master.IpssManagerWriteMapper;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.IpssDO;
import com.prostate.base.service.IpssManagerService;



@Service
public class IpssServiceImpl implements IpssManagerService {
	@Autowired
	private IpssManagerWriteMapper ipssManagerWriteMapper;

	@Autowired
	private IpssManagerReadMapper ipssManagerReadMapper;
	
	@Override
	public IpssDO get(String id){
		return ipssManagerReadMapper.get(id);
	}
	
	@Override
	public List<IpssDO> list(Map<String, Object> map){
		return ipssManagerReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ipssManagerReadMapper.count(map);
	}
	
	@Override
	public int save(IpssDO ipss){
		return ipssManagerWriteMapper.save(ipss);
	}
	
	@Override
	public int update(IpssDO ipss){
		return ipssManagerWriteMapper.update(ipss);
	}
	
	@Override
	public int remove(String id){
		return ipssManagerWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return ipssManagerWriteMapper.batchRemove(ids);
	}


	@Override
	public Tree<IpssDO> getTree() {
		List<Tree<IpssDO>> trees = new ArrayList<Tree<IpssDO>>();
		List<IpssDO> ipssDOS = ipssManagerReadMapper.getTree(new HashMap<String, Object>(16));
		for (IpssDO ipssDO:ipssDOS){
			Tree<IpssDO> tree = new Tree<IpssDO>();
			tree.setId(ipssDO.getId());
			tree.setParentId(ipssDO.getParentId());
			tree.setText(ipssDO.getIpssTitle());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", false);
			state.put("selected_arr",false);
			tree.setState(state);
			trees.add(tree);
		}
		Tree<IpssDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<IpssDO> listByName(String name) {
		return ipssManagerReadMapper.listByName(name);
	}
	
}
