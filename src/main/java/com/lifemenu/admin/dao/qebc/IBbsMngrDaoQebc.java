package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.BbsMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.BbscttDto;

// ver.1.0.1.

public interface IBbsMngrDaoQebc {

	// 다건 조회(게시글 목록 조회)
	List<BbsMngrDto> selectMultiBbsMngr(PageParamVo pageParamVo);
	List<BbsMngrDto> selectMultiBbsMngrByCondition(ConditionParamVo conditionParamVo);
	List<BbsMngrDto> selectMultiBbsMngrByBbscttSjCn(ConditionParamVo conditionParamVo);
	
	// 다건 조회(게시글 리플 조회)
	List<BbscttDto> selectMultiBbsReply(String upBbscttNo);
	
	int selectBbsTotalCount();
	
}
