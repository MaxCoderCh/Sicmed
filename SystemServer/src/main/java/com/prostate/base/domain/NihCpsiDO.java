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
public class NihCpsiDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	@NotNull(message = "id不能为空",groups = GroupID.class)
	@Pattern(regexp = "^[A-Za-z0-9]{32}",message = "id必须是32位字符串",groups = GroupID.class)
	private String id;
	//父id
	private String parentId;
	//标题
	@NotBlank(message =" 题目不能为空，且长度必须大于0" ,groups = {GroupWithoutID.class})
	private String nihCpsiTitle;
	//分数
	private String nihCpsiScore;
	//
	private String nihCpsiType;
	//权重
	private String nihCpsiWeight;
	//排序
	private Integer orderWeight;
	//创建人
	private String createUser;
	//创建时间
	private Date createTime;
	//删除人
	private String deleteUser;
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
	 * 设置：父id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父id
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：标题
	 */
	public void setNihCpsiTitle(String nihCpsiTitle) {
		this.nihCpsiTitle = nihCpsiTitle;
	}
	/**
	 * 获取：标题
	 */
	public String getNihCpsiTitle() {
		return nihCpsiTitle;
	}
	/**
	 * 设置：分数
	 */
	public void setNihCpsiScore(String nihCpsiScore) {
		this.nihCpsiScore = nihCpsiScore;
	}
	/**
	 * 获取：分数
	 */
	public String getNihCpsiScore() {
		return nihCpsiScore;
	}
	/**
	 * 设置：
	 */
	public void setNihCpsiType(String nihCpsiType) {
		this.nihCpsiType = nihCpsiType;
	}
	/**
	 * 获取：
	 */
	public String getNihCpsiType() {
		return nihCpsiType;
	}
	/**
	 * 设置：权重
	 */
	public void setNihCpsiWeight(String nihCpsiWeight) {
		this.nihCpsiWeight = nihCpsiWeight;
	}
	/**
	 * 获取：权重
	 */
	public String getNihCpsiWeight() {
		return nihCpsiWeight;
	}

	public Integer getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(Integer orderWeight) {
		this.orderWeight = orderWeight;
	}

	/**
	 * 设置：创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
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
	 * 设置：删除人
	 */
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	/**
	 * 获取：删除人
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
