package com.sicmed.register.bean;

import java.util.List;

/**
 * @Author: ykbian
 * @Date: 2018/9/20 9:51
 * @Todo:   统计数据相关
 */

public class CountBean {

    //人数
    String totalPeople;

    //金额
    String totalMoney;

    //已经就诊结束的人
    String isCrue;


    //总人数
    String patientes;


    //挂号bean
    List<RegisteredBean>  registeredBeans;


    public String getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(String totalPeople) {
        this.totalPeople = totalPeople;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<RegisteredBean> getRegisteredBeans() {
        return registeredBeans;
    }

    public void setRegisteredBeans(List<RegisteredBean> registeredBeans) {
        this.registeredBeans = registeredBeans;
    }

    public String getIsCrue() {
        return isCrue;
    }

    public void setIsCrue(String isCrue) {
        this.isCrue = isCrue;
    }

    public String getPatientes() {
        return patientes;
    }

    public void setPatientes(String patientes) {
        this.patientes = patientes;
    }
}
