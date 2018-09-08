package com.prostate.base.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.BranchDO;
import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.service.BranchService;
import com.prostate.common.utils.R;

/**
 * 科室表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
 
@Controller
@RequestMapping("/base/dept")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@GetMapping()
	@RequiresPermissions("base:dept:dept")
	String Branch(){
		return "base/dept/dept";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:dept:dept")
	public List<BranchDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<BranchDO> sysBranchList = branchService.list(query);
		return sysBranchList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("base:dept:add")
	String add(@PathVariable("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equalsIgnoreCase("0")) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", branchService.get(pId).getBranchName());
		}
		return  "base/dept/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:dept:edit")
	String edit(@PathVariable("id") String deptId, Model model) {
		BranchDO sysBranch = branchService.get(deptId);
		model.addAttribute("branch", sysBranch);
		if(sysBranch.getParentBranchId() == null) {
			model.addAttribute("parentBranchName", "无");
		}else {
			BranchDO parBranch = branchService.get(sysBranch.getParentBranchId());
			model.addAttribute("parentBranchName", parBranch.getBranchName());
		}
		return  "base/dept/edit";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:dept:add")
	public R save( @Validated(GroupWithoutID.class)BranchDO dept){
		BranchDO deptByName=  branchService.getByName(dept.getBranchName());
		if (deptByName == null ){
			dept.setCreateUser(ShiroUtils.getUserId().toString());
			dept.setCreateTime(new Date());
			dept.setDelFlag("0");
			if (dept.getParentBranchId().equalsIgnoreCase("0")){
				dept.setParentBranchId(null);
			}
			if(branchService.save(dept)>0){
				return R.ok();
			}
		}
		return R.error(20001,"名称或者编号重复");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:dept:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})BranchDO dept){
		BranchDO deptDO = branchService.get(dept.getId());
		if ( !deptDO.getBranchName().equalsIgnoreCase(dept.getBranchName())){
			if (branchService.getByName(dept.getBranchName()) != null){
				return R.error(20001,"名称重复");
			}
		}
		if (dept.getParentBranchId().equalsIgnoreCase("")){
			dept.setParentBranchId(null);
		}
		dept.setUpdateUser(ShiroUtils.getUserId().toString());
		dept.setUpdateTime(new Date());
		branchService.update(dept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:dept:remove")
	public R remove(String id){
		BranchDO deptDO = branchService.get(id);
		deptDO.setDelFlag("1");
		deptDO.setDeleteUser(ShiroUtils.getUserId().toString());
		deptDO.setDeleteTime(new Date());
		if(branchService.update(deptDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:dept:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids) {
			BranchDO deptDO = branchService.get(id);
			deptDO.setDelFlag("1");
			deptDO.setDeleteUser(ShiroUtils.getUserId().toString());
			deptDO.setDeleteTime(new Date());
			branchService.update(deptDO);
		}
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<BranchDO> tree() {
		Tree<BranchDO> tree = new Tree<BranchDO>();
		tree = branchService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/dept/deptTree";
	}
}
