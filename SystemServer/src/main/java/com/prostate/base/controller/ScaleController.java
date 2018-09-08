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

import com.prostate.base.domain.ScaleDO;
import com.prostate.base.service.ScaleService;
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
@RequestMapping("/base/scale")
public class ScaleController {
	@Autowired
	private ScaleService scaleService;
	
	@GetMapping()
	@RequiresPermissions("base:scale:scale")
	String Scale(){
	    return "base/scale/scale";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:scale:scale")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScaleDO> scaleList = scaleService.list(query);
		int total = scaleService.count(query);
		PageUtils pageUtils = new PageUtils(scaleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:scale:add")
	String add(){
	    return "base/scale/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:scale:edit")
	String edit(@PathVariable("id") String id,Model model){
		ScaleDO scale = scaleService.get(id);
		model.addAttribute("scale", scale);
	    return "base/scale/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:scale:add")
	public R save(@Validated(GroupWithoutID.class) ScaleDO scale){
		if(scaleService.save(scale)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:scale:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class})  ScaleDO scale){
		scaleService.update(scale);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:scale:remove")
	public R remove( String id){
		if(scaleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:scale:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		scaleService.batchRemove(ids);
		return R.ok();
	}
	
}
