package com.lifemenu.admin.service;

import org.springframework.ui.Model;

import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-27
 * 	Version 	: 0.0.1
 * 
 * 	자유게시판
 * 2021-06-18-0.0.2 	- 서비스 명세 및 확인
 * 2021-06-27-0.0.3 	- 연동 확인 및 삭제 기능 추가
 */

public interface IBbsMngrService {

	// 단건 조회(게시글 상세 조회)
	String selectOneBbs(Model model, String bbscttNo, PageParamVo pageParamVo);
	
	// 다건 조회
	String selectMultiBbs(Model model, PageParamVo pageParamVo);
	String selectMultiBbsByCondition(Model model, ConditionParamVo conditionParamVo);
	
	// 게시글 삭제
	String deleteBbs(Model model, String bbscttNo) throws Exception;
	
}
