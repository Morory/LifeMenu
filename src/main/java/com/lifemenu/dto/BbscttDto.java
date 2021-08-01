package com.lifemenu.dto;

import java.util.Date;

public class BbscttDto {
    private String bbscttNo;	// 게시글 번호
    private String upBbscttNo;	// 상위 게시글 번호
    private String mberCode;	// 회원 코드
    private String bbscttSj;	// 게시글 제목
    private String bbscttCn;	// 게시글 내용
    private Date bbscttDt;		// 작성 일시
    
	public String getBbscttNo() {
		return bbscttNo;
	}
	public void setBbscttNo(String bbscttNo) {
		this.bbscttNo = bbscttNo;
	}
	public String getUpBbscttNo() {
		return upBbscttNo;
	}
	public void setUpBbscttNo(String upBbscttNo) {
		this.upBbscttNo = upBbscttNo;
	}
	public String getMberCode() {
		return mberCode;
	}
	public void setMberCode(String mberCode) {
		this.mberCode = mberCode;
	}
	public String getBbscttSj() {
		return bbscttSj;
	}
	public void setBbscttSj(String bbscttSj) {
		this.bbscttSj = bbscttSj;
	}
	public String getBbscttCn() {
		return bbscttCn;
	}
	public void setBbscttCn(String bbscttCn) {
		this.bbscttCn = bbscttCn;
	}
	public Date getBbscttDt() {
		return bbscttDt;
	}
	public void setBbscttDt(Date bbscttDt) {
		this.bbscttDt = bbscttDt;
	}

}
