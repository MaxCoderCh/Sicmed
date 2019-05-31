package com.prostate.stata.controller;

import com.prostate.stata.entity.Branch;
import com.prostate.stata.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "branch")
public class BranchController extends BaseController {

    @Autowired
    private BranchService branchService;

    @GetMapping(value = "getById")
    public Map<String, Object> getById(String id) {
        Branch branch = branchService.selectById(id);
        return querySuccessResponse(branch.getBranchName());
    }

    @GetMapping(value = "getBranchList")
    public Map<String, Object> getBranchList() {

        List<Branch> branchList = branchService.getBranchList();
        if (branchList.isEmpty()){
            return queryEmptyResponse();
        }
        return querySuccessResponse(branchList);
    }

}