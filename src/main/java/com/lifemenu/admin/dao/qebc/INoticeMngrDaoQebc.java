package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.NoticeMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

// ver.1.0.1.

public interface INoticeMngrDaoQebc {

	List<NoticeMngrDto> selectMultiNotice(PageParamVo pageParamVo);
	List<NoticeMngrDto> selectMultiNoticeByCondition(ConditionParamVo conditionParamVo);
	List<NoticeMngrDto> selectMultiNoticeByBbscttSjCn(ConditionParamVo conditionParamVo);
	
	int selectNoticeTotalCount();
	
	int selectNextBbscttNo();
	
}
