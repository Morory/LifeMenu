package com.lifemenu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifemenu.admin.service.IBbsMngrService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-27
 * 	Version 	: 0.0.2
 * 
 * 	게시판 관리
 * 2021-06-12-0.0.1 	- 최초 작성
 * 2021-06-27-0.0.2 	- 서비스 명체 및 연동 확인, 삭제 기능 추가
 */

@Controller
public class BbsMngrController {

	@Autowired
	@Qualifier("bbsMngrService")
	private IBbsMngrService bbsMngrService;
	
	// 첫 화면 전체 게시글 목록 조회
	@RequestMapping(value="bbsMngrMulti.do")
	public String selectMultiBbsMngr(Model model, PageParamVo pageParamVo) {
		return bbsMngrService.selectMultiBbs(model, pageParamVo);
	}
	
	// 전체 게시글 목록 조건조회
	@RequestMapping(value="bbsMngrCondition.do")
	public String selectMultiBbsMngrByCondition(Model model, ConditionParamVo conditionParamVo) {
		return bbsMngrService.selectMultiBbsByCondition(model, conditionParamVo);
	}
	
	// 게시글 상세 조회
	@RequestMapping(value="bbsMngrDetail.do")
	public String selectOneBbsMngr(Model model, @RequestParam("bbscttNo") String bbscttNo, PageParamVo pageParamVo) {
		return bbsMngrService.selectOneBbs(model, bbscttNo, pageParamVo);
	}
	
	// 게시글 삭제
	@RequestMapping(value="deleteBbsMngr.do")
	public String deleteBbsMngr(Model model, @RequestParam("bbscttNo") String bbscttNo) throws Exception {
		return bbsMngrService.deleteBbs(model, bbscttNo);
	}
	
}
