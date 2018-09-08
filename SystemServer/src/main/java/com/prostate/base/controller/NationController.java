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

import com.prostate.base.domain.NationDO;
import com.prostate.base.service.NationService;
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
@RequestMapping("/base/nation")
public class NationController {
	@Autowired
	private NationService nationService;
	
	@GetMapping()
	@RequiresPermissions("base:nation:nation")
	String Nation(){
	    return "base/nation/nation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:nation:nation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NationDO> nationList = nationService.list(query);
		int total = nationService.count(query);
		PageUtils pageUtils = new PageUtils(nationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:nation:add")
	String add(){
	    return "base/nation/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:nation:edit")
	String edit(@PathVariable("id") String id,Model model){
		NationDO nation = nationService.get(id);
		model.addAttribute("nation", nation);
	    return "base/nation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:nation:add")
	public R save(@Validated(GroupWithoutID.class) NationDO nation){
		NationDO nationDOByName = nationService.getByName(nation.getNationName());
		NationDO nationDOByNumber = nationService.getByNumber(nation.getNationNumber());
		if (nationDOByName == null && nationDOByNumber == null){
			nation.setCreateTime(new Date());
			nation.setCreateUser(ShiroUtils.getUserId().toString());
			nation.setDelFlag("0");
			if(nationService.save(nation) > 0){
				return  R.ok();
			}
		}return R.error(20001,"名称或者编号重复");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:nation:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class})  NationDO nation){
		//根据id查询数据库中原来的数据
		NationDO nationDOByID = nationService.get(nation.getId());
		//如果新的名称和原来的名称不一致
		if (!nationDOByID.getNationName().equalsIgnoreCase(nation.getNationName())){
			//判断新的名称和数据库中其他的名称是否重复
			if (nationService.getByName(nation.getNationName()) != null){
				return R.error(20001,"名称重复");
			}
		}
		if (!nationDOByID.getNationNumber().equalsIgnoreCase(nation.getNationNumber())){
			if (nationService.getByNumber(nation.getNationNumber()) != null){
				return  R.error(20001,"编号重复");
			}
		}
		nation.setUpdateTime(new Date());
		nation.setUpdateUser(ShiroUtils.getUserId().toString());
		nationService.update(nation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:nation:remove")
	public R remove( String id){
		NationDO nationDO = nationService.get(id);
		nationDO.setDeleteTime(new Date());
		nationDO.setDeleteUser(ShiroUtils.getUserId().toString());
		nationDO.setDelFlag("1");
		if (nationService.update(nationDO) > 0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:nation:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		//这种遍历方法增加了与数据库的交互次数，拖慢了访问速度，从数据库中直接取出id的方法效率更高（参照批量删除），
		// 但是对象中必须有createUser和createTime
		for (String id:ids ) {
			NationDO nationDO = nationService.get(id);
			nationDO.setDeleteTime(new Date());
			nationDO.setDeleteUser(ShiroUtils.getUserId().toString());
			nationDO.setDelFlag("1");
			nationService.update(nationDO);
		}
		return R.ok();
	}


//	/**
//	* 删除，
//	*/
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("base:nation:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//			nationService.batchUpdate(ids);
//			//nationService.update(nationDO);
//
//		return R.ok();
//	}
}
