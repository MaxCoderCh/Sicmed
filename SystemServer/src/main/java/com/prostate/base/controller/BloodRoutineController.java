package com.prostate.base.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.config.Constant;
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

import com.prostate.base.domain.BloodRoutineDO;
import com.prostate.base.service.BloodRoutineService;
import com.prostate.common.utils.R;

/**
 * 血常规量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 10:28:07
 */
 
@Controller
@RequestMapping("/base/bloodRoutine")
public class BloodRoutineController {
	@Autowired
	private BloodRoutineService bloodRoutineService;
	
	@GetMapping()
	@RequiresPermissions("base:bloodRoutine:bloodRoutine")
	String BloodRoutine(){
		return "base/bloodRoutine/bloodRoutine";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:bloodRoutine:bloodRoutine")
	public List<BloodRoutineDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<BloodRoutineDO> bloodRoutineDOS = bloodRoutineService.list(query);
		return bloodRoutineDOS;
	}
	
//	@GetMapping("/add")
//	@RequiresPermissions("base:bloodRoutine:add")
//	String add(){
//	    return "base/bloodRoutine/add";
//	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("base:bloodRoutine:add")
	String add(@PathVariable("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if ("0".equalsIgnoreCase(pId)) {
			model.addAttribute("pName", "无");
		} else {
			//一级菜单的父id为空，报空指针异常。
			model.addAttribute("pName", bloodRoutineService.get(pId).getScaleTitle());
		}
		return  "base/bloodRoutine/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:bloodRoutine:edit")
	String edit(@PathVariable("id") String id,Model model){
		BloodRoutineDO bloodRoutine = bloodRoutineService.get(id);
		model.addAttribute("bloodRoutine", bloodRoutine);
		if(bloodRoutine.getParentId() == null) {
			model.addAttribute("parentScaleTitle", "无");
		}else {
			BloodRoutineDO bloodRoutineDO = bloodRoutineService.get(bloodRoutine.getParentId());
			model.addAttribute("parentScaleTitle", bloodRoutineDO.getScaleTitle());
		}
	    return "base/bloodRoutine/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:bloodRoutine:add")
	public R save( @Validated(GroupWithoutID.class) BloodRoutineDO bloodRoutine){
		if ("0".equalsIgnoreCase(bloodRoutine.getParentId())){
			bloodRoutine.setParentId(null);
		}
		bloodRoutine.setCreateTime(new Date());
		bloodRoutine.setCreateUser(ShiroUtils.getUserId().toString());
		bloodRoutine.setDelFlag("0");
		if(bloodRoutineService.save(bloodRoutine)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:bloodRoutine:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) BloodRoutineDO bloodRoutine){
		if (bloodRoutine.getParentId().equalsIgnoreCase("")){
			bloodRoutine.setParentId(null);
		}
		bloodRoutineService.update(bloodRoutine);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:bloodRoutine:remove")
	public R remove( String id){
		BloodRoutineDO bloodRoutine = bloodRoutineService.get(id);
		bloodRoutine.setDelFlag("1");
		bloodRoutine.setDeleteTime(new Date());
		bloodRoutine.setDeleteUser(ShiroUtils.getUserId().toString());
		if(bloodRoutineService.update(bloodRoutine)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:bloodRoutine:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids) {
			BloodRoutineDO bloodRoutine = bloodRoutineService.get(id);
			bloodRoutine.setDelFlag("1");
			bloodRoutine.setDeleteTime(new Date());
			bloodRoutine.setDeleteUser(ShiroUtils.getUserId().toString());
			bloodRoutineService.update(bloodRoutine);
		}
		return R.ok();
	}


	@GetMapping("/tree")
	@ResponseBody
	public Tree<BloodRoutineDO> tree() {
		Tree<BloodRoutineDO> tree = new Tree<BloodRoutineDO>();
		tree = bloodRoutineService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/bloodRoutine/bloodRoutineTree";
	}
}
