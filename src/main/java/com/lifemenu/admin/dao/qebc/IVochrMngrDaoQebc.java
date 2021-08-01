package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.VochrMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.ResveDto;
import com.lifemenu.dto.SetleDto;

// ver.1.0.0

public interface IVochrMngrDaoQebc {

	List<VochrMngrDto> selectMultiVochr(PageParamVo pageParamVo);
	
	List<VochrMngrDto> selectMultiVochrByCondition(ConditionParamVo conditionParamVo);
	
	List<ResveDto> selectMultiResve(String vochrCode);
	
	List<SetleDto> selectMultiSetle(String vochrCode);
	
	int selectVochrMngrTotalCount();
	int selectResveSetleTotalCount();
	
}
