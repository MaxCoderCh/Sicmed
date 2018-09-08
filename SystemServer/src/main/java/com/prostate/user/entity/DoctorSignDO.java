package com.prostate.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医生认证信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-07 10:26:55
 */
public class DoctorSignDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//医生用户ID doctor_user
	private String doctorId;
	//医院ID
	private String hospitalId;
	//科室ID
	private String branchId;
	//职称ID
	private String titleId;
	//身份证正面照地址
	private String idCardFront;
	//身份证反面照片地址
	private String idCardContrary;
	//医师执业证 正面照片地址
	private String doctorCardFront;
	//医师执业证反面照片地址
	private String doctorCardContrary;
	//手持工牌照片地址
	private String workCard;
	//认证状态 0:认证失败  1:认证成功  2:认证中 
	private String approveStatus;
	//创建时间
	private Date createTime;
	//
	private String createUser;
	//
	private Date updateTime;
	//
	private String updateUser;
	//
	private Date deleteTime;
	//
	private String deleteUser;
	//
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
	 * 设置：医生用户ID doctor_user
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：医生用户ID doctor_user
	 */
	public String getDoctorId() {
		return doctorId;
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
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * 获取：科室ID
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * 设置：职称ID
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	/**
	 * 获取：职称ID
	 */
	public String getTitleId() {
		return titleId;
	}
	/**
	 * 设置：身份证正面照地址
	 */
	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
	}
	/**
	 * 获取：身份证正面照地址
	 */
	public String getIdCardFront() {
		return idCardFront;
	}
	/**
	 * 设置：身份证反面照片地址
	 */
	public void setIdCardContrary(String idCardContrary) {
		this.idCardContrary = idCardContrary;
	}
	/**
	 * 获取：身份证反面照片地址
	 */
	public String getIdCardContrary() {
		return idCardContrary;
	}
	/**
	 * 设置：医师执业证 正面照片地址
	 */
	public void setDoctorCardFront(String doctorCardFront) {
		this.doctorCardFront = doctorCardFront;
	}
	/**
	 * 获取：医师执业证 正面照片地址
	 */
	public String getDoctorCardFront() {
		return doctorCardFront;
	}
	/**
	 * 设置：医师执业证反面照片地址
	 */
	public void setDoctorCardContrary(String doctorCardContrary) {
		this.doctorCardContrary = doctorCardContrary;
	}
	/**
	 * 获取：医师执业证反面照片地址
	 */
	public String getDoctorCardContrary() {
		return doctorCardContrary;
	}
	/**
	 * 设置：手持工牌照片地址
	 */
	public void setWorkCard(String workCard) {
		this.workCard = workCard;
	}
	/**
	 * 获取：手持工牌照片地址
	 */
	public String getWorkCard() {
		return workCard;
	}
	/**
	 * 设置：认证状态 0:认证失败  1:认证成功  2:认证中 
	 */
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	/**
	 * 获取：认证状态 0:认证失败  1:认证成功  2:认证中 
	 */
	public String getApproveStatus() {
		return approveStatus;
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
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	/**
	 * 获取：
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	/**
	 * 设置：
	 */
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	/**
	 * 获取：
	 */
	public String getDeleteUser() {
		return deleteUser;
	}
	/**
	 * 设置：
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：
	 */
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public String toString() {
		return "DoctorSignDO{" +
				"id='" + id + '\'' +
				", doctorId='" + doctorId + '\'' +
				", hospitalId='" + hospitalId + '\'' +
				", branchId='" + branchId + '\'' +
				", titleId='" + titleId + '\'' +
				", idCardFront='" + idCardFront + '\'' +
				", idCardContrary='" + idCardContrary + '\'' +
				", doctorCardFront='" + doctorCardFront + '\'' +
				", doctorCardContrary='" + doctorCardContrary + '\'' +
				", workCard='" + workCard + '\'' +
				", approveStatus='" + approveStatus + '\'' +
				", createTime=" + createTime +
				", createUser='" + createUser + '\'' +
				", updateTime=" + updateTime +
				", updateUser='" + updateUser + '\'' +
				", deleteTime=" + deleteTime +
				", deleteUser='" + deleteUser + '\'' +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
