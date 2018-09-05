package com.prostate.order.entity;

public class OrderConstants {

    //订单状态
    public final static String TO_BE_PAYMENT = "TO_BE_PAYMENT"; //待支付
    public final static String TO_BE_ACCEPTED = "TO_BE_ACCEPTED"; //带接受
    public final static String TO_BE_ANSWERED = "TO_BE_ANSWERED"; //待回复
    public final static String TO_BE_ADD = "TO_BE_ADD"; //需要补充资料
    public final static String IS_REFUSE = "IS_REFUSE";//已拒绝
    public final static String IS_DONE = "IS_DONE";   //已完成
    public final static String BE_REJECTED = "BE_REJECTED";   //已完成
    ///订单类型
    public final static String PICTURE_INQUIRY_TYPE = "PICTURE_INQUIRY_TYPE";   //图文问诊订单
    public final static String PHONE_INQUIRY_TYPE = "PHONE_INQUIRY_TYPE";   //电话问诊订单
    public final static String VIDEO_INQUIRY_TYPE = "VIDEO_INQUIRY_TYPE";   //视频问诊订单
    public final static String PICTURE_TURN_TYPE = "PICTURE_TURN_TYPE";   //图文转诊订单
    public final static String PHONE_TURN_TYPE = "PHONE_TURN_TYPE";   //电话转诊订单
    public final static String VIDEO_TURN_TYPE = "VIDEO_TURN_TYPE";   //视频转诊订单


    //提现订单 状态
    public final static String IS_ACCEPTED = "IS_ACCEPTED"; //已受理
    public final static String NO_ACCEPTED = "NO_ACCEPTED"; //未受理
    //提现订单 类型
    public final static String CASH_TYPE = "CASH_TYPE";   //提现订单

}
