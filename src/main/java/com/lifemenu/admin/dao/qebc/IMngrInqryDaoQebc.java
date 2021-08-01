package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.InqryMngrDto;
import com.lifemenu.admin.dto.MngrInqryDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;

// ver.1.0.1.

public interface IMngrInqryDaoQebc {

	MngrInqryDto selectOneMngrInqryByMngrAnswerNo(String mngrAnswerNo);
	
	List<InqryMngrDto> selectMultiMngrInqry(PageParamVo pageParamVo);
	List<InqryMngrDto> selectMultiMngrInqryByCondition(ConditionParamVo conditionParamVo);
	
	int selectMngrInqryTotalCount();
	
	int selectNextMngrInqryNo();
	
}
