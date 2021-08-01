package com.lifemenu.dto;

import java.sql.Date;

public class ResveDto {

	private String resveCode;
	private String mberCode;
	private String vochrCode;
	private int resvePc;
	private Date resveDt;
	
	public String getResveCode() {
		return resveCode;
	}
	public void setResveCode(String resveCode) {
		this.resveCode = resveCode;
	}
	public String getMberCode() {
		return mberCode;
	}
	public void setMberCode(String mberCode) {
		this.mberCode = mberCode;
	}
	public String getVochrCode() {
		return vochrCode;
	}
	public void setVochrCode(String vochrCode) {
		this.vochrCode = vochrCode;
	}
	public int getResvePc() {
		return resvePc;
	}
	public void setResvePc(int resvePc) {
		this.resvePc = resvePc;
	}
	public Date getResveDt() {
		return resveDt;
	}
	public void setResveDt(Date resveDt) {
		this.resveDt = resveDt;
	}
	
	
}
