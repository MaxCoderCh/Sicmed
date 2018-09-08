package com.sicmed.goods.controller;

import com.sicmed.goods.common.GoodsConstants;
import com.sicmed.goods.entity.GoodsInquiry;
import com.sicmed.goods.service.GoodsInquiryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "goods/inquiry")
public class GoodsInquiryController extends BaseController {

    @Autowired
    private GoodsInquiryService goodsInquiryService;

    /**
     * 添加
     *
     * @param goodsInquiry
     * @return
     */
    @PostMapping(value = "add")
    public Map add(GoodsInquiry goodsInquiry) {
        //数据校验

        //重复添加校验
        goodsInquiry.setDoctor(getToken());
        goodsInquiry.setGoodsStatus(GoodsConstants.USABLE);
        int goodsCount = goodsInquiryService.selectCount(goodsInquiry);
        if(goodsCount>0){
            return insertSuccseeResponse();
        }
        int b = goodsInquiryService.ban(goodsInquiry);
        int i = goodsInquiryService.insertSelective(goodsInquiry);
        if (i <= 0) {
            return insertFailedResponse();
        }
        return insertSuccseeResponse();
    }

    /**
     * 查询列表
     *
     * @param doctorId
     * @return
     */
    @GetMapping(value = "queryListByDoctor")
    public Map queryListByDoctor(String doctorId) {
        if (StringUtils.isBlank(doctorId)) {
            return emptyParamResponse();
        }
        GoodsInquiry goodsInquiry = new GoodsInquiry();
        goodsInquiry.setDoctor(doctorId);
        goodsInquiry.setGoodsStatus(GoodsConstants.USABLE);
        List<GoodsInquiry> goodsInquiryList = goodsInquiryService.selectByParams(goodsInquiry);
        if (goodsInquiryList == null || goodsInquiryList.isEmpty()) {
            return queryEmptyResponse();
        }
        for (GoodsInquiry inquiry : goodsInquiryList) {

                inquiry.setGoodsPrice(f2y(inquiry.getGoodsPrice()));
        }
        return querySuccessResponse(goodsInquiryList);
    }



    /**
     * 查询列表
     */
    @GetMapping(value = "queryListByToken")
    public Map queryListByToken() {

        GoodsInquiry goodsInquiry = new GoodsInquiry();
        goodsInquiry.setDoctor(getToken());
        goodsInquiry.setGoodsStatus(GoodsConstants.USABLE);
        List<GoodsInquiry> goodsInquiryList = goodsInquiryService.selectByParams(goodsInquiry);
        if (goodsInquiryList == null || goodsInquiryList.isEmpty()) {
            return queryEmptyResponse();
        }
        for (GoodsInquiry inquiry : goodsInquiryList) {
            inquiry.setGoodsPrice(f2y(inquiry.getGoodsPrice()));
        }
        return querySuccessResponse(goodsInquiryList);
    }

    /**
     * 医生问诊商品 为空判断
     */
    @GetMapping(value = "queryCount")
    public Map queryCount() {

        GoodsInquiry goodsInquiry = new GoodsInquiry();
        goodsInquiry.setDoctor(getToken());
        goodsInquiry.setGoodsStatus(GoodsConstants.USABLE);
        int goodsCount = goodsInquiryService.selectCount(goodsInquiry);

        return querySuccessResponse(goodsCount);
    }


    /**
     * 查询已选图文问诊价格
     */
    @GetMapping(value = "getPriceInquiryPictureByParams")
    public Map getPriceInquiryPictureByParams() {

        GoodsInquiry goodsInquiry = new GoodsInquiry();
        goodsInquiry.setDoctor(getToken());
        goodsInquiry.setGoodsType(GoodsConstants.GOODS_INQUIRY_PICTURE);
        goodsInquiry.setGoodsStatus(GoodsConstants.USABLE);
        String goodsPrice = goodsInquiryService.getPriceInquiryPictureByParams(goodsInquiry);

        if (StringUtils.isBlank(goodsPrice)) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(goodsPrice);
    }

    public static String f2y(String balance) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isBlank(balance)) {
            stringBuffer.append("0.00");
        } else if (balance.length() > 2) {
            stringBuffer.append(balance);
            stringBuffer.insert(stringBuffer.length() - 2, ".");
        } else if (balance.length() == 2) {
            stringBuffer.append("0.");
            stringBuffer.append(balance);
        } else if (balance.length() == 1) {
            stringBuffer.append("0.0");
            stringBuffer.append(balance);
        }
        return stringBuffer.toString();
    }
}
