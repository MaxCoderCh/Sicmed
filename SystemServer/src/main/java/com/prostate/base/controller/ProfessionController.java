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

import com.prostate.base.domain.ProfessionDO;
import com.prostate.base.service.ProfessionService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:36
 */
 
@Controller
@RequestMapping("/base/profession")
public class ProfessionController {
	@Autowired
	private ProfessionService professionService;
	
	@GetMapping()
	@RequiresPermissions("base:profession:profession")
	String Profession(){
	    return "base/profession/profession";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:profession:profession")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProfessionDO> professionList = professionService.list(query);
		int total = professionService.count(query);
		PageUtils pageUtils = new PageUtils(professionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:profession:add")
	String add(){
	    return "base/profession/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:profession:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProfessionDO profession = professionService.get(id);
		model.addAttribute("profession", profession);
	    return "base/profession/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:profession:add")
	public R save(@Validated(GroupWithoutID.class) ProfessionDO profession){
		if (professionService.listByName(profession.getProfessionName()).size()==0&&
				professionService.listByNumber(profession.getProfessionNumber()).size()==0){
			profession.setCreateUser(ShiroUtils.getUserId().toString());
			profession.setUpdateTime(new Date());
			profession.setUpdateUser(ShiroUtils.getUserId().toString());
			if(professionService.save(profession)>0){
				return R.ok();
			}

		}
		return R.error(20001,"该职业名称或者编号已经存在");


	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:profession:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class})  ProfessionDO profession){
		ProfessionDO professionDO=professionService.get(profession.getId());
		if (!professionDO.getProfessionName().equalsIgnoreCase(profession.getProfessionName())){
			if (professionService.listByName(profession.getProfessionName()).size()>0){
				return R.error(20001,"该职业名称已经存在");
			}
		}
		if (!professionDO.getProfessionNumber().equalsIgnoreCase(profession.getProfessionNumber())){
			if (professionService.listByNumber(profession.getProfessionNumber()).size()>0){
				return R.error(20001,"该职业编号已经存在");
			}
		}
		profession.setUpdateUser(ShiroUtils.getUserId().toString());
		profession.setUpdateTime(new Date());
		if (professionService.update(profession)>0){
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
	@RequiresPermissions("base:profession:remove")
	public R remove( String id){
		ProfessionDO professionDO=new ProfessionDO();
		professionDO.setId(id);
		professionDO.setDeleteUser(ShiroUtils.getUserId().toString());
		professionDO.setDeleteTime(new Date());
		professionDO.setDelFlag("1");
		if(professionService.update(professionDO)>0){
			return R.ok();
		}
		return R.error();


	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:profession:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids){
			ProfessionDO professionDO=new ProfessionDO();
			professionDO.setId(id);
			professionDO.setDeleteUser(ShiroUtils.getUserId().toString());
			professionDO.setDeleteTime(new Date());
			professionDO.setDelFlag("1");
			professionService.update(professionDO);
		}

		//anamnesisTypeService.batchRemove(ids);
		return R.ok();


	}
	
}
