package com.prostate.base.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;



/**
 * 医院科室表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public class HospitalDeptDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空",groups = GroupID.class)
	@Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
	private String id;
	//医院ID
	private String hospitalId;
	//科室ID
	private String deptId;
	//科室信息简介
	private String deptInform;
	//权重排序用
	private Integer deptWeight;
	//科室激活标记(0/1 已/未)
	private String activationFlag;
	//排序
	private Integer orderWeight;
	//创建人ID(后台管理员)
	private String createUser;
	//创建时间
	private Date createTime;
	//修改人ID(后台管理员)
	private String updateUser;
	//修改时间
	private Date updateTime;
	//删除人ID(后台管理员)
	private String deleteUser;
	//删除时间
	private Date deleteTime;
	//删除标记
	private String delFlag;

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
	 * 设置：医院ID
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * 获取：医院ID
	 */
	public String getHospitalId() {
		return hospitalId;
	}
	/**
	 * 设置：科室ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：科室ID
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置：科室信息简介
	 */
	public void setDeptInform(String deptInform) {
		this.deptInform = deptInform;
	}
	/**
	 * 获取：科室信息简介
	 */
	public String getDeptInform() {
		return deptInform;
	}
	/**
	 * 设置：权重排序用
	 */
	public void setDeptWeight(Integer deptWeight) {
		this.deptWeight = deptWeight;
	}
	/**
	 * 获取：权重排序用
	 */
	public Integer getDeptWeight() {
		return deptWeight;
	}
	/**
	 * 设置：科室激活标记(0/1 已/未)
	 */
	public void setActivationFlag(String activationFlag) {
		this.activationFlag = activationFlag;
	}
	/**
	 * 获取：科室激活标记(0/1 已/未)
	 */
	public String getActivationFlag() {
		return activationFlag;
	}

	public Integer getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(Integer orderWeight) {
		this.orderWeight = orderWeight;
	}

	/**
	 * 设置：创建人ID(后台管理员)
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人ID(后台管理员)
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
	/**
	 * 设置：修改人ID(后台管理员)
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：修改人ID(后台管理员)
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：删除人ID(后台管理员)
	 */
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	/**
	 * 获取：删除人ID(后台管理员)
	 */
	public String getDeleteUser() {
		return deleteUser;
	}
	/**
	 * 设置：删除时间
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	/**
	 * 设置：删除标记
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标记
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
