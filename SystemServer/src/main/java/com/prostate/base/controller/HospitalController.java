package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.CityDO;
import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.base.service.CityService;
import com.prostate.base.service.HospitalService;
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

import com.prostate.base.domain.HospitalDO;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 医院表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-10 17:53:04
 */
 
@Controller
@RequestMapping("/base/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private CityService cityService;


	@GetMapping()
	@RequiresPermissions("base:hospital:hospital")
	String Hospital(){
	    return "base/hospital/hospital";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:hospital:hospital")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HospitalDO> hospitalList = hospitalService.list(query);
		//将每个医院对象的类型id都替换为其具体的name,城市id替换为具体的城市信息
//		for (HospitalDO hospitalDO:hospitalList ) {
//			//先通过类型id查找具体的类型名称，然后将名称设到类型id上，都是String类型，投机取巧
////			hospitalDO.setTypeId(hospitalTypeService.get(hospitalDO.getTypeId()).getHospitalTypeName());
//			//县级信息
//			CityDO county =cityService.get(hospitalDO.getCityId());
//			//市信息
//			CityDO city = cityService.getParent(hospitalDO.getCityId());
//			//省级信息
//			CityDO province = cityService.getParent(city.getId());
//			hospitalDO.setCityId(province.getCityName()+"省（自治区/直辖市）"+city.getCityName()+"市"+county.getCityName());
//		}
		int total = hospitalService.count(query);
		PageUtils pageUtils = new PageUtils(hospitalList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:hospital:add")
	String add(){
	    return "base/hospital/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:hospital:edit")
	String edit(@PathVariable("id") String id,Model model){
		HospitalDO hospital = hospitalService.get(id);
		model.addAttribute("hospital", hospital);
	    return "base/hospital/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:hospital:add")
	public R save( @Validated(GroupWithoutID.class)HospitalDO hospital){
		HospitalDO hospitalDOByName = hospitalService.getByName(hospital.getHospitalName());
		//HospitalDO hospitalDOByNumber = hospitalService.getByNumber(hospital.getHospitalNumber());
		//if (hospitalDOByName == null && hospitalDOByNumber == null){
		if (hospitalDOByName == null){
			hospital.setCreateUser(ShiroUtils.getUserId().toString());
			hospital.setCreateTime(new Date());
			hospital.setDelFlag("0");
			if(hospitalService.save(hospital)>0){
				return R.ok();
			}
		}
		return R.error(20001,"编号或者名称重复");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:hospital:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})HospitalDO hospital){
		//获取正在修改的对象信息
		HospitalDO hospitalById = hospitalService.get(hospital.getId());
		if( !hospital.getHospitalName().equalsIgnoreCase(hospitalById.getHospitalName())){
			if (hospitalService.getByName(hospital.getHospitalName()) != null){
				return  R.error(20001,"名称重复");
			}
		}
//		if( !hospital.getHospitalNumber().equalsIgnoreCase(hospitalById.getHospitalNumber())){
//			if (hospitalService.getByNumber(hospital.getHospitalNumber()) != null){
//				return  R.error(20001,"编号重复");
//			}
//		}
		hospitalService.update(hospital);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:hospital:remove")
	public R remove( String id){
		HospitalDO hospitalDO = hospitalService.get(id);
		hospitalDO.setDelFlag("1");
		hospitalDO.setDeleteUser(ShiroUtils.getUserId().toString());
		hospitalDO.setDeleteTime(new Date());
		//hospitalService.update(hospitalDO);
		if(hospitalService.update(hospitalDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:hospital:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id: ids) {
			HospitalDO hospitalDO = hospitalService.get(id);
			hospitalDO.setDelFlag("1");
			hospitalDO.setDeleteUser(ShiroUtils.getUserId().toString());
			hospitalDO.setDeleteTime(new Date());
			hospitalService.update(hospitalDO);
		}
		//hospitalService.batchRemove(ids);
		return R.ok();
	}
	
}
