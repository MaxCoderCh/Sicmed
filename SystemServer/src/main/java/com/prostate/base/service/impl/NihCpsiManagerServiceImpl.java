package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.NihCpsiManagerReadMapper;
import com.prostate.base.mapper.master.NihCpsiManagerWriteMapper;
import com.prostate.base.domain.NihCpsiDO;
import com.prostate.base.service.NihCpsiManagerService;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:59 2018/5/21
 */
@Service
public class NihCpsiManagerServiceImpl implements NihCpsiManagerService {

    @Autowired
    private NihCpsiManagerWriteMapper nihCpsiManagerWriteMapper;


    @Autowired
    private NihCpsiManagerReadMapper nihCpsiManagerReadMapper;
    @Override
    public NihCpsiDO get(String id) {
        return nihCpsiManagerReadMapper.get(id);
    }

    @Override
    public List<NihCpsiDO> list(Map<String, Object> map) {
        return nihCpsiManagerReadMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return nihCpsiManagerReadMapper.count(map);
    }

    @Override
    public int save(NihCpsiDO scaleDO) {
        return nihCpsiManagerWriteMapper.save(scaleDO);
    }

    @Override
    public int update(NihCpsiDO scaleDO) {
        return nihCpsiManagerWriteMapper.update(scaleDO);
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
    public Tree<NihCpsiDO> getTree() {
        List<Tree<NihCpsiDO>> trees = new ArrayList<Tree<NihCpsiDO>>();
        List<NihCpsiDO> nihCpsiDOS = nihCpsiManagerReadMapper.getTree(new HashMap<String, Object>(16));
        for (NihCpsiDO nihCpsiDO:nihCpsiDOS){
            Tree<NihCpsiDO> tree = new Tree<NihCpsiDO>();
            tree.setId(nihCpsiDO.getId());
            tree.setParentId(nihCpsiDO.getParentId());
            tree.setText(nihCpsiDO.getNihCpsiTitle());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", false);
            state.put("selected_arr",false);

            //state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);



        }
        Tree<NihCpsiDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<NihCpsiDO> listByName(String name) {
        return nihCpsiManagerReadMapper.listByName(name);
    }
}
