package com.lifemenu.admin.service;

import org.springframework.ui.Model;

import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-29
 * 	Version 	: 0.0.3
 * 
 * 	식사권, 예약, 결제, 환불 관리
 * 2021-06-16-0.0.2 	- DAO 테스트
 * 2021-06-29-0.0.3 	- 연동 확인, 변경사항 반영
 */

public interface IVochrMngrService {

	// 다건 조회
	String selectMultiVochr(Model model, PageParamVo pageParamVo);
	String selectMultiVochrByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiResveSetle(Model model, String vochrCode, PageParamVo pageParamVo);
	
	// 삭제(약관 위반된 식사권 또는 기간 만료된 식사권 등)
	String blindVochr(Model model, String vochrCode, PageParamVo pageParamVo) throws Exception;
	
}
