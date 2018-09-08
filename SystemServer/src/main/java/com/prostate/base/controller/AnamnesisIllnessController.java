package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.utils.ShiroUtils;
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

import com.prostate.base.domain.AnamnesisIllnessDO;
import com.prostate.base.service.AnamnesisIllnessService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
 
@Controller
@RequestMapping("/base/anamnesisIllness")
public class AnamnesisIllnessController {
	@Autowired
	private AnamnesisIllnessService anamnesisIllnessService;
	
	@GetMapping()
	@RequiresPermissions("base:anamnesisIllness:anamnesisIllness")
	String AnamnesisIllness(){
	    return "base/anamnesisIllness/anamnesisIllness";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:anamnesisIllness:anamnesisIllness")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AnamnesisIllnessDO> anamnesisIllnessList = anamnesisIllnessService.list(query);
		int total = anamnesisIllnessService.count(query);
		PageUtils pageUtils = new PageUtils(anamnesisIllnessList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:anamnesisIllness:add")
	String add(){
	    return "base/anamnesisIllness/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:anamnesisIllness:edit")
	String edit(@PathVariable("id") String id,Model model){
		AnamnesisIllnessDO anamnesisIllness = anamnesisIllnessService.get(id);
		model.addAttribute("anamnesisIllness", anamnesisIllness);
	    return "base/anamnesisIllness/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:anamnesisIllness:add")
	public R save(@Validated(GroupWithoutID.class) AnamnesisIllnessDO anamnesisIllness){

		if (anamnesisIllnessService.listByName(anamnesisIllness.getAnamnesisIllnessName()).size()==0&&
				anamnesisIllnessService.listByNumber(anamnesisIllness.getAnamnesisIllnessNumber()).size()==0){
			anamnesisIllness.setCreateUser(ShiroUtils.getUserId().toString());
			anamnesisIllness.setUpdateTime(new Date());
			anamnesisIllness.setUpdateUser(ShiroUtils.getUserId().toString());
			if(anamnesisIllnessService.save(anamnesisIllness)>0){
				return R.ok();
			}

		}
		return R.error(20001,"该基础疾病名称或者编号已经存在");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:anamnesisIllness:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class}) AnamnesisIllnessDO anamnesisIllness){
		AnamnesisIllnessDO anamnesisIllnessDO01=anamnesisIllnessService.get(anamnesisIllness.getId());
		if (!anamnesisIllnessDO01.getAnamnesisIllnessName().equalsIgnoreCase(anamnesisIllness.getAnamnesisIllnessName())){
			if (anamnesisIllnessService.listByName(anamnesisIllness.getAnamnesisIllnessName()).size()>0){
				return R.error(20001,"该疾病名称已经存在");
			}
		}
		if (!anamnesisIllnessDO01.getAnamnesisIllnessNumber().equalsIgnoreCase(anamnesisIllness.getAnamnesisIllnessNumber())){
			if (anamnesisIllnessService.listByNumber(anamnesisIllness.getAnamnesisIllnessNumber()).size()>0){
				return R.error(20001,"该疾病名称对应编号已经存在");
			}
		}
		anamnesisIllness.setUpdateUser(ShiroUtils.getUserId().toString());
		anamnesisIllness.setUpdateTime(new Date());
		if (anamnesisIllnessService.update(anamnesisIllness)>0){
			return R.ok();
		}else {
			return R.error();
		}

	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:anamnesisIllness:remove")
	public R remove( String id){

		AnamnesisIllnessDO anamnesisIllnessDO=new AnamnesisIllnessDO();
		anamnesisIllnessDO.setId(id);
		anamnesisIllnessDO.setDeleteUser(ShiroUtils.getUserId().toString());
		anamnesisIllnessDO.setDeleteTime(new Date());
		anamnesisIllnessDO.setDelFlag("1");
		if(anamnesisIllnessService.update(anamnesisIllnessDO)>0){
			return R.ok();
		}
		return R.error();

	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:anamnesisIllness:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){

		for (String id:ids){
			AnamnesisIllnessDO anamnesisIllnessDO=new AnamnesisIllnessDO();
			anamnesisIllnessDO.setId(id);
			anamnesisIllnessDO.setDeleteUser(ShiroUtils.getUserId().toString());
			anamnesisIllnessDO.setDeleteTime(new Date());
			anamnesisIllnessDO.setDelFlag("1");
			anamnesisIllnessService.update(anamnesisIllnessDO);
		}

		//anamnesisTypeService.batchRemove(ids);
		return R.ok();

	}
	
}
