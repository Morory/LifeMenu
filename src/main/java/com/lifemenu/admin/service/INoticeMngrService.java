package com.lifemenu.admin.service;

import org.springframework.ui.Model;

import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.NoticeDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-26
 * 	Version 	: 0.0.3
 * 
 * 	공지사항
 * 2021-06-17-0.0.2 	- 서비스 명세 및 DAO연결 확인
 * 2021-06-26-0.0.3 	- 연동 확인, 등록, 수정, 삭제 추가
 */

public interface INoticeMngrService {

	// 단건 조회(상세 조회)
	String selectOneNotice(Model model, String bbscttNo, PageParamVo pageParamVo);
	
	// 다건 조회(목록 조회)
	String selectMultiNotice(Model model, PageParamVo pageParamVo);
	String selectMultiNoticeByCondition(Model model, ConditionParamVo conditionParamVo);
	
	// 공지사항 등록
	String insertNotice(Model model, NoticeDto noticeDto) throws Exception;
	
	// 공지사항 수정
	String updateNotice(Model model, NoticeDto noticeDto, PageParamVo pageParamVo) throws Exception;
	
	// 공지사항 삭제
	String deleteNotice(Model model, String bbscttNo) throws Exception;
	
}
