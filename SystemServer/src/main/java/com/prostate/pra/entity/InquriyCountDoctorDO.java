package com.prostate.pra.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 医生之问诊统计
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:24
 */
public class InquriyCountDoctorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//医生id
	private String doctorId;
	//问诊状态码
	private String inquiryStatus;
	//问诊时间
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
	 * 设置：问诊状态码
	 */
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	/**
	 * 获取：问诊状态码
	 */
	public String getInquiryStatus() {
		return inquiryStatus;
	}
	/**
	 * 设置：问诊时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：问诊时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
