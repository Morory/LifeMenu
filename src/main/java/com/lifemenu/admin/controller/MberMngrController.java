package com.lifemenu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifemenu.admin.service.IMberMngrService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.SellerDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-26
 * 	Version 	: 0.0.2
 * 
 * 	회원, 판매자 관리
 * 2021-06-10-0.0.1 	- 최초 작성
 * 2021-06-26-0.0.2 	- 연동 확인
 */

@Controller
public class MberMngrController {

	@Autowired
	@Qualifier("mberMngrService")
	private IMberMngrService mberMngrService;
	
	// 첫 화면 전체 회원 목록 조회
	@RequestMapping(value="mberMngrMulti.do")
	public String selectMultiMberMngr(Model model, PageParamVo pageParamVo, @ModelAttribute("MSG") String msg) {
		System.out.println(msg);
		return mberMngrService.selectMultiMber(model, pageParamVo);
	}
	
	// 전체 회원 목록 조건조회
	@RequestMapping(value="mberMngrCondition.do")
	public String selectMultiMberMngrByCondition(Model model, ConditionParamVo conditionParamVo) {
		return mberMngrService.selectMultiMberByCondition(model, conditionParamVo);
	}
	
	// 회원 상세 조회
	@RequestMapping(value="mberMngrDetail.do")
	public String selectOneMberMngrDetail(Model model, String mberCode, PageParamVo pageParamVo) {
		return mberMngrService.selectOneMberMngr(model, mberCode, pageParamVo);
	}
	
	// 회원 및 판매자 수정
	@RequestMapping(value="updateMberMngr.do")
	public String updateMberMngrDetail(Model model, MberDto mberDto, SellerDto sellerDto, PageParamVo pageParamVo) throws Exception {
		
		System.out.println(mberDto.toString());
		System.out.println(sellerDto.toString()+", "+sellerDto.getMberCode());
		return mberMngrService.updateMberMngr(model, mberDto, sellerDto, pageParamVo);
		
	}
	
	// 회원 및 판매자 삭제(탈퇴 처리)
	@RequestMapping(value="deleteMberMngr.do")
	public String deleteMberMngrDetail(Model model, @RequestParam("mberCode") String mberCode, PageParamVo pageParamVo) throws Exception {

		return mberMngrService.deleteMberMngr(model, mberCode);
		
	}
	
}
