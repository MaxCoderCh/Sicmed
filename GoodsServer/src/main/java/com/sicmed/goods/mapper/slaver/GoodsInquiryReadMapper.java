package com.sicmed.goods.mapper.slaver;

import com.sicmed.goods.entity.GoodsInquiry;

public interface GoodsInquiryReadMapper extends BaseReadMapper<GoodsInquiry> {

    int selectCount(GoodsInquiry goodsInquiry);

    String getPriceInquiryPictureByParams(GoodsInquiry goodsInquiry);
}