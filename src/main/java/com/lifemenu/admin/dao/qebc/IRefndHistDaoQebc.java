package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.hist.RefndHistDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

public interface IRefndHistDaoQebc {

	List<RefndHistDto> selectMultiRefndHist(HistoryParamVo historyParamVo);
	List<RefndHistDto> selectMultiRefndHistByCondition(ConditionParamVo conditionParamVo);
	
}
