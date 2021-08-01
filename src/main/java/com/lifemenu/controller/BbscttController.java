package com.lifemenu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.BbscttDto;
import com.lifemenu.service.IBbscttService;



@Controller
public class BbscttController {
    
	@Autowired
	@Qualifier("bbscttService")
	private IBbscttService bbscttService;
	
	// 첫 화면 전체 자유게시판 목록 조회
	@RequestMapping(value="bbscttMulti.do")
	public String selectMultiBbsctt(Model model, PageParamVo pageParamVo) {
		return bbscttService.selectMultiBbsctt(model, pageParamVo);
	}
	
	// 자유 게시글 상세 조회
	@RequestMapping(value="bbscttDetail.do")
	public String selectOneBbsctt(Model model, HttpSession session, @RequestParam("BBSCTT_NO") String bbscttNo) {
		return bbscttService.selectOneBbsctt(model, session, bbscttNo);
	}
	
	//새글 등록페이지
	@RequestMapping(value="bbscttInsertForm.do")
	public String insertBbscttForm() {
		return "bbsctt/bbscttInsertForm";
	}
	
	//새글 등록
	@RequestMapping(value="bbscttInsert.do")
	public String insertBbsctt(Model model, HttpSession session, BbscttDto bbscDto) {
		
		return bbscttService.insertBbsctt(model, session, bbscDto);
	}
	
	@RequestMapping(value="comentInsert.do")
	public String insertComent(Model model, HttpSession session, 
			BbscttDto bbscDto, @RequestParam(value="BBSCTT_NO") String bbscttNo) {
		
		return bbscttService.insertComent(model, session, bbscDto, bbscttNo);
	}

}
