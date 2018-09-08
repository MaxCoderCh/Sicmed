package com.prostate.base.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 会诊类型标签
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
public class InquiryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//标签名称
	private String lableName;
	//
	private Date createTime;
	//
	private String createUser;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：标签名称
	 */
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	/**
	 * 获取：标签名称
	 */
	public String getLableName() {
		return lableName;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public String getCreateUser() {
		return createUser;
	}
}
