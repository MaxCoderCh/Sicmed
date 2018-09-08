package com.prostate.base.controller;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.DeptIllnessDO;
import com.prostate.base.service.DeptIllnessService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 科室疾病中间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
 
@Controller
@RequestMapping("/base/deptIllness")
public class DeptIllnessController {
	@Autowired
	private DeptIllnessService deptIllnessService;
	
	@GetMapping()
	@RequiresPermissions("base:deptIllness:deptIllness")
	String DeptIllness(){
	    return "base/deptIllness/deptIllness";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:deptIllness:deptIllness")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeptIllnessDO> deptIllnessList = deptIllnessService.list(query);
		int total = deptIllnessService.count(query);
		PageUtils pageUtils = new PageUtils(deptIllnessList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:deptIllness:add")
	String add(){
	    return "base/deptIllness/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:deptIllness:edit")
	String edit(@PathVariable("id") String id,Model model){
		DeptIllnessDO deptIllness = deptIllnessService.get(id);
		model.addAttribute("deptIllness", deptIllness);
	    return "base/deptIllness/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:deptIllness:add")
	public R save(@Validated(GroupWithoutID.class) DeptIllnessDO deptIllness){
		if(deptIllnessService.save(deptIllness)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:deptIllness:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})DeptIllnessDO deptIllness){
		deptIllnessService.update(deptIllness);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:deptIllness:remove")
	public R remove( String id){
		if(deptIllnessService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:deptIllness:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		deptIllnessService.batchRemove(ids);
		return R.ok();
	}
	
}
