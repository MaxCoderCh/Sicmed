package com.prostate.stata.entity;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户意见反馈表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
public class FeedbackDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String id;
	//反馈内容
	private String feedbackText;
	//处理信息
	private String replyInfo;
	//状态信息，0未处理，1已处理
	private String replyState;
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
	 * 设置：反馈内容
	 */
	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	/**
	 * 获取：反馈内容
	 */
	public String getFeedbackText() {
		return feedbackText;
	}
	/**
	 * 设置：处理信息
	 */
	public void setReplyInfo(String replyInfo) {
		this.replyInfo = replyInfo;
	}
	/**
	 * 获取：处理信息
	 */
	public String getReplyInfo() {
		return replyInfo;
	}
	/**
	 * 设置：状态信息，0未处理，1已处理
	 */
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	/**
	 * 获取：状态信息，0未处理，1已处理
	 */
	public String getReplyState() {
		return replyState;
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
