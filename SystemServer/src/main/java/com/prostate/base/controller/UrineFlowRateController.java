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

import com.prostate.base.domain.UrineFlowRateDO;
import com.prostate.base.service.UrineFlowRateService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 尿流率量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
 
@Controller
@RequestMapping("/base/urineFlowRate")
public class UrineFlowRateController {
	private String prefix = "base/urineFlowRate";
	@Autowired
	private UrineFlowRateService urineFlowRateService;

	@GetMapping()
	@RequiresPermissions("base:urineFlowRate:urineFlowRate")
	String urineFlowRate() {
		return prefix + "/urineFlowRate";
	}

	@ApiOperation(value="获取题干列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:urineFlowRate:urineFlowRate")
	public List<UrineFlowRateDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<UrineFlowRateDO> urineFlowRateDOS = urineFlowRateService.list(query);
		return urineFlowRateDOS;
	}

	@GetMapping("/add/{id}")
	@RequiresPermissions("base:urineFlowRate:add")
	String add(@PathVariable("id") String parentId, Model model) {
		model.addAttribute("parentId", parentId);

		if (parentId.equals("0")) {
			model.addAttribute("parentName", "顶级节点");
		} else {
			model.addAttribute("parentName", urineFlowRateService.get(parentId).getScaleTitle());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:urineFlowRate:edit")
	String edit(@PathVariable("id") String id, Model model) {
		//查出本身id对应的实体
		UrineFlowRateDO urineFlowRate = urineFlowRateService.get(id);
		model.addAttribute("urineFlowRate", urineFlowRate);
		if(urineFlowRate.getParentId()==null||urineFlowRate.getParentId().equals("")) {
			model.addAttribute("parentName", "无");
		}else {
			//存在父级
			UrineFlowRateDO urineFlowRateDO = urineFlowRateService.get(urineFlowRate.getParentId());
			if(urineFlowRateDO==null||urineFlowRateDO.equals("")){
				model.addAttribute("parentName", "无");
			}else {
				model.addAttribute("parentName", urineFlowRateDO.getScaleTitle());
			}

		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:urineFlowRate:add")
	public R save(@Validated(GroupWithoutID.class)UrineFlowRateDO urineFlowRateDO) {
		if (urineFlowRateDO.getParentId().equals("0")){
			urineFlowRateDO.setScaleType("1");
		}
		urineFlowRateDO.setCreateUser(ShiroUtils.getUserId().toString());
		urineFlowRateDO.setCreateTime(new Date());
		urineFlowRateDO.setDelFlag("0");
		if (Constant.DEMO_ACCOUNT.equals("")) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (urineFlowRateService.save(urineFlowRateDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:urineFlowRate:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class}) UrineFlowRateDO urineFlowRateDO) {
		//System.out.println("--------------"+urineFlowRateDO);
		if (urineFlowRateService.update(urineFlowRateDO) > 0) {
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
		UrineFlowRateDO urineFlowRateDO=urineFlowRateService.get(id);
		urineFlowRateDO.setDeleteTime(new Date());
		urineFlowRateDO.setDeleteUser(ShiroUtils.getUserId().toString());
		urineFlowRateDO.setDelFlag("1");
		if (urineFlowRateService.update(urineFlowRateDO) > 0) {
			return R.ok();
		}
		return R.error();

	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:urineFlowRate:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {

		for (String id:ids){
			UrineFlowRateDO urineFlowRateDO=urineFlowRateService.get(id);
			urineFlowRateDO.setCreateTime(new Date());
			urineFlowRateDO.setCreateUser(ShiroUtils.getUserId().toString());
			if (urineFlowRateService.update(urineFlowRateDO) == 0) {
				return R.error();
			}
		}
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<UrineFlowRateDO> tree() {
		Tree<UrineFlowRateDO> tree = new Tree<UrineFlowRateDO>();
		tree = urineFlowRateService.getTree();
		return tree;
	}

	@GetMapping("/urineFlowRateTree")
	String treeView() {
		return  prefix + "/urineFlowRateTree";
	}
}
