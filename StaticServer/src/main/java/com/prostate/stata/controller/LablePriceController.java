package com.prostate.stata.controller;

import com.prostate.stata.entity.LablePrice;
import com.prostate.stata.service.LablePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "lablePrice")
public class LablePriceController extends BaseController {

    @Autowired
    private LablePriceService lablePriceService;



    /**
     * 医生添加 自定义标签
     * @param lableName
     * @return
     */
    @PostMapping(value = "add")
    public Map add(String lableName) {

        LablePrice lablePrice = new LablePrice();
        lablePrice.setCreateUser(getToken());
        lablePrice.setLableName(lableName);
        int result = lablePriceService.insertSelective(lablePrice);

        if (result > 0) {
            return insertSuccseeResponse(lablePrice);
        }
        return insertFailedResponse();

    }


    /**
     * 医生查询 标签
     * @return
     */
    @GetMapping(value = "getAll")
    public Map get() {

        LablePrice lablePrice = new LablePrice();
        lablePrice.setCreateUser(getToken());
        List<LablePrice> lablePriceList = lablePriceService.selectByToken(lablePrice);

        if (lablePriceList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(lablePriceList);

    }


}
