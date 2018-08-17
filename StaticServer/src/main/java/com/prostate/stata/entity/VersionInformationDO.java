package com.prostate.stata.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 版本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
public class VersionInformationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//版本号
	private String versionNumber;
	//版本信息

	private String versionDetails;
	//创建者
	private String createUser;
	//创建时间
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
	 * 设置：版本号
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersionNumber() {
		return versionNumber;
	}
	/**
	 * 设置：版本信息
	 */
	public void setVersionDetails(String versionDetails) {
		this.versionDetails = versionDetails;
	}
	/**
	 * 获取：版本信息
	 */
	public String getVersionDetails() {
		return versionDetails;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
