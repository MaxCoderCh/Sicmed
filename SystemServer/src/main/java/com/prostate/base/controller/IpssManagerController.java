package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.annotation.Log;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.ShiroUtils;
import com.prostate.system.domain.RoleDO;
import com.prostate.system.domain.UserDO;
import com.prostate.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.IpssDO;
import com.prostate.base.service.IpssManagerService;
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
@RequestMapping("/base/ipssManager")
public class IpssManagerController {

	private String prefix="base/ipssManager";
	@Autowired
	private IpssManagerService ipssManagerService;
	@Autowired
	private RoleService roleService;

	@RequiresPermissions("base:ipssManager:ipssManager")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/ipssManager";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		//System.out.println(params);
		if(params.get("parentId")==null||params.get("parentId").equals("")){
			query.put("ipssType","1");
		}
		//System.out.println(query);
		List<IpssDO> ipssDOList = ipssManagerService.list(query);

		int total = ipssManagerService.count(query);
		PageUtils pageUtil = new PageUtils(ipssDOList, total);
		return pageUtil;
	}

	@RequiresPermissions("base:ipssManager:add")
	@Log("添加区县")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@RequiresPermissions("base:ipssManager:edit")
	@Log("编辑区县")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") String id) {
		IpssDO ipssDO = ipssManagerService.get(id);
		model.addAttribute("ipssDO", ipssDO);
		IpssDO ipss = ipssManagerService.get(ipssDO.getParentId());
		model.addAttribute("ipss",ipss);
		return prefix+"/edit";
	}

	@RequiresPermissions("base:ipssManager:add")
	@Log("保存区县")
	@PostMapping("/save")
	@ResponseBody
	R save(@Validated(GroupWithoutID.class)IpssDO ipssDO) {

		IpssDO ipss=ipssManagerService.get(ipssDO.getParentId());
		if (ipss!=null||ipss.equals("")){
			if(ipss.getIpssType().equalsIgnoreCase("a")||
					ipss.getIpssType().equalsIgnoreCase("b")||
					ipss.getIpssType().equalsIgnoreCase("c")){
				ipssDO.setIpssType("1");
			}else if (ipss.getIpssType().equalsIgnoreCase("1")){
				ipssDO.setIpssType("0");
			}
		}

		// if (scaleManagerService.listByName(scaleDO.getCityName()).size()==0){
		ipssDO.setCreateUser(ShiroUtils.getUserId().toString());
		// scaleDO.setUpdateUser(ShiroUtils.getUserId().toString());
		if(ipssManagerService.save(ipssDO)>0){
			return R.ok();
		}

		//  }
		return R.error(20001,"该区县名已经存在");


	}

	@RequiresPermissions("base:ipssManager:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update( @Validated({GroupID.class,GroupWithoutID.class})IpssDO ipssDO) {


		IpssDO ipss=ipssManagerService.get(ipssDO.getParentId());
		if (ipss!=null||ipss.equals("")){
			if(ipss.getIpssType().equalsIgnoreCase("a")||
					ipss.getIpssType().equalsIgnoreCase("b")||
					ipss.getIpssType().equalsIgnoreCase("c")){
				ipssDO.setIpssType("1");
			}else if (ipss.getIpssType().equalsIgnoreCase("1")){
				ipssDO.setIpssType("0");
			}
		}
		// if (!scale.getCityName().equalsIgnoreCase(scaleDO.getCityName())){
		// if (scaleManagerService.listByName(scaleDO.getCityName()).size()>0){
		//     return R.error(20001,"该区县名已经存在");
		//  }
		//  }

		//scaleDO.setUpdateUser(ShiroUtils.getUserId().toString());
		// scaleDO.setUpdateTime(new Date());
		if (ipssManagerService.update(ipssDO)>0){
			return R.ok();
		}else {
			return R.error();
		}

	}


	@RequiresPermissions("base:ipssManager:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		return R.error();
	}


	@RequiresPermissions("base:ipssManager:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(String id) {

		IpssDO ipssDO =ipssManagerService.get(id);
		ipssDO.setDeleteTime(new Date());
		ipssDO.setDeleteUser(ShiroUtils.getUserId().toString());
		ipssDO.setDelFlag("1");
		if (ipssManagerService.update(ipssDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("base:ipssManager:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") String[] ids) {
		for (String id:ids){
			IpssDO ipssDO=ipssManagerService.get(id);
			ipssDO.setId(id);
			ipssDO.setDeleteUser(ShiroUtils.getUserId().toString());
			ipssDO.setDeleteTime(new Date());
			ipssDO.setDelFlag("1");
			ipssManagerService.update(ipssDO);
		}

		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<IpssDO> tree() {
		Tree<IpssDO> tree = new Tree<IpssDO>();
		tree = ipssManagerService.getTree();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(tree);
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/ipssManagerTree";
	}
	
}
