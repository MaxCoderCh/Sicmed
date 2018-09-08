package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.utils.ShiroUtils;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
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

import com.prostate.base.domain.AnamnesisEatingDrugDO;
import com.prostate.base.service.AnamnesisEatingDrugService;
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
@RequestMapping("/base/anamnesisEatingDrug")
public class AnamnesisEatingDrugController {
	@Autowired
	private AnamnesisEatingDrugService anamnesisEatingDrugService;
	
	@GetMapping()
	@RequiresPermissions("base:anamnesisEatingDrug:anamnesisEatingDrug")
	String AnamnesisEatingDrug(){
	    return "base/anamnesisEatingDrug/anamnesisEatingDrug";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:anamnesisEatingDrug:anamnesisEatingDrug")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<AnamnesisEatingDrugDO> anamnesisEatingDrugList = anamnesisEatingDrugService.list(query);
		int total = anamnesisEatingDrugService.count(query);
		PageUtils pageUtils = new PageUtils(anamnesisEatingDrugList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:anamnesisEatingDrug:add")
	String add(){
	    return "base/anamnesisEatingDrug/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:anamnesisEatingDrug:edit")
	String edit(@PathVariable("id") String id,Model model){
		AnamnesisEatingDrugDO anamnesisEatingDrug = anamnesisEatingDrugService.get(id);
		model.addAttribute("anamnesisEatingDrug", anamnesisEatingDrug);
	    return "base/anamnesisEatingDrug/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:anamnesisEatingDrug:add")
	public R save( @Validated(GroupWithoutID.class) AnamnesisEatingDrugDO anamnesisEatingDrug){
		//先判断再插入
		//如果编号和名字都不重复的话
		if (anamnesisEatingDrugService.getByName(anamnesisEatingDrug.getEatingDrugName()) == null
				&& anamnesisEatingDrugService.getByNumber(anamnesisEatingDrug.getEatingDrugNumber())==null){
			anamnesisEatingDrug.setCreateTime(new Date());
			anamnesisEatingDrug.setCreateName(ShiroUtils.getUserId().toString());
			anamnesisEatingDrug.setDelFlag("0");
			if(anamnesisEatingDrugService.save(anamnesisEatingDrug)>0){
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
	@RequiresPermissions("base:anamnesisEatingDrug:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})AnamnesisEatingDrugDO anamnesisEatingDrug){
		//先判断再修改
		//根据新的信息在数据库中查找。判断数据是否发生变化
		AnamnesisEatingDrugDO anamnesisEatingDrugById = anamnesisEatingDrugService.get(anamnesisEatingDrug.getId());
		//名称发生变化
		if (!anamnesisEatingDrugById.getEatingDrugName().equalsIgnoreCase(anamnesisEatingDrug.getEatingDrugName())){
			//判断是不是和数据库其他内容重合
			if (anamnesisEatingDrugService.getByName(anamnesisEatingDrug.getEatingDrugName()) != null){
				return R.error(20001,"名称重复");
			}
		}
		//编号发生重复
		if (!anamnesisEatingDrugById.getEatingDrugNumber().equalsIgnoreCase(anamnesisEatingDrug.getEatingDrugNumber())){
			if (anamnesisEatingDrugService.getByNumber(anamnesisEatingDrug.getEatingDrugNumber())!= null){
				return R.error(20001,"编号重复");
			}
		}
		anamnesisEatingDrug.setUpdateName(ShiroUtils.getUserId().toString());
		anamnesisEatingDrug.setUpdateTime(new Date());
		anamnesisEatingDrugService.update(anamnesisEatingDrug);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:anamnesisEatingDrug:remove")
	public R remove( String id){
		AnamnesisEatingDrugDO anamnesisEatingDrugById = anamnesisEatingDrugService.get(id);
		if (anamnesisEatingDrugById == null){
			return R.error(20004,"信息不存在");
		}
		anamnesisEatingDrugById.setDelFlag("1");
		anamnesisEatingDrugById.setDeleteName(ShiroUtils.getUserId().toString());
		anamnesisEatingDrugById.setDeleteTime(new Date());
		if (anamnesisEatingDrugService.update(anamnesisEatingDrugById) > 0){
			return R.ok();
		}
//		if(anamnesisEatingDrugService.remove(id)>0){
//		return R.ok();
//		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:anamnesisEatingDrug:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids) {
			AnamnesisEatingDrugDO anamnesisEatingDrugDO = anamnesisEatingDrugService.get(id);
			anamnesisEatingDrugDO.setDelFlag("1");
			anamnesisEatingDrugDO.setDeleteName(ShiroUtils.getUserId().toString());
			anamnesisEatingDrugDO.setDeleteTime(new Date());
			anamnesisEatingDrugService.update(anamnesisEatingDrugDO);
		}
//		anamnesisEatingDrugService.batchRemove(ids);
		return R.ok();
	}
	
}
