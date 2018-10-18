package com.sicmed.register.base;


/**
 *@Author:      ykbian
 *@date_time:   2018/9/4 17:35
 *@Description:  常量配置
 *@param:
*/
public class Constant {
    /**
     *   del_flag  删除标记
     */
    public static String DEL_FLAG_USABLE = "0";
    public static String DEL_FLAG_DISUSABLE = "1";

    /**
     *   可以挂号的科室
     */
    public static String BRANCH_REGISTERED_YES = "1";
    public static String BRANCH_REGISTERED_NO = "0";

   /**
    *  挂号状态 0 正常 1改号   2退号   3正常结束
    */
    public static String PATIENT_REGISTERED_CURING = "0";
    public static String PATIENT_REGISTERED_EXCHANGE = "1";
    public static String PATIENT_REGISTERED_RETURN = "2";
    public static String PATIENT_REGISTERED_OVER = "3";




    /**
     *   收费信息  0未缴费 1缴费  2 退费
     */
    public static String PRICE_NOT_PAY = "0";
    public static String PRICE_EXCHARGE = "1";
    public static String PRICE_RETURN = "2";

    /**
     *  患者编号生成的初始id
     */
    public static String PATIENT_NUM_ID = "86096bea2cbb4ebfa5d985de261bbf28";

}
