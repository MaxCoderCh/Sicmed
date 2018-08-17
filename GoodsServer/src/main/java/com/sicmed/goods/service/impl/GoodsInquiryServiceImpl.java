package com.sicmed.goods.service.impl;

import com.sicmed.goods.entity.GoodsInquiry;
import com.sicmed.goods.mapper.master.GoodsInquiryWriteMapper;
import com.sicmed.goods.mapper.slaver.GoodsInquiryReadMapper;
import com.sicmed.goods.service.GoodsInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInquiryServiceImpl implements GoodsInquiryService {
    @Autowired
    private GoodsInquiryWriteMapper goodsInquiryWriteMapper;

    @Autowired
    private GoodsInquiryReadMapper goodsInquiryReadMapper;

    @Override
    public int insertSelective(GoodsInquiry goodsInquiry) {
        return goodsInquiryWriteMapper.insertSelective(goodsInquiry);
    }

    @Override
    public int updateSelective(GoodsInquiry goodsInquiry) {
        return 0;
    }

    @Override
    public GoodsInquiry selectById(String id) {
        return null;
    }

    @Override
    public List<GoodsInquiry> selectByParams(GoodsInquiry goodsInquiry) {
        return goodsInquiryReadMapper.selectByParams(goodsInquiry);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int selectCount(GoodsInquiry goodsInquiry) {
        return goodsInquiryReadMapper.selectCount(goodsInquiry);
    }

    @Override
    public int ban(GoodsInquiry goodsInquiry) {
        return goodsInquiryWriteMapper.ban(goodsInquiry);
    }
}
