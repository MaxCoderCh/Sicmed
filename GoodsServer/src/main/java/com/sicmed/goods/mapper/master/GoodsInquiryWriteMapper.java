package com.sicmed.goods.mapper.master;

import com.sicmed.goods.entity.GoodsInquiry;

public interface GoodsInquiryWriteMapper extends BaseWriteMapper<GoodsInquiry> {

    int ban(GoodsInquiry goodsInquiry);
}