package com.prostate.base.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;



/**
 * 科室表
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-05-21 16:05:42
 */
public class BranchDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空",groups = GroupID.class)
	@Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
	private String id;
	//上级科室ID
	private String parentBranchId;
	//科室名称
	@NotBlank(message =" 名称不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
	private String branchName;

	//科室等级
	private String branchGrade;
	//科室简介
	private String branchInform;
	//权重排序用
	private Integer branchWeight;
	//排序
	private String orderWeight;
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
	 * 设置：上级科室ID
	 */
	public void setParentBranchId(String parentBranchId) {
		this.parentBranchId = parentBranchId;
	}
	/**
	 * 获取：上级科室ID
	 */
	public String getParentBranchId() {
		return parentBranchId;
	}
	/**
	 * 设置：科室名称
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * 获取：科室名称
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * 设置：科室等级
	 */
	public void setBranchGrade(String branchGrade) {
		this.branchGrade = branchGrade;
	}
	/**
	 * 获取：科室等级
	 */
	public String getBranchGrade() {
		return branchGrade;
	}
	/**
	 * 设置：科室简介
	 */
	public void setBranchInform(String branchInform) {
		this.branchInform = branchInform;
	}
	/**
	 * 获取：科室简介
	 */
	public String getBranchInform() {
		return branchInform;
	}
	/**
	 * 设置：权重排序用
	 */
	public void setBranchWeight(Integer branchWeight) {
		this.branchWeight = branchWeight;
	}
	/**
	 * 获取：权重排序用
	 */
	public Integer getBranchWeight() {
		return branchWeight;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderWeight(String orderWeight) {
		this.orderWeight = orderWeight;
	}
	/**
	 * 获取：排序
	 */
	public String getOrderWeight() {
		return orderWeight;
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

	@Override
	public String toString() {
		return "BranchDO{" +
				"id='" + id + '\'' +
				", parentBranchId='" + parentBranchId + '\'' +
//				", branchName='" + branchName + '\'' +
//				", branchNumber='" + branchNumber + '\'' +
				", branchGrade='" + branchGrade + '\'' +
				", branchInform='" + branchInform + '\'' +
				", branchWeight=" + branchWeight +
				", orderWeight='" + orderWeight + '\'' +
				", createUser='" + createUser + '\'' +
				", createTime=" + createTime +
				", updateUser='" + updateUser + '\'' +
				", updateTime=" + updateTime +
				", deleteUser='" + deleteUser + '\'' +
				", deleteTime=" + deleteTime +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
