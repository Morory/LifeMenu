package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.TagMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

// ver.1.0.1

public interface ITagMngrDaoQebc {

	List<TagMngrDto> selectMultiTagMngr(PageParamVo pageParamVo);
	List<TagMngrDto> selectMultiTagMngrByCondition(ConditionParamVo conditionParamVo);	
	
	int selectTagSearchCount(String tagCode);
	
	int selectTagMngrTotalCount();
	
	int selectNextTagCode();
	
}
