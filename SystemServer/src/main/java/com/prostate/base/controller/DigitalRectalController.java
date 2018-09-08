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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.DigitalRectalDO;
import com.prostate.base.service.DigitalRectalService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 前列腺指检量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
 
@Controller
@RequestMapping("/base/digitalRectal")
public class DigitalRectalController {
	@Autowired
	private DigitalRectalService digitalRectalService;
	
	@GetMapping()
	@RequiresPermissions("base:digitalRectal:digitalRectal")
	String DigitalRectal(){
	    return "base/digitalRectal/digitalRectal";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:digitalRectal:digitalRectal")
	public List<DigitalRectalDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<DigitalRectalDO> digitalRectalDOS = digitalRectalService.list(query);
		return digitalRectalDOS;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("base:digitalRectal:add")
	String add(@PathVariable("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equalsIgnoreCase("0")) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", digitalRectalService.get(pId).getScaleTitle());
		}
		return  "base/digitalRectal/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:digitalRectal:edit")
	String edit(@PathVariable("id") String id,Model model){
		DigitalRectalDO digitalRectalDO = digitalRectalService.get(id);
		model.addAttribute("digitalRectal", digitalRectalDO);
		if(digitalRectalDO.getParentId() == null) {
			model.addAttribute("parentScaleTitle", "无");
		}else {
			DigitalRectalDO digitalRectalDO1 = digitalRectalService.get(digitalRectalDO.getParentId());
			model.addAttribute("parentScaleTitle", digitalRectalDO1.getScaleTitle());
		}
		return  "base/digitalRectal/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:digitalRectal:add")
	public R save( @Validated(GroupWithoutID.class) DigitalRectalDO digitalRectal){
		if ("0".equalsIgnoreCase(digitalRectal.getParentId())){
			digitalRectal.setParentId(null);
		}
		digitalRectal.setCreateTime(new Date());
		digitalRectal.setCreateUser(ShiroUtils.getUserId().toString());
		digitalRectal.setDelFlag("0");
		if(digitalRectalService.save(digitalRectal)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:digitalRectal:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})DigitalRectalDO digitalRectal){
		if (digitalRectal.getParentId().equalsIgnoreCase("")){
			digitalRectal.setParentId(null);
		}
		digitalRectalService.update(digitalRectal);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:digitalRectal:remove")
	public R remove( String id){
		DigitalRectalDO digitalRectalDO = digitalRectalService.get(id);
		digitalRectalDO.setDelFlag("1");
		digitalRectalDO.setDeleteTime(new Date());
		digitalRectalDO.setDeleteUser(ShiroUtils.getUserId().toString());
		if(digitalRectalService.update(digitalRectalDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:digitalRectal:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id:ids) {
			DigitalRectalDO digitalRectalDO = digitalRectalService.get(id);
			digitalRectalDO.setDelFlag("1");
			digitalRectalDO.setDeleteTime(new Date());
			digitalRectalDO.setDeleteUser(ShiroUtils.getUserId().toString());
			digitalRectalService.update(digitalRectalDO);
		}
		return R.ok();
	}


	@GetMapping("/tree")
	@ResponseBody
	public Tree<DigitalRectalDO> tree() {
		Tree<DigitalRectalDO> tree = new Tree<DigitalRectalDO>();
		tree = digitalRectalService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/digitalRectal/digitalRectalTree";
	}
	
}
