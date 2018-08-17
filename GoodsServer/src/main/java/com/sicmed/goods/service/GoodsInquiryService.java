package com.sicmed.goods.service;

import com.sicmed.goods.entity.GoodsInquiry;

public interface GoodsInquiryService extends BaseService<GoodsInquiry> {
    int selectCount(GoodsInquiry goodsInquiry);

    int ban(GoodsInquiry goodsInquiry);
}
