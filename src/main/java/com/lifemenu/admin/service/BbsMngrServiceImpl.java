package com.lifemenu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.mebc.IBbsMngrDaoMebc;
import com.lifemenu.admin.dao.qebc.IBbsMngrDaoQebc;
import com.lifemenu.admin.dto.BbsMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.dto.BbscttDto;


/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-28
 * 	Version 	: 0.0.5
 * 
 * 	자유게시판
 * 2021-06-18-0.0.2 	- 서비스 명세 및 확인
 * 2021-06-27-0.0.3 	- 연동 확인 및 삭제 기능 추가
 * 2021-06-28-0.0.4 	- 검색 시 쿼리 오류(ORA-00918) 보안
 * 2021-06-29-0.0.5 	- 삭제 처리 시 확인
 */

@Service("bbsMngrService")
public class BbsMngrServiceImpl implements IBbsMngrService {

	@Autowired
	private IBbsMngrDaoMebc bbsMngrDaoMebc;
	@Autowired
	private IBbsMngrDaoQebc bbsMngrDaoQebc;
	
	private static final String VIEW_PAGE 			= "admin/bbs/bbsMngr";
	private static final String VIEW_DETAIL_PAGE 	= "admin/bbs/bbsDetail";
	private static final int 	DEFAULT_START 		= 1;
	private static final int 	DEFAULT_CNT 		= 8;
	
	// 전체 게시글 목록 조회
	@Override
	public String selectMultiBbs(Model model, PageParamVo pageParamVo) {

		List<BbsMngrDto> bbsList = bbsMngrDaoQebc.selectMultiBbsMngr(pageParamVo);
		int totalCnt = bbsMngrDaoQebc.selectBbsTotalCount();
		
		model.addAttribute("LIST", bbsList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	// 전체 게시글 목록 조건조회
	@Override
	public String selectMultiBbsByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		if("MBER_CODE".equals(conditionParamVo.getConditionType())) {
			conditionParamVo.setConditionType("B.MBER_CODE");
		}
		
		List<BbsMngrDto> bbsList;
		if("BBSCTT_SJ_CN".equals(conditionParamVo.getConditionType())) {
			bbsList = bbsMngrDaoQebc.selectMultiBbsMngrByBbscttSjCn(conditionParamVo);
		}
		else {
			bbsList = bbsMngrDaoQebc.selectMultiBbsMngrByCondition(conditionParamVo);
		}
		
		int totalCnt = bbsMngrDaoQebc.selectBbsTotalCount();
		
		model.addAttribute("LIST", bbsList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	// 게시글 상세 조회
	@Override
	public String selectOneBbs(Model model, String bbscttNo, PageParamVo pageParamVo) {
		
		BbscttDto bbscttDto = bbsMngrDaoMebc.selectOneBbsMngr(bbscttNo);
		
		model.addAttribute("BBSCTT", bbscttDto);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_DETAIL_PAGE;
		
	}
	
	// 게시글 삭제
	@Override
	@Transactional
	public String deleteBbs(Model model, String bbscttNo) throws Exception {

		// 1. 게시글에 달린 리플(답글) 제거 (좋은 방법은 아니므로 나중에 블라인드 처리로 바꿀 것)
		List<BbscttDto> replyList = bbsMngrDaoQebc.selectMultiBbsReply(bbscttNo);
		if (replyList != null && replyList.size() > 0) {
			for (BbscttDto bbscttDto : replyList) {
				deleteBbs(model, bbscttDto.getBbscttNo());
			}
		}
		
		// 2. 게시글 삭제
		BbscttDto isBbs = bbsMngrDaoMebc.selectOneBbsMngr(bbscttNo);
		if (isBbs == null) {
			throw new Exception();
		}
		
		bbsMngrDaoMebc.deleteBbsMngr(bbscttNo);
		
		return "redirect:/bbsMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
	
}
