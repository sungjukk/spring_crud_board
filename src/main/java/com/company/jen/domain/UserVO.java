package com.company.jen.domain;

public class UserVO {
	private int userNo;
	private String userId;
	private String userPw;
	
	public UserVO() {}
	
	public UserVO(int userNo, String userPw) {
		super();
		this.userNo = userNo;
		this.userPw = userPw;
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
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
}
