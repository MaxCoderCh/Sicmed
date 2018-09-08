package com.prostate.pra.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 医生之点击统计
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:11
 */
public class ClickCountDoctorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//医生id
	private String doctorId;
	//点击状态码
	private String clickStatus;
	//点击时间
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
	 * 设置：点击状态码
	 */
	public void setClickStatus(String clickStatus) {
		this.clickStatus = clickStatus;
	}
	/**
	 * 获取：点击状态码
	 */
	public String getClickStatus() {
		return clickStatus;
	}
	/**
	 * 设置：点击时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：点击时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
