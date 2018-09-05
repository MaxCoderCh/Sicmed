package com.prostate.wallet.controller;

import com.prostate.wallet.entity.CashAccount;
import com.prostate.wallet.entity.CashAccountConstant;
import com.prostate.wallet.service.CashAccountService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "account")
public class CashAccountController extends BaseController {

    @Autowired
    private CashAccountService cashAccountService;

    /**
     * 添加微信 账户
     *
     * @return
     */
    @PostMapping(value = "addWeChatAccount")
    public Map addWeChatAccount(String accountNumber) {

        CashAccount cashAccount = new CashAccount();
        cashAccount.setAccountType(CashAccountConstant.WECHAT_ACCOUNT);
        cashAccount.setAccountNumber(accountNumber);
        cashAccount.setAccountStatus(CashAccountConstant.USABLE_ACCOUNT);
        cashAccount.setCreateUser(getToken());
        cashAccount.setDoctorId(getToken());
        int i = cashAccountService.insertSelective(cashAccount);

        if (i > 0) {
            return insertSuccseeResponse(cashAccount.getId());
        }
        return insertFailedResponse();
    }

    /**
     * 查询 微信 账号
     */

    @GetMapping(value = "getWeChatAccount")
    public Map getWeChatAccount() {
        CashAccount cashAccount = new CashAccount();
        cashAccount.setCreateUser(getToken());
        cashAccount.setAccountType(CashAccountConstant.WECHAT_ACCOUNT);
        cashAccount.setAccountStatus(CashAccountConstant.USABLE_ACCOUNT);
        cashAccount = cashAccountService.getByParams(cashAccount);
        if (cashAccount == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(cashAccount);
    }


    /**
     * 删除微信 账号
     */

    @PostMapping(value = "deleteWeChatAccount")
    public Map deleteWeChatAccount(@NonNull String id) {

        int i = cashAccountService.deleteById(id);

        if (i > 0) {
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }
}

