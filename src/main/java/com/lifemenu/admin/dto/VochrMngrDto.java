package com.lifemenu.admin.dto;

import com.lifemenu.dto.VoucherDto;

public class VochrMngrDto {

	private VoucherDto 	voucherDto; // 식사권 Dto
	private String 		vochrCode;
	private String 		mberCode;
	private int 		vochrPrice;
	private String 		mealPrearngeDt;
	private String 		vochrRegistDt;
	private int 		mealTime;
	private char 		delngAt;
	private String 		mberNcnm;
	private String 		mberRlnm;
	private String 		mberEmail;
	private String 		mberTel;
	private String 		bankCode;
	private String 		mberAcountNo;
	public VoucherDto getVoucherDto() {
		return voucherDto;
	}
	public void setVoucherDto(VoucherDto voucherDto) {
		this.voucherDto = voucherDto;
	}
	public String getVochrCode() {
		return vochrCode;
	}
	public void setVochrCode(String vochrCode) {
		this.vochrCode = vochrCode;
	}
	public String getMberCode() {
		return mberCode;
	}
	public void setMberCode(String mberCode) {
		this.mberCode = mberCode;
	}
	public int getVochrPrice() {
		return vochrPrice;
	}
	public void setVochrPrice(int vochrPrice) {
		this.vochrPrice = vochrPrice;
	}
	public String getMealPrearngeDt() {
		return mealPrearngeDt;
	}
	public void setMealPrearngeDt(String mealPrearngeDt) {
		this.mealPrearngeDt = mealPrearngeDt;
	}
	public String getVochrRegistDt() {
		return vochrRegistDt;
	}
	public void setVochrRegistDt(String vochrRegistDt) {
		this.vochrRegistDt = vochrRegistDt;
	}
	public int getMealTime() {
		return mealTime;
	}
	public void setMealTime(int mealTime) {
		this.mealTime = mealTime;
	}
	public char getDelngAt() {
		return delngAt;
	}
	public void setDelngAt(char delngAt) {
		this.delngAt = delngAt;
	}
	public String getMberNcnm() {
		return mberNcnm;
	}
	public void setMberNcnm(String mberNcnm) {
		this.mberNcnm = mberNcnm;
	}
	public String getMberRlnm() {
		return mberRlnm;
	}
	public void setMberRlnm(String mberRlnm) {
		this.mberRlnm = mberRlnm;
	}
	public String getMberEmail() {
		return mberEmail;
	}
	public void setMberEmail(String mberEmail) {
		this.mberEmail = mberEmail;
	}
	public String getMberTel() {
		return mberTel;
	}
	public void setMberTel(String mberTel) {
		this.mberTel = mberTel;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getMberAcountNo() {
		return mberAcountNo;
	}
	public void setMberAcountNo(String mberAcountNo) {
		this.mberAcountNo = mberAcountNo;
	}
	@Override
	public String toString() {
		return "VochrMngrDto [voucherDto=" + voucherDto + ", vochrCode=" + vochrCode + ", mberCode=" + mberCode
				+ ", vochrPrice=" + vochrPrice + ", mealPrearngeDt=" + mealPrearngeDt + ", vochrRegistDt="
				+ vochrRegistDt + ", mealTime=" + mealTime + ", delngAt=" + delngAt + ", mberNcnm=" + mberNcnm
				+ ", mberRlnm=" + mberRlnm + ", mberEmail=" + mberEmail + ", mberTel=" + mberTel + ", bankCode="
				+ bankCode + ", mberAcountNo=" + mberAcountNo + "]";
	}
	
}
