package com.prostate.base.service.impl;

import com.prostate.base.domain.PriceDO;
import com.prostate.base.mapper.slaver.PriceReadMapper;
import com.prostate.base.mapper.master.PriceWriteMapper;
import com.prostate.base.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceReadMapper priceReadMapper;

	@Autowired
	private PriceWriteMapper priceWriteMapper;
	
	@Override
	public PriceDO get(String id){
		return priceReadMapper.get(id);
	}
	
	@Override
	public List<PriceDO> list(Map<String, Object> map){
		return priceReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return priceReadMapper.count(map);
	}
	
	@Override
	public int save(PriceDO priceDO){
		return priceWriteMapper.save(priceDO);
	}
	
	@Override
	public int update(PriceDO priceDO){
		return priceWriteMapper.update(priceDO);
	}
	
	@Override
	public int remove(String id){
		return priceWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return priceWriteMapper.batchRemove(ids);
	}
	
}
