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
import io.swagger.annotations.ApiOperation;
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

import com.prostate.base.domain.UrineRoutineDO;
import com.prostate.base.service.UrineRoutineService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 尿常规量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-04 10:40:25
 */
 
@Controller
@RequestMapping("/base/urineRoutine")
public class UrineRoutineController {

	private String prefix = "base/urineRoutine";
	@Autowired
	private UrineRoutineService urineRoutineService;

	@GetMapping()
	@RequiresPermissions("base:urineRoutine:urineRoutine")
	String urineRoutine() {
		return prefix + "/urineRoutine";
	}

	@ApiOperation(value="获取题干列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:urineRoutine:urineRoutine")
	public List<UrineRoutineDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<UrineRoutineDO> urineRoutineList = urineRoutineService.list(query);
		return urineRoutineList;
	}

	@GetMapping("/add/{id}")
	@RequiresPermissions("base:urineRoutine:add")
	String add(@PathVariable("id") String parentId, Model model) {
		model.addAttribute("parentId", parentId);

		if (parentId.equals("0")) {
			model.addAttribute("parentName", "顶级节点");
		} else {
			model.addAttribute("parentName", urineRoutineService.get(parentId).getScaleTitle());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:urineRoutine:edit")
	String edit(@PathVariable("id") String id, Model model) {
		//查出本身id对应的实体
		UrineRoutineDO urineRoutine = urineRoutineService.get(id);
		model.addAttribute("urineRoutine", urineRoutine);
		if(urineRoutine.getParentId()==null||urineRoutine.getParentId().equals("")) {
			model.addAttribute("parentName", "无");
		}else {
			//存在父级
			UrineRoutineDO parurineRoutine = urineRoutineService.get(urineRoutine.getParentId());
			if(parurineRoutine==null||parurineRoutine.equals("")){
				model.addAttribute("parentName", "无");
			}else {
				model.addAttribute("parentName", parurineRoutine.getScaleTitle());
			}

		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:urineRoutine:add")
	public R save(@Validated(GroupWithoutID.class)UrineRoutineDO urineRoutine) {
		if (urineRoutine.getParentId().equals("0")){
			urineRoutine.setScaleType("1");
		}
		urineRoutine.setCreateUser(ShiroUtils.getUserId().toString());
		urineRoutine.setCreateTime(new Date());
		urineRoutine.setDelFlag("0");
		if (Constant.DEMO_ACCOUNT.equals("")) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (urineRoutineService.save(urineRoutine) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:urineRoutine:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class}) UrineRoutineDO urineRoutine) {
		if (urineRoutineService.update(urineRoutine) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("base:urineRoutine:remove")
	public R remove(String id) {
		UrineRoutineDO urineRoutineDO=urineRoutineService.get(id);
		urineRoutineDO.setDeleteTime(new Date());
		urineRoutineDO.setDeleteUser(ShiroUtils.getUserId().toString());
		urineRoutineDO.setDelFlag("1");
		if (urineRoutineService.update(urineRoutineDO) > 0) {
			return R.ok();
		}
		return R.error();

	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:urineRoutine:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {

		for (String id:ids){
			UrineRoutineDO urineRoutineDO=urineRoutineService.get(id);
			urineRoutineDO.setCreateTime(new Date());
			urineRoutineDO.setCreateUser(ShiroUtils.getUserId().toString());
			if (urineRoutineService.update(urineRoutineDO) == 0) {
				return R.error();
			}
		}
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<UrineRoutineDO> tree() {
		Tree<UrineRoutineDO> tree = new Tree<UrineRoutineDO>();
		tree = urineRoutineService.getTree();
		return tree;
	}

	@GetMapping("/urineRoutineTree")
	String treeView() {
		return  prefix + "/urineRoutineTree";
	}
	
}
