package com.prostate.wallet.controller;

import com.prostate.wallet.entity.DealRecord;
import com.prostate.wallet.service.DealRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "dealRecord")
public class DealRecordController extends BaseController {

    @Autowired
    private DealRecordService dealRecordService;

    /**
     * APP 分页查询 收支明细列表
     * @return
     */
    @GetMapping(value = "getList")
    public Map getList() {

        DealRecord dealRecord = new DealRecord();
        dealRecord.setWalletId(getToken());

        List<DealRecord> dealRecordList = dealRecordService.selectByParams(dealRecord);

        if (dealRecordList == null || dealRecordList.isEmpty()) {
            return queryEmptyResponse();
        }
        for (DealRecord record : dealRecordList) {
            StringBuffer stringBuffer = new StringBuffer(record.getDealAmount());
            stringBuffer.insert(stringBuffer.length() - 2, ".");
            record.setDealAmount(stringBuffer.toString());
        }
        return querySuccessResponse(dealRecordList);

    }
}
