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

import com.prostate.base.domain.AnamnesisTypeDO;
import com.prostate.base.service.AnamnesisTypeService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 
 * 
 * @author xiaobai
 * @email 1992lcg@163.com
 * @date 2018-05-04 14:46:36
 */
 
@Controller
@RequestMapping("/base/anamnesisType")
public class AnamnesisTypeController {
	@Autowired
	private AnamnesisTypeService anamnesisTypeService;
	
	@GetMapping()
	@RequiresPermissions("base:anamnesisType:anamnesisType")
	String AnamnesisType(){
	    return "base/anamnesisType/anamnesisType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:anamnesisType:anamnesisType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AnamnesisTypeDO> anamnesisTypeList = anamnesisTypeService.list(query);
		int total = anamnesisTypeService.count(query);
		PageUtils pageUtils = new PageUtils(anamnesisTypeList, total);
		return pageUtils;
	}

	/**
	 *   弹出新增页面
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("base:anamnesisType:add")
	String add(){
	    return "base/anamnesisType/add";
	}

	/**
	 *   数据回显
	 * @return
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:anamnesisType:edit")
	String edit(@PathVariable("id") String id,Model model){
		AnamnesisTypeDO anamnesisType = anamnesisTypeService.get(id);
		model.addAttribute("anamnesisType", anamnesisType);
	    return "base/anamnesisType/edit";
	}
	
	/**
	 * 保存  实际上新增数据
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:anamnesisType:add")
	public R save( @Validated(GroupWithoutID.class) AnamnesisTypeDO anamnesisType){

		if (anamnesisTypeService.listByName(anamnesisType.getAnamnesisTypeName()).size()==0&&
				anamnesisTypeService.listByNumber(anamnesisType.getAnamnesisTypeNumber()).size()==0){
			anamnesisType.setCreateUser(ShiroUtils.getUserId().toString());
			anamnesisType.setUpdateTime(new Date());
			anamnesisType.setUpdateUser(ShiroUtils.getUserId().toString());
			if(anamnesisTypeService.save(anamnesisType)>0){
				return R.ok();
			}

		}
		return R.error(20001,"该病史类型或者已经存在");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:anamnesisType:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) AnamnesisTypeDO anamnesisType){
		AnamnesisTypeDO anamnesisType01=anamnesisTypeService.get(anamnesisType.getId());
		if (!anamnesisType01.getAnamnesisTypeName().equalsIgnoreCase(anamnesisType.getAnamnesisTypeName())){
			if (anamnesisTypeService.listByName(anamnesisType.getAnamnesisTypeName()).size()>0){
				return R.error(20001,"该病史类型已经存在");
			}
		}
		if (!anamnesisType01.getAnamnesisTypeNumber().equalsIgnoreCase(anamnesisType.getAnamnesisTypeNumber())){
			if (anamnesisTypeService.listByNumber(anamnesisType.getAnamnesisTypeNumber()).size()>0){
				return R.error(20001,"该病史类型编号已经存在");
			}
		}
		anamnesisType.setUpdateUser(ShiroUtils.getUserId().toString());
		anamnesisType.setUpdateTime(new Date());
		if (anamnesisTypeService.update(anamnesisType)>0){
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
	@RequiresPermissions("base:anamnesisType:remove")
	public R remove( String id){
		AnamnesisTypeDO anamnesisTypeDO=new AnamnesisTypeDO();
		anamnesisTypeDO.setId(id);
		anamnesisTypeDO.setDeleteUser(ShiroUtils.getUserId().toString());
		anamnesisTypeDO.setDeleteTime(new Date());
		anamnesisTypeDO.setDelFlag("1");
		if(anamnesisTypeService.update(anamnesisTypeDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 *     批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:anamnesisType:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids){
			AnamnesisTypeDO anamnesisTypeDO=new AnamnesisTypeDO();
			anamnesisTypeDO.setId(id);
			anamnesisTypeDO.setDeleteUser(ShiroUtils.getUserId().toString());
			anamnesisTypeDO.setDeleteTime(new Date());
			anamnesisTypeDO.setDelFlag("1");
			anamnesisTypeService.update(anamnesisTypeDO);
		}

		//anamnesisTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
