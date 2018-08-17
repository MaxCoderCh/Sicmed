package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.LablePrice;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LablePriceReadMapper extends BaseReadMapper<LablePrice>{

    List<LablePrice> selectByToken(LablePrice lablePrice);
}