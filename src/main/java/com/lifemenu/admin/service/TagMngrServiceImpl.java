package com.lifemenu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.lifemenu.admin.dao.mebc.ITagDaoMebc;
import com.lifemenu.admin.dao.mebc.ITagReadCntDaoMebc;
import com.lifemenu.admin.dao.qebc.ITagDaoQebc;
import com.lifemenu.admin.dao.qebc.ITagMngrDaoQebc;
import com.lifemenu.admin.dto.TagDto;
import com.lifemenu.admin.dto.TagMngrDto;
import com.lifemenu.admin.dto.TagReadCntDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.cmmn.IUploadCommon;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-28
 * 	Version 	: 0.0.4
 * 
 * 	태그, 태그 조회수 관리
 * 2021-06-19-0.0.2 	- 서비스 명세 및 확인
 * 2021-06-28-0.0.3 	- 연동 확인 등록, 수정, 삭제 기능 추가
 * 2021-06-30-0.0.4 	- ITagReadCntDaoMebc.java pk조회 반환타입 변경
 */

@Service("tagMngrService")
public class TagMngrServiceImpl implements ITagMngrService {

	@Autowired
	private ITagDaoMebc tagDaoMebc;
	@Autowired
	private ITagDaoQebc tagDaoQebc;
	@Autowired
	private ITagReadCntDaoMebc tagReadCntDaoMebc;
	@Autowired
	private ITagMngrDaoQebc tagMngrDaoQebc;
	@Autowired
	private IUploadCommon uploadCommon;
	
	private static final String VIEW_PAGE 		= "admin/tag/tagMngr";
	private static final int 	DEFAULT_START 	= 1;
	private static final int 	DEFAULT_CNT 	= 8;
	
	@Override
	public String selectMultiTagMngr(Model model, PageParamVo pageParamVo) {
		
		List<TagMngrDto> list = tagMngrDaoQebc.selectMultiTagMngr(pageParamVo);
		int totalCnt = tagMngrDaoQebc.selectTagMngrTotalCount();
		
		model.addAttribute("LIST", list);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	@Override
	public String selectMultiTagMngrByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		if("TAG_CODE".equals(conditionParamVo.getConditionType())) {
			conditionParamVo.setConditionType("T.TAG_CODE");
		}
		
		List<TagMngrDto> list = tagMngrDaoQebc.selectMultiTagMngrByCondition(conditionParamVo);
		int totalCnt = tagMngrDaoQebc.selectTagMngrTotalCount();
		
		model.addAttribute("LIST", list);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}
	
	@Override
	@Transactional
	public String insertTagMngr(Model model, String tagName, MultipartFile[] uploadFile) throws Exception {
		
		TagDto tagDto = new TagDto();
		
		// 1. 태그명이 중복되는 지 확인
		String isTagNm = tagDaoQebc.selectTagCodeByName(tagName);
		if(isTagNm != null) {
			throw new Exception();
		}
		tagDto.setTagNm(tagName);
		
		// 2. 태그 코드 생성
		String nextTagCode = String.valueOf(tagMngrDaoQebc.selectNextTagCode());
		tagDto.setTagCode(nextTagCode);
		
		// 3. 태그 이미지 복사 및 Dto에 경로 추가
		List<String> imageAdres = uploadCommon.uploadAjax(uploadFile, "c:/img/tag");
		tagDto.setImageAdres(imageAdres.get(0));
		
		// 3. insert 태그
		System.out.println(tagDto.toString());
		tagDaoMebc.insertTag(tagDto);
		
		// 4. insert 태그 조회수
		TagReadCntDto tagReadCntDto = tagReadCntDaoMebc.selectOneTagReadCnt(tagDto.getTagCode());
		if (tagReadCntDto != null) {
			throw new Exception();
		}
		tagReadCntDaoMebc.insertTagReadCnt(tagDto.getTagCode());
		
		model.addAttribute("MSG", "태그 등록 완료");
		
		return "redirect:/tagMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
	
	@Override
	@Transactional
	public String updateTagMngr(Model model, TagDto tagDto, PageParamVo pageParamVo) throws Exception {
		
		System.out.println("update: "+tagDto.toString());
		
		TagDto isTag = tagDaoMebc.selectOneTag(tagDto.getTagCode());
		
		if(isTag == null) {
			throw new Exception();
		}
		
		tagDaoMebc.updateTag(tagDto);
		
		model.addAttribute("MSG", "태그 수정 완료");
		
		return "redirect:/tagMngrMulti.do?start=" + pageParamVo.getStart() + "&cnt=" + pageParamVo.getCnt();
		
	}
	
	@Override
	@Transactional
	public String deleteTagMngr(Model model, String tagCode) throws Exception {
		
		// 태그 삭제 처리 시, 관리자와 상의할 것
		// 판매자가 등록한 태그 처리할 것.
		System.out.println("delete: "+tagCode);
		
		String msg = "";
		// 1. 태그 검색 테이블에 존재하는 경우, 삭제 불가
		int cnt = tagMngrDaoQebc.selectTagSearchCount(tagCode);
		if (cnt > 0) {
			msg = "판매자가 사용중인 태그입니다";
		}
		else {
			// 2. 태그 조회수 삭제
			TagReadCntDto isTagReadCnt = tagReadCntDaoMebc.selectOneTagReadCnt(tagCode);
			if(isTagReadCnt == null) {
				throw new Exception();
			}
			tagReadCntDaoMebc.deleteTagReadCnt(tagCode);
			
			// 3. 태그 삭제
			TagDto isTag = tagDaoMebc.selectOneTag(tagCode);
			if(isTag == null) {
				throw new Exception();
			}
			tagDaoMebc.deleteTag(tagCode);
			
			msg = "태그가 삭제되었습니다";
		}
		
		model.addAttribute("MSG", msg);
		
		return "redirect:/tagMngrMulti.do?start=" + DEFAULT_START + "&cnt=" + DEFAULT_CNT;
		
	}
	
}
