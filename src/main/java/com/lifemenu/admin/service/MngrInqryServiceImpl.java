package com.lifemenu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.mebc.IMngrInqryDaoMebc;
import com.lifemenu.admin.dao.qebc.IMngrInqryDaoQebc;
import com.lifemenu.admin.dto.InqryMngrDto;
import com.lifemenu.admin.dto.MngrInqryDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-29
 * 	Version 	: 0.0.4
 * 
 * 	관리자 문의
 * 2021-06-18-0.0.2 	- 관리자 문의 서비스 명세 및 확인
 * 2021-06-26-0.0.3 	- 관리자문의 상세 조회
 * 2021-06-29-0.0.4 	- 상세조회 조건 변경
 */

@Service("mngrInqryService")
public class MngrInqryServiceImpl implements IMngrInqryService {

	@Autowired
	private IMngrInqryDaoMebc mngrInqryDaoMebc;
	@Autowired
	private IMngrInqryDaoQebc mngrInqryDaoQebc;
	
	private static final String VIEW_PAGE 			= "admin/inqry/inqryMngr";
	private static final String VIEW_DETAIL_PAGE 	= "admin/inqry/inqryDetail";
	private static final int 	DEFAULT_START 		= 1;
	private static final int 	DEFAULT_CNT 		= 8;
	
	// 전체 관리자 문의 목록 조회
	@Override
	public String selectMultiMngrInqry(Model model, PageParamVo pageParamVo) {

		List<InqryMngrDto> mngrInqryList = mngrInqryDaoQebc.selectMultiMngrInqry(pageParamVo);
		int totalCnt = mngrInqryDaoQebc.selectMngrInqryTotalCount();
		
		model.addAttribute("LIST", mngrInqryList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	// 전체 관리자 문의 목록 조건조회
	@Override
	public String selectMultiMngrInqryByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		if("MBER_CODE".equals(conditionParamVo.getConditionType())) {
			conditionParamVo.setConditionType("I.MBER_CODE");
		}
		
		List<InqryMngrDto> mngrInqryList = mngrInqryDaoQebc.selectMultiMngrInqryByCondition(conditionParamVo);
		int totalCnt = mngrInqryDaoQebc.selectMngrInqryTotalCount();
		
		model.addAttribute("LIST", mngrInqryList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	// 관리자 문의 상세 조회
	@Override
	public String selectOneMngrInqry(Model model, String mngrInqryNo, PageParamVo pageParamVo) {

		MngrInqryDto mngrInqry = mngrInqryDaoMebc.selectOneMngrInqry(mngrInqryNo);
		
		model.addAttribute("INQRY", mngrInqry);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_DETAIL_PAGE;
		
	}
	
	// 관리자 문의 답변 등록
	@Override
	@Transactional
	public String insertMngrAnswer(Model model, MngrInqryDto mngrInqryDto) throws Exception {

		// 1. 문의 Dto의 PK
		String inqryNo = mngrInqryDto.getMngrInqryNo();
		
		// 2. 답변 Dto의 PK 생성
		String answerNo = String.valueOf(mngrInqryDaoQebc.selectNextMngrInqryNo());
		mngrInqryDto.setMngrInqryNo(answerNo);
		
		// 3. 답변 Dto 등록
		MngrInqryDto isMngrAnswer = mngrInqryDaoMebc.selectOneMngrInqry(mngrInqryDto.getMngrInqryNo());
		if (isMngrAnswer != null) {
			throw new Exception();
		}
		mngrInqryDaoMebc.insertMngrInqry(mngrInqryDto);

		// 4. 문의 Dto 업데이트
		MngrInqryDto isMngrInqry = mngrInqryDaoMebc.selectOneMngrInqry(inqryNo);
		if (isMngrInqry == null) {
			throw new Exception();
		}
		isMngrInqry.setMngrAnswerNo(answerNo);
		mngrInqryDaoMebc.updateMngrInqry(isMngrInqry);
		
		
		return "redirect:/mngrInqryMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}

	@Override
	@Transactional
	public String updateMngrAnswer(Model model, MngrInqryDto mngrInqryDto, PageParamVo pageParamVo) throws Exception {

		MngrInqryDto isMngrInqry = mngrInqryDaoMebc.selectOneMngrInqry(mngrInqryDto.getMngrInqryNo());
		if (isMngrInqry == null) {
			throw new Exception();
		}
		
		mngrInqryDaoMebc.updateMngrInqry(mngrInqryDto);
		
		return "redirect:/mngrInqryDetail.do?mngrInqryNo=" + mngrInqryDto.getMngrInqryNo() 
			 + "&start=" + pageParamVo.getStart() + "&cnt=" + pageParamVo.getCnt();
				
	}

	@Override
	@Transactional
	public String deleteMngrAnswer(Model model, String mngrInqryNo) throws Exception {

		// 1. 문의의 답변번호 제거
		MngrInqryDto isMngrInqry = mngrInqryDaoQebc.selectOneMngrInqryByMngrAnswerNo(mngrInqryNo);
		if(isMngrInqry == null) {
			throw new Exception();
		}
		
		isMngrInqry.setMngrAnswerNo(null);
		mngrInqryDaoMebc.updateMngrInqry(isMngrInqry);
		
		// 2. 관리자 답변 제거
		MngrInqryDto isMngrAnswer = mngrInqryDaoMebc.selectOneMngrInqry(mngrInqryNo);
		if (isMngrAnswer == null) {
			throw new Exception();
		}
		
		mngrInqryDaoMebc.deleteMngrInqry(mngrInqryNo);
		
		return "redirect:/mngrInqryMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
				
	}

}
