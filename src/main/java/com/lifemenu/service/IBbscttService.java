package com.lifemenu.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.BbscttDto;

public interface IBbscttService {

	// 단건 조회(게시글 상세 조회)
		String selectOneBbsctt(Model model, HttpSession session, String bbscttNo);
		
	// 다건 조회(목록 조회)
	String selectMultiBbsctt(Model model, PageParamVo pageParamVo);

	// 게시글 수정
	String bbscttUpdate(Model model, BbscttDto bbscttDto) throws Exception;
	
	//새글 등록
	String insertBbsctt(Model model, HttpSession session, BbscttDto bbDto);
	
	//답급 등록
	String insertComent(Model model, HttpSession session, BbscttDto bbscDto, String bbscttNo);
}
