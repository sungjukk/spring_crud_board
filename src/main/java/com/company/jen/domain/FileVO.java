package com.company.jen.domain;

import java.util.Date;

public class FileVO {

	private int fileNo;
	private int bbsNo;
	private String oriFileName;
	private String realFileName;
	private String filePath;
	private long fileSize;
	private Date regDate;
	private String regUserId;
	private Date modifyDate;
	private String modifyUserId;
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
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
