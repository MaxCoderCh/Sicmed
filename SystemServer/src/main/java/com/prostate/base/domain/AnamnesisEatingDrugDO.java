package com.prostate.base.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public class AnamnesisEatingDrugDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	@NotNull(message = "id不能为空",groups = GroupID.class)
	@Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
	private String id;
	//药物名
	@NotBlank(message =" 名称不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
	private String eatingDrugName;
	//中文缩写
	private String spellName;
	//药品编号
	@NotBlank(message =" 编号不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
	private String eatingDrugNumber;
	//排序
	private Integer orderWeight;
	//创建者名字
	private String createName;
	//创建时间
	private Date createTime;
	//更新人的名字
	private String updateName;
	//更新时间
	private Date updateTime;
	//删除人的名字
	private String deleteName;
	//删除时间
	private Date deleteTime;
	//删除标记
	private String delFlag;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：药物名
	 */
	public void setEatingDrugName(String eatingDrugName) {
		this.eatingDrugName = eatingDrugName;
	}
	/**
	 * 获取：药物名
	 */
	public String getEatingDrugName() {
		return eatingDrugName;
	}
	/**
	 * 设置：中文缩写
	 */
	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}
	/**
	 * 获取：中文缩写
	 */
	public String getSpellName() {
		return spellName;
	}
	/**
	 * 设置：药品编号
	 */
	public void setEatingDrugNumber(String eatingDrugNumber) {
		this.eatingDrugNumber = eatingDrugNumber;
	}
	/**
	 * 获取：药品编号
	 */
	public String getEatingDrugNumber() {
		return eatingDrugNumber;
	}

	public Integer getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(Integer orderWeight) {
		this.orderWeight = orderWeight;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * 获取：创建者名字
	 */
	public String getCreateName() {
		return createName;
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
	 * 设置：更新人的名字
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * 获取：更新人的名字
	 */
	public String getUpdateName() {
		return updateName;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：删除人的名字
	 */
	public void setDeleteName(String deleteName) {
		this.deleteName = deleteName;
	}
	/**
	 * 获取：删除人的名字
	 */
	public String getDeleteName() {
		return deleteName;
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
