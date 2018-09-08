package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.ScaleManagerReadMapper;
import com.prostate.base.mapper.master.ScaleManagerWriteMapper;
import com.prostate.base.domain.ScaleDO;
import com.prostate.base.service.ScaleManagerService;
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
 * @Date: Created in 10:46 2018/5/14
 */
@Service
public class ScaleManagerServiceImpl implements ScaleManagerService {


    @Autowired
    private ScaleManagerWriteMapper scaleManagerWriteMapper;


    @Autowired
    private ScaleManagerReadMapper scaleManagerReadMapper;


    @Override
    public ScaleDO get(String id) {
        return scaleManagerReadMapper.get(id);
    }

    @Override
    public List<ScaleDO> list(Map<String, Object> map) {
        return scaleManagerReadMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return scaleManagerReadMapper.count(map);
    }

    @Override
    public int save(ScaleDO scaleDO) {
        return scaleManagerWriteMapper.save(scaleDO);
    }

    @Override
    public int update(ScaleDO scaleDO) {
        return scaleManagerWriteMapper.update(scaleDO);
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
    public Tree<ScaleDO> getTree() {
        List<Tree<ScaleDO>> trees = new ArrayList<Tree<ScaleDO>>();
        List<ScaleDO> scaleDOS = scaleManagerReadMapper.getTree(new HashMap<String, Object>(16));
        for (ScaleDO scaleDO:scaleDOS){
            Tree<ScaleDO> tree = new Tree<ScaleDO>();
            tree.setId(scaleDO.getId());
            tree.setParentId(scaleDO.getParentId());
            tree.setText(scaleDO.getScaleTitle());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", false);
            state.put("selected_arr",false);

            //state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);



        }
        Tree<ScaleDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<ScaleDO> listByName(String name) {
        return scaleManagerReadMapper.listByName(name);
    }
}

