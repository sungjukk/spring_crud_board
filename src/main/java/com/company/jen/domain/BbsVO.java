package com.company.jen.domain;

import java.util.Date;

public class BbsVO {
	
	private int bbsNo;
	private int userNo;
	private String userId;
	private String bbsTitle;
	private String bbsContent;
	private Date regDate;
	private Date modifyDate;
	
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Override
	public String toString() {
		return "BbsVO [bbsNo=" + bbsNo + ", userNo=" + userNo + ", userId="
				+ userId + ", bbsTitle=" + bbsTitle + ", bbsContent="
				+ bbsContent + ", regDate=" + regDate + ", modifyDate="
				+ modifyDate + "]";
	}
	
}
