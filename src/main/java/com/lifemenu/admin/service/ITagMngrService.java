package com.lifemenu.admin.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.lifemenu.admin.dto.TagDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-28
 * 	Version 	: 0.0.2
 * 
 * 	태그, 태그 조회수 관리
 * 2021-06-09-0.0.1 	- 최초 작성
 * 2021-06-28-0.0.2 	- 연동 확인 및 등록, 수정, 삭제 기능 추가
 */

public interface ITagMngrService {
	
	String selectMultiTagMngr(Model model, PageParamVo pageParamVo);
	String selectMultiTagMngrByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String insertTagMngr(Model model, String tagName, MultipartFile[] uploadFile) throws Exception;
	
	String updateTagMngr(Model model, TagDto tagDto, PageParamVo pageParamVo) throws Exception;
	
	String deleteTagMngr(Model model, String tagCode) throws Exception;
	
}
