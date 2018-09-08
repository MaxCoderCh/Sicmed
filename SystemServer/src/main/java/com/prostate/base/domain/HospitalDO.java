package com.prostate.base.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;



/**
 * 医院表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-10 17:53:04
 */
public class HospitalDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//医院表ID
	@NotNull(message = "id不能为空",groups = GroupID.class)
	@Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
	private String id;
	//医院名称
	@NotBlank(message =" 名称不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
	private String hospitalName;
//	//医院编号
//	@NotBlank(message =" 编号不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
//	private String hospitalNumber;
	//医院别名
	private String hospitalAlias;
	//医院信息简介
	private String hospitalInform;
	//医院等级
	private String hospitalGrade;
	//权重排序用
	private Integer hospitalWeight;
	//医院类型ID
	private String typeId;
	//医院所在城市ID
	private String cityId;
	//医院激活标记
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
	 * 设置：医院表ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：医院表ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：医院名称
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	/**
	 * 获取：医院名称
	 */
	public String getHospitalName() {
		return hospitalName;
	}
//	/**
//	 * 设置：医院编号
//	 */
//	public void setHospitalNumber(String hospitalNumber) {
//		this.hospitalNumber = hospitalNumber;
//	}
//	/**
//	 * 获取：医院编号
//	 */
//	public String getHospitalNumber() {
//		return hospitalNumber;
//	}
	/**
	 * 设置：医院别名
	 */
	public void setHospitalAlias(String hospitalAlias) {
		this.hospitalAlias = hospitalAlias;
	}
	/**
	 * 获取：医院别名
	 */
	public String getHospitalAlias() {
		return hospitalAlias;
	}
	/**
	 * 设置：医院信息简介
	 */
	public void setHospitalInform(String hospitalInform) {
		this.hospitalInform = hospitalInform;
	}
	/**
	 * 获取：医院信息简介
	 */
	public String getHospitalInform() {
		return hospitalInform;
	}
	/**
	 * 设置：医院等级
	 */
	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}
	/**
	 * 获取：医院等级
	 */
	public String getHospitalGrade() {
		return hospitalGrade;
	}
	/**
	 * 设置：权重排序用
	 */
	public void setHospitalWeight(Integer hospitalWeight) {
		this.hospitalWeight = hospitalWeight;
	}
	/**
	 * 获取：权重排序用
	 */
	public Integer getHospitalWeight() {
		return hospitalWeight;
	}
	/**
	 * 设置：医院类型ID
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：医院类型ID
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * 设置：医院所在城市ID
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：医院所在城市ID
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * 设置：医院激活标记
	 */
	public void setActivationFlag(String activationFlag) {
		this.activationFlag = activationFlag;
	}
	/**
	 * 获取：医院激活标记
	 */
	public String getActivationFlag() {
		return activationFlag;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderWeight(Integer orderWeight) {
		this.orderWeight = orderWeight;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderWeight() {
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
}
