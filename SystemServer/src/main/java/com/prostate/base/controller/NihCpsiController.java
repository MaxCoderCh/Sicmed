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

import com.prostate.base.domain.NihCpsiDO;
import com.prostate.base.service.NihCpsiService;
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
@RequestMapping("/base/nihCpsi")
public class NihCpsiController {
	@Autowired
	private NihCpsiService nihCpsiService;
	
	@GetMapping()
	@RequiresPermissions("base:nihCpsi:nihCpsi")
	String NihCpsi(){
	    return "base/nihCpsi/nihCpsi";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:nihCpsi:nihCpsi")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NihCpsiDO> nihCpsiList = nihCpsiService.list(query);
		int total = nihCpsiService.count(query);
		PageUtils pageUtils = new PageUtils(nihCpsiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:nihCpsi:add")
	String add(){
	    return "base/nihCpsi/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:nihCpsi:edit")
	String edit(@PathVariable("id") String id,Model model){
		NihCpsiDO nihCpsi = nihCpsiService.get(id);
		model.addAttribute("nihCpsi", nihCpsi);
	    return "base/nihCpsi/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:nihCpsi:add")
	public R save( @Validated(GroupWithoutID.class)NihCpsiDO nihCpsi){
		if(nihCpsiService.save(nihCpsi)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:nihCpsi:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class}) NihCpsiDO nihCpsi){
		nihCpsiService.update(nihCpsi);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:nihCpsi:remove")
	public R remove( String id){
		if(nihCpsiService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:nihCpsi:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		nihCpsiService.batchRemove(ids);
		return R.ok();
	}
	
}
