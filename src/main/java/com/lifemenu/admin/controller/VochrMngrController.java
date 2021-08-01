package com.lifemenu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifemenu.admin.service.IVochrMngrService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-30
 * 	Version 	: 0.0.2
 * 
 * 	식사권, 예약, 결제, 환불 관리
 * 2021-06-10-0.0.1 	- 최초 작성
 * 2021-06-30-0.0.2 	- 연동 확인
 */

@Controller
public class VochrMngrController {

	@Autowired
	@Qualifier("vochrMngrService")
	private IVochrMngrService vochrMngrService;
	
	// 전체 식사권 목록 조회
	@RequestMapping(value="vochrMngrMulti.do")
	public String selectMultiVochrMngr(Model model, PageParamVo pageParamVo) {
		return vochrMngrService.selectMultiVochr(model, pageParamVo);
	}
	
	// 전체 식사권 목록 조건조회
	@RequestMapping(value="vochrMngrCondition.do")
	public String selectMultiVochrMngrByCondition(Model model, ConditionParamVo conditionParamVo) {
		return vochrMngrService.selectMultiVochrByCondition(model, conditionParamVo);
	}
	
	// 식사권 상세 조회
	@RequestMapping(value="vochrMngrDetail.do")
	public String selectMultiResveSetlt(Model model, @RequestParam("vochrCode") String vochrCode, PageParamVo pageParamVo) {
		return vochrMngrService.selectMultiResveSetle(model, vochrCode, pageParamVo);
	}
	
	// 식사권의 블라인드
	@RequestMapping(value="vochrMngrBlind.do")
	public String updateVochrMngr(Model model, @RequestParam("vochrCode") String vochrCode, PageParamVo pageParamVo) throws Exception {
		return vochrMngrService.blindVochr(model, vochrCode, pageParamVo);
	}
	
	// 식사권의 구매확정 팝업
	@RequestMapping(value="purchsDcsnAtPopup.do")
	public String purchsDcsnAtPopup(Model model, PageParamVo pageParamVo) throws Exception {
		return "";
	}
	
}
