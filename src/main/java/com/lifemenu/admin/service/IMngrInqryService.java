package com.lifemenu.admin.service;

import org.springframework.ui.Model;

import com.lifemenu.admin.dto.MngrInqryDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-26
 * 	Version 	: 0.0.2
 * 
 * 	관리자 문의
 * 2021-06-26-0.0.2 	-페이지 수정에 맞춰 서비스 수정, 등록, 수정, 삭제 기능 추가
 */

public interface IMngrInqryService {

	// 단건 조회(상세 조회)
	String selectOneMngrInqry(Model model, String mngrInqryNo, PageParamVo pageParamVo);
	
	// 다건 조회(목록 조회)
	String selectMultiMngrInqry(Model model, PageParamVo pageParamVo);
	String selectMultiMngrInqryByCondition(Model model, ConditionParamVo conditionParamVo);
	
	// 관리자 문의 답변 등록(답변 등록)
	String insertMngrAnswer(Model model, MngrInqryDto mngrInqryDto) throws Exception;
	
	// 관리자 문의 답변 수정(답변 수정)
	String updateMngrAnswer(Model model, MngrInqryDto mngrInqryDto, PageParamVo pageParamVo) throws Exception;
	
	// 관리자 문의 답변 삭제
	String deleteMngrAnswer(Model model, String mngrInqryNo) throws Exception;
	
}
