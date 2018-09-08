package com.prostate.user.vo;

/**
 * @Author: ykbian
 * @Date: 2018/8/10 16:28
 * @Todo:
 */

public class CountDoctorVO {

    /**
     * 点击访问
     */
    private  int clickCount;

    /**
     * 关注数据
     */
    private int focusCount;

    /**
     * 问诊数据
     */
    private int inquriyCount;

    /**
     * 医生id
     */
    private String doctorId;

    private String doctorName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getInquriyCount() {
        return inquriyCount;
    }

    public void setInquriyCount(int inquriyCount) {
        this.inquriyCount = inquriyCount;
    }

    @Override
    public String toString() {
        return "CountDoctorVO{" +
                "clickCount=" + clickCount +
                ", focusCount=" + focusCount +
                ", inquriyCount=" + inquriyCount +
                '}';
    }
}
