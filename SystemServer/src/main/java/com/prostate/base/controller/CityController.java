package com.prostate.base.controller;

import java.util.*;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.base.service.CityService;
import com.prostate.base.vo.CityVO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.CityDO;
import com.prostate.common.utils.R;

/**
 * 城市表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */

@Controller
@RequestMapping("/base/city")
public class CityController {
	@Autowired
	private CityService cityService;


	@GetMapping()
	@RequiresPermissions("base:city:city")
	String City(){
	    return "base/city/city";
	}


@GetMapping("/list")
@ResponseBody
public List<CityDO> list() {
	Map<String, Object> query = new HashMap<>(16);
	List<CityDO> sysBranchList = cityService.list(query);
	return sysBranchList;
}



	@ResponseBody
	@GetMapping("/ChildList/{parentCityId}")
	public Map<String, Object> ChildList(@PathVariable("parentCityId")String parentCityId) {
		Map<String, Object> query = new HashMap<>(16);
		query.put("parentCityId",parentCityId);
		Map<String, Object> result = new HashMap<>(16);
		List<CityDO> cityList = cityService.getChild(query);

		if (cityList.isEmpty()){
			result.put("code",20001);
			result.put("msg","暂无数据");
		}else {
			result.put("code",20000);
			result.put("data",cityList);
			result.put("msg","查询成功");
		}
		return result;
	}



	@GetMapping("/add/{id}")
	@RequiresPermissions("base:city:add")
	String add(@PathVariable("id") String pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId.equalsIgnoreCase("0")) {
			model.addAttribute("pName", "无");
		} else {
			model.addAttribute("pName", cityService.get(pId).getCityName());
			int type = Integer.parseInt(cityService.get(pId).getCityType())+1;
			model.addAttribute("type", type+"");
		}
		return  "base/city/add";
	}
	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:city:edit")
	String edit(@PathVariable("id") String id,Model model){
		CityDO city = cityService.get(id);
		model.addAttribute("city", city);
		if(city.getParentCityId() == null) {
			model.addAttribute("parentCityName", "无");
		}else {
			CityDO cityDO  = cityService.get(city.getParentCityId());
			model.addAttribute("parentCityName", cityDO.getCityName());
		}
		return  "base/city/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:city:add")
	public R save(@Validated(GroupWithoutID.class) CityDO city){
		city.setCreateTime(new Date());
		city.setCreateUser(ShiroUtils.getUserId().toString());
		city.setDelFlag("0");
		if(cityService.save(city)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:city:edit")
	public R update(@Validated({GroupID.class,GroupWithoutID.class}) CityDO city){
		cityService.update(city);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:city:remove")
	public R remove( String id){
		if(cityService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:city:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		cityService.batchRemove(ids);
		return R.ok();
	}


	@ResponseBody
	@GetMapping("/getById")
	//@RequiresPermissions("base:city:city")
	public CityVO getById(String id){
		CityVO cityVO = new CityVO();
		//分别获取三级信息
		CityDO cityDOCounty  = cityService.get(id);
		CityDO cityDOCity  = cityService.get(cityDOCounty.getParentCityId());
		CityDO cityDOProvince  = cityService.get(cityDOCity.getParentCityId());
		cityVO.setProvince(cityDOProvince);
		cityVO.setCity(cityDOCity);
		cityVO.setCounty(cityDOCounty);
		return cityVO;
	}



	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/6 17:30
	 *@Description:  根据不确定的cityId查询父级信息及祖父级
	 *@param:
	*/
	@ResponseBody
	@GetMapping("/getById02/{id}")
	public Map getById02(@PathVariable("id") String id){
		Map<String, Object> result = new HashMap<>(16);
		List<CityDO> cityDOS = new ArrayList();
		CityDO city;
		String myId = id;
		do {
			city = cityService.get(myId);
			myId = city.getParentCityId();
			//每次查询到的上级城市信息都存入list 的首位
			cityDOS.add(0,city);
			//“中国”的父ID是空字符窜=======不具有复用性，
		}while (!city.getParentCityId().equalsIgnoreCase(""));
		if (cityDOS == null ){
			result.put("code",20001);
			result.put("msg","暂无数据");
		}else {
			result.put("code",20000);
			result.put("data",cityDOS);
			result.put("msg","查询成功");
		}
		return result;
	}


	@ResponseBody
	@GetMapping("/get/{id}")
	//@RequiresPermissions("base:city:city")
	public Map get(@PathVariable("id")String id){
		Map<String, Object> result = new HashMap<>(16);
		CityDO city = cityService.get(id);

		if (city == null ){
			result.put("code",20001);
			result.put("msg","暂无数据");
		}else {
			result.put("code",20000);
			result.put("data",city);
			result.put("msg","查询成功");
		}
		return result;
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<CityDO> tree() {
		Tree<CityDO> tree = new Tree<CityDO>();
		tree = cityService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return   "base/city/cityTree";
	}
}
