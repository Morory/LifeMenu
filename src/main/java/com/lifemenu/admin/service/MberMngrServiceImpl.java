package com.lifemenu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.mebc.IMberMngrDaoMebc;
import com.lifemenu.admin.dao.qebc.IMberMngrDaoQebc;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.dao.mebc.ILoginAfterDaoMebc;
import com.lifemenu.dao.mebc.ILoginPreDaoMebc;
import com.lifemenu.dao.mebc.ISellerDaoMebc;
import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.SellerDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-29
 * 	Version 	: 0.0.6
 * 
 * 	회원, 판매자 관리
 * 2021-06-16-0.0.2 	- 서비스 내용 확인 및 명시
 * 2021-06-24-0.0.3 	- 회원 상세 조회 추가
 * 2021-06-26-0.0.4 	- 연동 확인 & 문서 업데이트
 * 2021-06-29-0.0.5 	- 수정 및 삭제 리턴 페이지 수정, 처리후 메시지 추가
 * 2021-06-30-0.0.6 	- 회원 수정 처리 오류 수정(판매자가 존재하지 않는 경우 - 0.0.5 버전 리벤지)
 * 2021-07-01-0.0.7 	- 페이지 처리 수정
 */

@Service("mberMngrService")
public class MberMngrServiceImpl implements IMberMngrService {

	@Autowired
	private IMberMngrDaoQebc mberMngrDaoQebc;
	@Autowired
	private IMberMngrDaoMebc mberMngrDaoMebc;
	@Autowired
	private ILoginPreDaoMebc loginPreDaoMebc;
	@Autowired
	private ILoginAfterDaoMebc loginAfterDaoMebc;
	@Autowired
	private ISellerDaoMebc sellerDaoMebc;
	
	private static final String VIEW_PAGE 			= "admin/mber/mberMngr";
	private static final String VIEW_DETAIL_PAGE 	= "admin/mber/mberDetail";
	private static final int 	DEFAULT_START 		= 1;
	private static final int 	DEFAULT_CNT 		= 8;
	
	@Override
	public String selectOneMberMngr(Model model, String mberCode, PageParamVo pageParamVo) {
		
		MberDto 	mberDto 	= mberMngrDaoMebc.selectOneMberMngr(mberCode);
		SellerDto 	sellerDto 	= sellerDaoMebc.selectOneSeller(mberCode);
		
		model.addAttribute("MBER", mberDto);
		model.addAttribute("SELER", sellerDto);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_DETAIL_PAGE;
		
	}
	
	@Override
	public String selectMultiMber(Model model, PageParamVo pageParamVo) {

		List<MberDto> mberList = mberMngrDaoQebc.selectMultiMberMngr(pageParamVo);
		int totalCnt = mberMngrDaoQebc.selectMberTotalCount();
		
		model.addAttribute("LIST", mberList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "Y");
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	@Override
	public String selectMultiMberByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		
		List<MberDto> mberList = mberMngrDaoQebc.selectMultiMberMngrByCondition(conditionParamVo);
		int totalCnt = mberMngrDaoQebc.selectMberTotalCount();
		
		model.addAttribute("LIST", mberList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "N");
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	@Override
	@Transactional
	public String updateMberMngr(Model model, MberDto mberDto, SellerDto sellerDto, PageParamVo pageParamVo) throws Exception {

		String msg = "";
		
		MberDto isMber = loginPreDaoMebc.selectOneMber(mberDto.getMberCode());
		if (isMber == null) {
			throw new Exception();
		}
		
		loginAfterDaoMebc.updateMber(mberDto);
		
		// 판매자일 경우에만 판매자 정보 업데이트(0.0.5 버전에서 수정했던 사항, 0.0.6 리벤지)
		if ("2".equals(mberDto.getMberAuthorLevel())) {
			SellerDto isSeller = sellerDaoMebc.selectOneSeller(sellerDto.getMberCode());
			if (isSeller == null) {
				throw new Exception();
			}
			
			sellerDaoMebc.updateSeller(sellerDto);
		}
		
//		msg = mberDto.getMberCode() + " 회원의 정보가 수정되었습니다.";
		msg = " 회원의 정보가 수정되었습니다.";
		
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		model.addAttribute("MSG", msg);
		
		return "redirect:/mberMngrMulti.do?start=" + pageParamVo.getStart() + "&cnt=" + pageParamVo.getCnt();
		
	}
	
	@Override
	@Transactional
	public String deleteMberMngr(Model model, String mberCode) throws Exception {
		
		String msg = "";
		
		MberDto isMber = loginPreDaoMebc.selectOneMber(mberCode);
		if (isMber == null) {
			throw new Exception();
		}
		
		if ("Y".equals(isMber.getMberSecsnAt())) {
			msg = "이미 탈퇴된 회원입니다";
		}
		else {
			loginAfterDaoMebc.updateScenc(mberCode);
			msg = mberCode + "회원이 탈퇴되었입니다";
		}
		
		model.addAttribute("START", DEFAULT_START);
		model.addAttribute("CNT", DEFAULT_CNT);
		model.addAttribute("MSG", msg);
		
		return "redirect:/mberMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
		
}
