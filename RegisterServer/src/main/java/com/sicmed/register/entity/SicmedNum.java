package com.sicmed.register.entity;

import java.util.Date;


/**
 *@Author:      ykbian
 *@date_time:   2018/9/19 10:26
 *@Description:  编号生成类
 *@param:
*/
public class SicmedNum {
    private String id;

    private String number;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}