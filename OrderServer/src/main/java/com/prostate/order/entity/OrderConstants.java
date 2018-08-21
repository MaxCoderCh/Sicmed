package com.prostate.order.entity;

public class OrderConstants {

    //订单状态
    public final static String TO_BE_PAYMENT = "TO_BE_PAYMENT"; //待支付
    public final static String TO_BE_ACCEPTED = "TO_BE_ACCEPTED"; //带接受
    public final static String TO_BE_ADD = "TO_BE_ADD"; //需要补充资料
    public final static String TO_BE_ANSWERED = "TO_BE_ANSWERED"; //待回复
    public final static String IS_DONE = "IS_DONE";   //已完成
    public final static String IS_REFUSE = "IS_REFUSE";//已拒绝
    ///订单类型
    public final static String PICTURE_INQUIRY_TYPE = "PICTURE_INQUIRY_TYPE";   //问诊订单
    public final static String PHONE_INQUIRY_TYPE = "PHONE_INQUIRY_TYPE";   //问诊订单
    public final static String VIDEO_INQUIRY_TYPE = "VIDEO_INQUIRY_TYPE";   //问诊订单
    public final static String TURN_TYPE = "TURN_TYPE";   //转诊订单


}
