package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Branch;
import com.prostate.stata.entity.Hospital;
import com.prostate.stata.mapper.master.BranchWriteMapper;
import com.prostate.stata.mapper.master.HospitalWriteMapper;
import com.prostate.stata.mapper.slaver.BranchReadMapper;
import com.prostate.stata.mapper.slaver.HospitalReadMapper;
import com.prostate.stata.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchWriteMapper branchWriteMapper;

    @Autowired
    private BranchReadMapper branchReadMapper;

    @Override
    public int insertSelective(Branch branch) {
        return 0;
    }

    @Override
    public int updateSelective(Branch branch) {
        return 0;
    }

    @Override
    public Branch selectById(String id) {
        return branchReadMapper.selectById(id);
    }

    @Override
    public List<Branch> selectByParams(Branch branch) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "JSON_BRANCH",key = "'json_data'")
    @Override
    public Map<String, String> getBranchJson() {
        Map<String, String> map = new LinkedHashMap<>();
        List<Branch> branches = branchReadMapper.selectByParams(null);

        if (branches.size() == 0 || branches == null) {
            return null;
        }
        for (Branch branch : branches) {
            map.put(branch.getId(), branch.getBranchName());
        }
        return map;
    }
}
