package com.lifemenu.admin.vo;

public class ConditionParamVo {

	private String conditionType;	// 조건 타입
	private String condition;		// 조건 검색어
	private int start;				// 가져올 인스턴스의 시작번호
	private int cnt;				// 가져올 인스턴스의 수
	
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	
	@Override
	public String toString() {
		return "ConditionParamVo [conditionType=" + conditionType + ", condition=" + condition + ", start=" + start
				+ ", cnt=" + cnt + "]";
	}
	
}
