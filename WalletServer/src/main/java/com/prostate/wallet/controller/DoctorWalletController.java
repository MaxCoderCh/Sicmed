package com.prostate.wallet.controller;

import com.prostate.wallet.cache.redis.RedisSerive;
import com.prostate.wallet.entity.DoctorWallet;
import com.prostate.wallet.entity.GroupID;
import com.prostate.wallet.entity.GroupWithoutID;
import com.prostate.wallet.service.DoctorWalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/17 13:38
 * @Todo:
 */
@Slf4j
@RestController
@RequestMapping("/doctorWallet")
public class DoctorWalletController  extends BaseController {

    @Autowired
    private DoctorWalletService doctorWalletService;

    @Autowired
    private  DoctorWallet doctorWallet;

    @Autowired
    private RedisSerive redisSerive;

    @PostMapping("/save")
    public Map save(String token){
        doctorWallet.setWalletBalance("0");
        doctorWallet.setCreateUser(token);
        doctorWallet.setDoctorId(token);
        doctorWallet.setId(token);
        int r = doctorWalletService.insertSelective(doctorWallet);
        if (r>0){

            return insertSuccseeResponse();
        }else {
            return insertFailedResponse();
        }
    }


    @GetMapping("/selectByToken")
    public Map selectByDoctorId( String token){
        //查询钱包信息====因为token和id的值相等，所以把token当做id当做查询条件
        DoctorWallet doctorWallet = doctorWalletService.selectByDoctorId(token);
        if (doctorWallet==null){
            doctorWallet = doctorWalletService.create(token);
        }
        //查询结果为空
        if (doctorWallet == null ){
            return  queryEmptyResponse();
        }
        //查询结果不为空
        else {
            return querySuccessResponse(doctorWallet);
        }
    }

    @PostMapping(value = "/updateBalance")
    public Map updateBalance(@RequestBody @Validated({GroupID.class,GroupWithoutID.class})DoctorWallet doctorWallet,String token){
        //取出交易金额
        String money = doctorWallet.getWalletBalance();
        //医生的token 和钱包id值是一样的，因此这里使用token获取钱包信息。如果医生未登录，就不能修改钱包信息了
        DoctorWallet doctorWalletFromDatabase = doctorWalletService.selectById(token);
        //如果查询对象为空，直接返回。
        if (doctorWalletFromDatabase == null){
            return queryEmptyResponse();
        }
        //获取钱包余额
        int sum = Integer.parseInt(doctorWalletFromDatabase.getWalletBalance());
        //修改钱包金额
        int newBalance = sum + Integer.parseInt(money);
        //余额不足
        if (newBalance < 0){
            return updateFailedResponse();
        }
        doctorWallet.setWalletBalance(newBalance+"");
        //将钱包信息存回数据库
        if (doctorWalletService.updateSelective(doctorWallet) > 0){
            //如果是提现，且操作成功的话发送短信
            if (Integer.parseInt(money)<0){
                //从当前缓存中获取到医生的信息
               String phoneNumber = redisSerive.get(token);
                //提现发起时间
                String paramTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
                //取提现金额的绝对值========发短信的接口要求金额必须大于0
                String amount = money.substring(1,money.length());
                //发送短信
//               Map map = thirdService.sendBalanceForCash(phoneNumber,paramTime,amount);
//                log.info("提现短信通知结果 : " + map);
            }
            log.info("修改金额成功");
            return updateSuccseeResponse();
        }else {
            return updateFailedResponse();
        }
    }

    /**
     * 查询余额
     * @return
     */
    @GetMapping("getBalance")
    public Map getBalance(){
        //查询钱包信息====因为token和id的值相等，所以把token当做id当做查询条件
        DoctorWallet doctorWallet = doctorWalletService.selectByDoctorId(getToken());
        if (doctorWallet==null){
            doctorWallet = doctorWalletService.create(getToken());
        }
        //查询结果为空
        if (doctorWallet == null ){
            return  queryEmptyResponse();
        } else {
            String balance = f2y(doctorWallet.getWalletBalance());
            return querySuccessResponse(balance);
        }
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
