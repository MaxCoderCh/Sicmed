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

import com.prostate.base.domain.UltrasonographyBDO;
import com.prostate.base.service.UltrasonographyBService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * B超量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
 
@Controller
@RequestMapping("/base/ultrasonographyB")
public class UltrasonographyBController {
	private String prefix = "base/ultrasonographyB";
	@Autowired
	private UltrasonographyBService ultrasonographyBService;


	@GetMapping()
	@RequiresPermissions("base:ultrasonographyB:ultrasonographyB")
	String ultrasonographyB() {
		return prefix + "/ultrasonographyB";
	}

	@ApiOperation(value="获取题干列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:ultrasonographyB:ultrasonographyB")
	public List<UltrasonographyBDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<UltrasonographyBDO> ultrasonographyBDOS = ultrasonographyBService.list(query);
		return ultrasonographyBDOS;
	}

	@GetMapping("/add/{id}")
	@RequiresPermissions("base:ultrasonographyB:add")
	String add(@PathVariable("id") String parentId, Model model) {
		model.addAttribute("parentId", parentId);

		if (parentId.equals("0")) {
			model.addAttribute("parentName", "顶级节点");
		} else {
			model.addAttribute("parentName", ultrasonographyBService.get(parentId).getScaleTitle());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:ultrasonographyB:edit")
	String edit(@PathVariable("id") String id, Model model) {
		//查出本身id对应的实体
		UltrasonographyBDO ultrasonographyBDO = ultrasonographyBService.get(id);
		model.addAttribute("ultrasonographyB", ultrasonographyBDO);
		if(ultrasonographyBDO.getParentId()==null||ultrasonographyBDO.getParentId().equals("")) {
			model.addAttribute("parentName", "无");
		}else {
			//存在父级
			UltrasonographyBDO parultrasonographyBDO = ultrasonographyBService.get(ultrasonographyBDO.getParentId());
			if(parultrasonographyBDO==null||parultrasonographyBDO.equals("")){
				model.addAttribute("parentName", "无");
			}else {
				model.addAttribute("parentName", parultrasonographyBDO.getScaleTitle());
			}

		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:ultrasonographyB:add")
	public R save(@Validated(GroupWithoutID.class)UltrasonographyBDO ultrasonographyBDO) {
		if (ultrasonographyBDO.getParentId().equals("0")){
			ultrasonographyBDO.setScaleType("1");
		}
		ultrasonographyBDO.setCreateUser(ShiroUtils.getUserId().toString());
		ultrasonographyBDO.setCreateTime(new Date());
		ultrasonographyBDO.setDelFlag("0");
		if (Constant.DEMO_ACCOUNT.equals("")) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (ultrasonographyBService.save(ultrasonographyBDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:ultrasonographyB:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})UltrasonographyBDO ultrasonographyBDO) {
		//System.out.println("--------------"+ultrasonographyBDO);
		if (ultrasonographyBService.update(ultrasonographyBDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("base:ultrasonographyB:remove")
	public R remove(String id) {
		UltrasonographyBDO ultrasonographyBDO=ultrasonographyBService.get(id);
		ultrasonographyBDO.setDeleteTime(new Date());
		ultrasonographyBDO.setDeleteUser(ShiroUtils.getUserId().toString());
		ultrasonographyBDO.setDelFlag("1");
		if (ultrasonographyBService.update(ultrasonographyBDO) > 0) {
			return R.ok();
		}
		return R.error();

	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:ultrasonographyB:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {

		for (String id:ids){
			UltrasonographyBDO ultrasonographyBDO=ultrasonographyBService.get(id);
			ultrasonographyBDO.setCreateTime(new Date());
			ultrasonographyBDO.setCreateUser(ShiroUtils.getUserId().toString());
			if (ultrasonographyBService.update(ultrasonographyBDO) == 0) {
				return R.error();
			}
		}
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<UltrasonographyBDO> tree() {
		Tree<UltrasonographyBDO> tree = new Tree<UltrasonographyBDO>();
		tree = ultrasonographyBService.getTree();
		return tree;
	}

	@GetMapping("/ultrasonographyBTree")
	String treeView() {
		return  prefix + "/ultrasonographyBTree";
	}
	
}
