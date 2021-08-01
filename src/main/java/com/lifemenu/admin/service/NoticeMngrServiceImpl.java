package com.lifemenu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.mebc.INoticeMngrDaoMebc;
import com.lifemenu.admin.dao.qebc.INoticeMngrDaoQebc;
import com.lifemenu.admin.dto.NoticeMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.dto.NoticeDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-28
 * 	Version 	: 0.0.4
 * 
 * 	공지사항
 * 2021-06-16-0.0.2 	- 서비스 명세 및 DAO연결 확인
 * 2021-06-26-0.0.3 	- 연동 확인 및 등록, 수정, 삭제 추가
 * 2021-06-28-0.0.4 	- 제목+내용 조회 추가
 * 2021-07-01-0.0.5 	- 페이지 처리
 */

@Service("noticeMngrService")
public class NoticeMngrServiceImpl implements INoticeMngrService {

	@Autowired
	private INoticeMngrDaoMebc noticeMngrDaoMebc;
	@Autowired
	private INoticeMngrDaoQebc noticeMngrDaoQebc;
	
	private static final String VIEW_PAGE 			= "admin/notice/noticeMngr";
	private static final String VIEW_DETAIL_PAGE 	= "admin/notice/noticeDetail";
	private static final int 	DEFAULT_START 		= 1;
	private static final int 	DEFAULT_CNT 		= 8;
	
	// 첫 화면 및 전체 공지사항 목록 조회(목록 출력 다시 확인 할 것!)
	@Override
	public String selectMultiNotice(Model model, PageParamVo pageParamVo) {

		List<NoticeMngrDto> noticeList = noticeMngrDaoQebc.selectMultiNotice(pageParamVo);
		int totalCnt = noticeMngrDaoQebc.selectNoticeTotalCount();
		
		System.out.println(noticeList);
		
		model.addAttribute("LIST", noticeList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "Y");
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	// 전체 공지사항 목록 조건 조회
	@Override
	public String selectMultiNoticeByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		
		List<NoticeMngrDto> noticeList;
		if("BBSCTT_SJ_CN".equals(conditionParamVo.getConditionType())) {
			noticeList = noticeMngrDaoQebc.selectMultiNoticeByBbscttSjCn(conditionParamVo);
		}
		else {
			noticeList = noticeMngrDaoQebc.selectMultiNoticeByCondition(conditionParamVo);
		}
		int totalCnt = noticeMngrDaoQebc.selectNoticeTotalCount();
		
		model.addAttribute("LIST", noticeList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "N");
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	// 관리자 문의 상세 조회
	@Override
	public String selectOneNotice(Model model, String bbscttNo, PageParamVo pageParamVo) {

		NoticeDto noticeDto = noticeMngrDaoMebc.selectOneNotice(bbscttNo);
		
		model.addAttribute("NOTICE", noticeDto);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_DETAIL_PAGE;
		
	}
	
	// 공지사항 등록
	@Override
	@Transactional
	public String insertNotice(Model model, NoticeDto noticeDto) throws Exception {

		noticeDto.setBbscttNo(String.valueOf(noticeMngrDaoQebc.selectNextBbscttNo()));
		System.out.println("insertNoticeDto= "+noticeDto.toString());
		
		NoticeDto isNotice = noticeMngrDaoMebc.selectOneNotice(noticeDto.getBbscttNo());
		if (isNotice != null) {
			throw new Exception();
		}
		
		noticeMngrDaoMebc.insertNotice(noticeDto);
		
		return "redirect:/noticeMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
	
	@Override
	@Transactional
	public String updateNotice(Model model, NoticeDto noticeDto, PageParamVo pageParamVo) throws Exception {

		NoticeDto isNotice = noticeMngrDaoMebc.selectOneNotice(noticeDto.getBbscttNo());
		if (isNotice == null) {
			throw new Exception();
		}
		
		noticeMngrDaoMebc.updateNotice(noticeDto);
		
		return "redirect:/noticeMngrDetail.do?bbscttNo=" + noticeDto.getBbscttNo() 
		 	 + "&start=" + pageParamVo.getStart() + "&cnt=" + pageParamVo.getCnt();
		
	}
	
	@Override
	@Transactional
	public String deleteNotice(Model model, String bbscttNo) throws Exception {

		NoticeDto isNotice = noticeMngrDaoMebc.selectOneNotice(bbscttNo);
		if (isNotice == null) {
			throw new Exception();
		}
		
		noticeMngrDaoMebc.deleteNotice(bbscttNo);
		
		return "redirect:/noticeMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
	
}
