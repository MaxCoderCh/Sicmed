package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.InquiryReadMapper;
import com.prostate.base.mapper.master.InquiryWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.InquiryDO;
import com.prostate.base.service.InquiryService;



@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	private InquiryWriteMapper inquiryWriteMapper;

	@Autowired
	private InquiryReadMapper inquiryReadMapper;
	
	@Override
	public InquiryDO get(String id){
		return inquiryReadMapper.get(id);
	}
	
	@Override
	public List<InquiryDO> list(Map<String, Object> map){
		return inquiryReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return inquiryReadMapper.count(map);
	}
	
	@Override
	public int save(InquiryDO inquiry){
		return inquiryWriteMapper.save(inquiry);
	}
	
	@Override
	public int update(InquiryDO inquiry){
		return inquiryWriteMapper.update(inquiry);
	}
	
	@Override
	public int remove(String id){
		return inquiryWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return inquiryWriteMapper.batchRemove(ids);
	}
	
}
