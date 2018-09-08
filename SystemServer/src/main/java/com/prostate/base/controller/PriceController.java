package com.prostate.base.controller;

import com.prostate.base.domain.PriceDO;
import com.prostate.base.service.PriceService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@Author:      ykbian
 *@date_time:   2018/8/6 8:51
 *@Description:  价格标签控制
 *@param:
*/
 
@Controller
@RequestMapping("/base/price")
public class PriceController {




	@Autowired
	private PriceService priceService;
	
	@GetMapping()
	@RequiresPermissions("base:price:price")
	String Price(){
	    return "base/price/price";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:price:price")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PriceDO> priceDOS = priceService.list(query);
		int total = priceService.count(query);
		PageUtils pageUtils = new PageUtils(priceDOS, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:price:add")
	String add(){
	    return "base/price/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:price:edit")
	String edit(@PathVariable("id") String id,Model model){
		PriceDO price = priceService.get(id);
		model.addAttribute("price", price);
	    return "base/price/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:price:add")
	public R save( PriceDO price){
		price.setCreateTime(new Date());
//		price.setCreateUser(ShiroUtils.getUserId().toString());
		if(priceService.save(price)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:price:edit")
	public R update( PriceDO price){
		priceService.update(price);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:price:remove")
	public R remove( String id){
		if(priceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:price:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		priceService.batchRemove(ids);
		return R.ok();
	}
	
}
