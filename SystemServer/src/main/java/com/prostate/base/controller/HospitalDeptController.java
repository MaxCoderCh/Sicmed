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

import com.prostate.base.domain.HospitalDeptDO;
import com.prostate.base.service.HospitalDeptService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 医院科室表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
 
@Controller
@RequestMapping("/base/hospitalDept")
public class HospitalDeptController {
	@Autowired
	private HospitalDeptService hospitalDeptService;
	
	@GetMapping()
	@RequiresPermissions("base:hospitalDept:hospitalDept")
	String HospitalDept(){
	    return "base/hospitalDept/hospitalDept";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:hospitalDept:hospitalDept")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HospitalDeptDO> hospitalDeptList = hospitalDeptService.list(query);
		int total = hospitalDeptService.count(query);
		PageUtils pageUtils = new PageUtils(hospitalDeptList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:hospitalDept:add")
	String add(){
	    return "base/hospitalDept/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:hospitalDept:edit")
	String edit(@PathVariable("id") String id,Model model){
		HospitalDeptDO hospitalDept = hospitalDeptService.get(id);
		model.addAttribute("hospitalDept", hospitalDept);
	    return "base/hospitalDept/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:hospitalDept:add")
	public R save( @Validated(GroupWithoutID.class)HospitalDeptDO hospitalDept){
		if(hospitalDeptService.save(hospitalDept)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:hospitalDept:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) HospitalDeptDO hospitalDept){
		hospitalDeptService.update(hospitalDept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:hospitalDept:remove")
	public R remove( String id){
		if(hospitalDeptService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:hospitalDept:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		hospitalDeptService.batchRemove(ids);
		return R.ok();
	}
	
}
