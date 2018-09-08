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

import com.prostate.base.domain.SpecificAntigenDO;
import com.prostate.base.service.SpecificAntigenService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 特异性抗原量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
 
@Controller
@RequestMapping("/base/specificAntigen")
public class SpecificAntigenController {
	@Autowired
	private SpecificAntigenService specificAntigenService;
	
	@GetMapping()
	@RequiresPermissions("base:specificAntigen:specificAntigen")
	String SpecificAntigen(){
	    return "base/specificAntigen/specificAntigen";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:specificAntigen:specificAntigen")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<SpecificAntigenDO> specificAntigenList = specificAntigenService.list(query);
//		int total = specificAntigenService.count(query);
//		PageUtils pageUtils = new PageUtils(specificAntigenList, total);
//		return pageUtils;
//	}
	public List<SpecificAntigenDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<SpecificAntigenDO> bloodRoutineDOS = specificAntigenService.list(query);
		return bloodRoutineDOS;
	}
//	@GetMapping("/add")
//	@RequiresPermissions("base:specificAntigen:add")
//	String add(){
//	    return "base/specificAntigen/add";
//	}


	@GetMapping("/add/{pId}")
	@RequiresPermissions("base:specificAntigen:add")
	String add(@PathVariable("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equalsIgnoreCase("0")) {
			model.addAttribute("pName", "总");
		} else {
			model.addAttribute("pName", specificAntigenService.get(pId).getScaleTitle());
		}
		return  "base/specificAntigen/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:specificAntigen:edit")
	String edit(@PathVariable("id") String id,Model model){
		//根据id获取对象信息
		SpecificAntigenDO specificAntigenDO = specificAntigenService.get(id);
		//把对象信息存入model
		model.addAttribute("specificAntigen", specificAntigenDO);
		//如果这个对象没有父id,那么他就是一级菜单
		if(specificAntigenDO.getParentId() == null) {
			model.addAttribute("parentScaleTitle", "无");
		}else {
			//如果有父id，则把他的上级菜单的信息存入model
			SpecificAntigenDO specificAntigenDO1 =
					specificAntigenService.get(specificAntigenDO.getParentId());
			model.addAttribute("parentScaleTitle", specificAntigenDO1.getScaleTitle());
		}
		return  "base/specificAntigen/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:specificAntigen:add")
	public R save( @Validated(GroupWithoutID.class)SpecificAntigenDO specificAntigen){
		if ("0".equalsIgnoreCase(specificAntigen.getParentId())){
			specificAntigen.setParentId(null);
		}
		specificAntigen.setCreateTime(new Date());
		specificAntigen.setCreateUser(ShiroUtils.getUserId().toString());
		specificAntigen.setDelFlag("0");
		if(specificAntigenService.save(specificAntigen)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:specificAntigen:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) SpecificAntigenDO specificAntigen){
		if (specificAntigen.getParentId().equalsIgnoreCase("")){
			specificAntigen.setParentId(null);
		}
		specificAntigenService.update(specificAntigen);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:specificAntigen:remove")
	public R remove( String id){
		//删除这条信息
		SpecificAntigenDO specificAntigen = specificAntigenService.get(id);
		specificAntigen.setDeleteTime(new Date());
		specificAntigen.setDeleteUser(ShiroUtils.getUserId().toString());
		specificAntigen.setDelFlag("1");
		specificAntigenService.update(specificAntigen);
		//删除这条信息的子类信息
		List<SpecificAntigenDO> subSpecificAntigens = specificAntigenService.getByParentId(id);
		if (subSpecificAntigens != null){
			for (SpecificAntigenDO subSpecificAntigen:subSpecificAntigens) {
				subSpecificAntigen.setDeleteTime(new Date());
				subSpecificAntigen.setDeleteUser(ShiroUtils.getUserId().toString());
				subSpecificAntigen.setDelFlag("1");
				specificAntigenService.update(subSpecificAntigen);
			}
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:specificAntigen:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		specificAntigenService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/tree")
	@ResponseBody
	public Tree<SpecificAntigenDO> tree() {
		Tree<SpecificAntigenDO> tree = new Tree<SpecificAntigenDO>();
		tree = specificAntigenService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/specificAntigen/specificAntigenTree";
	}
}
