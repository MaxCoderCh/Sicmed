package com.prostate.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医生个人信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-13 18:04:59
 */
public class DoctorDetailDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String doctorId;
	//医生姓名
	private String doctorName;
	//年龄
	private String doctorAge;
	//性别
	private String doctorSex;
	//医生地址
	private String doctorAddress;
	//身份证号
	private String doctorCardNumber;
	//医院ID
	private String hospitalId;
	//科室ID
	private String branchId;
	//职称ID
	private String titleId;
	//头像地址
	private String headImg;
	//医生个人简介
	private String doctorResume;
	//医生擅长
	private String doctorStrong;
	//问诊方式标签
	private String lableInquiry;
	//
	private Date createTime;
	//
	private String createUser;
	//
	private Date updateTime;
	//
	private Date deleteTime;
	//
	private String updateUser;
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
	 * 设置：
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：
	 */
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：医生姓名
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * 获取：医生姓名
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * 设置：年龄
	 */
	public void setDoctorAge(String doctorAge) {
		this.doctorAge = doctorAge;
	}
	/**
	 * 获取：年龄
	 */
	public String getDoctorAge() {
		return doctorAge;
	}
	/**
	 * 设置：性别
	 */
	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}
	/**
	 * 获取：性别
	 */
	public String getDoctorSex() {
		return doctorSex;
	}
	/**
	 * 设置：医生地址
	 */
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	/**
	 * 获取：医生地址
	 */
	public String getDoctorAddress() {
		return doctorAddress;
	}
	/**
	 * 设置：身份证号
	 */
	public void setDoctorCardNumber(String doctorCardNumber) {
		this.doctorCardNumber = doctorCardNumber;
	}
	/**
	 * 获取：身份证号
	 */
	public String getDoctorCardNumber() {
		return doctorCardNumber;
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
	 * 设置：头像地址
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取：头像地址
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * 设置：医生个人简介
	 */
	public void setDoctorResume(String doctorResume) {
		this.doctorResume = doctorResume;
	}
	/**
	 * 获取：医生个人简介
	 */
	public String getDoctorResume() {
		return doctorResume;
	}
	/**
	 * 设置：医生擅长
	 */
	public void setDoctorStrong(String doctorStrong) {
		this.doctorStrong = doctorStrong;
	}
	/**
	 * 获取：医生擅长
	 */
	public String getDoctorStrong() {
		return doctorStrong;
	}
	/**
	 * 设置：问诊方式标签
	 */
	public void setLableInquiry(String lableInquiry) {
		this.lableInquiry = lableInquiry;
	}
	/**
	 * 获取：问诊方式标签
	 */
	public String getLableInquiry() {
		return lableInquiry;
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
}
