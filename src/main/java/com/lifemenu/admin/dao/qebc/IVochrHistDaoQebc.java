package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.hist.VochrHistDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

public interface IVochrHistDaoQebc {

	List<VochrHistDto> selectMultiVochrHist(HistoryParamVo historyParamVo);
	List<VochrHistDto> selectMultiVochrHistByCondition(ConditionParamVo conditionParamVo);
	
}
