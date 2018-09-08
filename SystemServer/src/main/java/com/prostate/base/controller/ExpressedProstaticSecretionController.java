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
import net.sf.ehcache.transaction.xa.EhcacheXAException;
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

import com.prostate.base.domain.ExpressedProstaticSecretionDO;
import com.prostate.base.service.ExpressedProstaticSecretionService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 前列腺按摩液量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
 
@Controller
@RequestMapping("/base/expressedProstaticSecretion")
public class ExpressedProstaticSecretionController {
	@Autowired
	private ExpressedProstaticSecretionService expressedProstaticSecretionService;
	
	@GetMapping()
	@RequiresPermissions("base:expressedProstaticSecretion:expressedProstaticSecretion")
	String ExpressedProstaticSecretion(){
	    return "base/expressedProstaticSecretion/expressedProstaticSecretion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:expressedProstaticSecretion:expressedProstaticSecretion")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<ExpressedProstaticSecretionDO> expressedProstaticSecretionList = expressedProstaticSecretionService.list(query);
//		int total = expressedProstaticSecretionService.count(query);
//		PageUtils pageUtils = new PageUtils(expressedProstaticSecretionList, total);
//		return pageUtils;
//	}
	public List<ExpressedProstaticSecretionDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<ExpressedProstaticSecretionDO> bloodRoutineDOS = expressedProstaticSecretionService.list(query);
		return bloodRoutineDOS;
	}
//
//	@GetMapping("/add")
//	@RequiresPermissions("base:expressedProstaticSecretion:add")
//	String add(){
//	    return "base/expressedProstaticSecretion/add";
//	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("base:expressedProstaticSecretion:add")
	String add(@PathVariable("pId") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equalsIgnoreCase("0")) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", expressedProstaticSecretionService.get(pId).getScaleTitle());
		}
		return  "base/expressedProstaticSecretion/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:expressedProstaticSecretion:edit")
	String edit(@PathVariable("id") String id,Model model){
	//根据id获取对象信息
		ExpressedProstaticSecretionDO expressedProstaticSecretionDO = expressedProstaticSecretionService.get(id);
		//把对象信息存入model
		model.addAttribute("expressedProstaticSecretion", expressedProstaticSecretionDO);
		//如果这个对象没有父id,那么他就是一级菜单
		if(expressedProstaticSecretionDO.getParentId() == null) {
			model.addAttribute("parentScaleTitle", "无");
		}else {
			//如果有父id，则把他的上级菜单的信息存入model
			ExpressedProstaticSecretionDO bloodRoutineDO =
					expressedProstaticSecretionService.get(expressedProstaticSecretionDO.getParentId());
			model.addAttribute("parentScaleTitle", bloodRoutineDO.getScaleTitle());
		}
	    return "base/expressedProstaticSecretion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:expressedProstaticSecretion:add")
	public R save(@Validated(GroupWithoutID.class) ExpressedProstaticSecretionDO expressedProstaticSecretion){
		if ("0".equalsIgnoreCase(expressedProstaticSecretion.getParentId())){
			expressedProstaticSecretion.setParentId(null);
		}
		expressedProstaticSecretion.setCreateTime(new Date());
		expressedProstaticSecretion.setCreateUser(ShiroUtils.getUserId().toString());
		expressedProstaticSecretion.setDelFlag("0");
		if(expressedProstaticSecretionService.save(expressedProstaticSecretion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:expressedProstaticSecretion:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) ExpressedProstaticSecretionDO expressedProstaticSecretion){
		if (expressedProstaticSecretion.getParentId().equalsIgnoreCase("")){
			expressedProstaticSecretion.setParentId(null);
		}
		expressedProstaticSecretionService.update(expressedProstaticSecretion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:expressedProstaticSecretion:remove")
	public R remove( String id){
		ExpressedProstaticSecretionDO expressedProstaticSecretion = expressedProstaticSecretionService.get(id);
		expressedProstaticSecretion.setDeleteTime(new Date());
		expressedProstaticSecretion.setDeleteUser(ShiroUtils.getUserId().toString());
		expressedProstaticSecretion.setDelFlag("1");
		if(expressedProstaticSecretionService.update(expressedProstaticSecretion)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:expressedProstaticSecretion:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		expressedProstaticSecretionService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/tree")
	@ResponseBody
	public Tree<ExpressedProstaticSecretionDO> tree() {
		Tree<ExpressedProstaticSecretionDO> tree = new Tree<ExpressedProstaticSecretionDO>();
		tree = expressedProstaticSecretionService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/expressedProstaticSecretion/expressedProstaticSecretionTree";
	}
}
