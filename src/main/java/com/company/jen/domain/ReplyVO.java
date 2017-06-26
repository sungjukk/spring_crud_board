package com.company.jen.domain;

import java.util.Date;

public class ReplyVO {
	
	private int replyNo;
	private int bbsNo;
	private String replyContent;
	private int replyParent;
	private int replyDepth;
	private Date regDate;
	private String regUserId;
	private Date modifyDate;
	private String modifyUserId;
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getReplyParent() {
		return replyParent;
	}
	public void setReplyParent(int replyParent) {
		this.replyParent = replyParent;
	}
	public int getReplyDepth() {
		return replyDepth;
	}
	public void setReplyDepth(int replyDepth) {
		this.replyDepth = replyDepth;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
}
