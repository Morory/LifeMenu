package com.lifemenu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lifemenu.admin.dto.TagDto;
import com.lifemenu.admin.service.ITagMngrService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-28
 * 	Version 	: 0.0.2
 * 
 * 	태그, 태그 조회수
 * 2021-06-10-0.0.1 	- 최초 작성
 * 2021-06-28-0.0.2 	- 연동 확인, 등록, 수정, 삭제 기능 추가
 */

@Controller
public class TagMngrController {

	@Autowired
	@Qualifier("tagMngrService")
	private ITagMngrService tagMngrService;
	
	// 전체 태그 목록 조회
	@RequestMapping(value="tagMngrMulti.do")
	public String selectMultiTagMngr(Model model, PageParamVo pageParamVo) {
		return tagMngrService.selectMultiTagMngr(model, pageParamVo);
	}
	
	// 전체 태그 목록 조건조회
	@RequestMapping(value="tagMngrCondition.do")
	public String viewTagMngr(Model model, ConditionParamVo conditionParamVo) {
		return tagMngrService.selectMultiTagMngrByCondition(model, conditionParamVo);
	}
	
	// 태그 등록
	@RequestMapping(value="insertTagMngr.do")
	public String insertTagMngr(Model model, @RequestParam("tagName") String tagName, MultipartFile[] uploadFile) throws Exception {
		return tagMngrService.insertTagMngr(model, tagName, uploadFile);
	}
	
	// 태그 수정
	@RequestMapping(value="updateTagMngr.do")
	public String updateTagMngr(Model model, TagDto tagDto, PageParamVo pageParamVo) throws Exception {
		return tagMngrService.updateTagMngr(model, tagDto, pageParamVo);
	}
	
	// 태그 삭제
	@RequestMapping(value="deleteTagMngr.do")
	public String deleteTagMngr(Model model, @RequestParam("tagCode") String tagCode) throws Exception {
		return tagMngrService.deleteTagMngr(model, tagCode);
	}
	
}
