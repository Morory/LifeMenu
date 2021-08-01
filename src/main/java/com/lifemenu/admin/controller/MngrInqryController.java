package com.lifemenu.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifemenu.admin.dto.MngrInqryDto;
import com.lifemenu.admin.service.IMngrInqryService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.MberDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-30
 * 	Version 	: 0.0.3
 * 
 * 	관리자 문의
 * 2021-06-10-0.0.1 	- 최초 작성
 * 2021-06-26-0.0.2 	- 서비스 연동 확인 및 등록, 수정, 삭제 추가
 * 2021-06-30-0.0.3 	- 세션에서 관리자 정보 가져오는 것으로 수정
 */

@Controller
public class MngrInqryController {

	@Autowired
	@Qualifier("mngrInqryService")
	private IMngrInqryService mngrInqryService;
	
	// 첫 화면 전체 관리자 문의 목록 조회
	@RequestMapping(value="mngrInqryMulti.do")
	public String selectMultiMngrInqry(Model model, PageParamVo pageParamVo) {
		return mngrInqryService.selectMultiMngrInqry(model, pageParamVo);
	}
	
	// 전체 관리자 문의 목록 조건조회
	@RequestMapping(value="mngrInqryCondition.do")
	public String selectMultiMngrInqryByCondition(Model model, ConditionParamVo conditionParamVo) {
		return mngrInqryService.selectMultiMngrInqryByCondition(model, conditionParamVo);
	}
	
	// 관리자 문의 상세 조회
	@RequestMapping(value="mngrInqryDetail.do")
	public String selectOneMngrInqryDetail(Model model, @RequestParam("mngrInqryNo") String mngrInqryNo, PageParamVo pageParamVo) {
		return mngrInqryService.selectOneMngrInqry(model, mngrInqryNo, pageParamVo);
	}
	
	// 관리자 문의 등록 양식
	@RequestMapping(value="insertMngrInqryForm.do")
	public String insertMngrInqryForm(Model model, @RequestParam("mngrInqryNo") String mngrInqryNo, HttpSession session, PageParamVo pageParamVo) {
		
		MberDto mberDto = (MberDto)session.getAttribute("dto"); // 세션 값 확인 후 수정할 것
//		MberDto mberDto = new MberDto();
//		mberDto.setMberCode("0000");
		
		model.addAttribute("MNGR_INQRY_NO", mngrInqryNo);
		model.addAttribute("MBER_CODE", mberDto.getMberCode());
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return "admin/inqry/inqryForm";
	}
	
	// 관리자 문의 답변 등록
	@RequestMapping(value="insertMngrAnswer.do")
	public String insertMngrAnswer(Model model, MngrInqryDto mngrInqryDto) throws Exception {
		return mngrInqryService.insertMngrAnswer(model, mngrInqryDto);
	}
	
	// 관리자 문의 답변 수정
	@RequestMapping(value="updateMngrAnswer.do")
	public String updateMngrAnswer(Model model, MngrInqryDto mngrInqryDto, PageParamVo pageParamVo) throws Exception {
		return mngrInqryService.updateMngrAnswer(model, mngrInqryDto, pageParamVo);
	}
	
	// 관리자 문의 답변 삭제
	@RequestMapping(value="deleteMngrAnswer.do")
	public String deleteMngrAnswer(Model model, @RequestParam("mngrInqryNo") String mngrInqryNo) throws Exception {
		return mngrInqryService.deleteMngrAnswer(model, mngrInqryNo);
	}
	
}
