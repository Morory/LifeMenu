package com.lifemenu.admin.vo;

import java.sql.Timestamp;

public class HistoryParamVo {

	private Timestamp startRcordDt; 	// 검색 범위: 시작 일시
	private Timestamp endRcordDt; 		// 검색 범위: 종료 일시
	private int start;					// 가져올 인스턴스의 시작번호
	private int cnt; 					// 가져올 인스턴스의 수
	
	public Timestamp getStartRcordDt() {
		return startRcordDt;
	}
	public void setStartRcordDt(Timestamp startRcordDt) {
		this.startRcordDt = startRcordDt;
	}
	public Timestamp getEndRcordDt() {
		return endRcordDt;
	}
	public void setEndRcordDt(Timestamp endRcordDt) {
		this.endRcordDt = endRcordDt;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
