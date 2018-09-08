package com.prostate.pra.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 医生关注统计表
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:17
 */
public class FocusCountDoctorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//医生id
	private String doctorId;
	//关注标记
	private String focusFlag;
	//关注时间
	private Date createTime;

	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：医生id
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：医生id
	 */
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：关注标记
	 */
	public void setFocusFlag(String focusFlag) {
		this.focusFlag = focusFlag;
	}
	/**
	 * 获取：关注标记
	 */
	public String getFocusFlag() {
		return focusFlag;
	}
	/**
	 * 设置：关注时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：关注时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
