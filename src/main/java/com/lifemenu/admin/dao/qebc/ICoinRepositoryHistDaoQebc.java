package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.hist.CoinRepositoryHistDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

public interface ICoinRepositoryHistDaoQebc {

	List<CoinRepositoryHistDto> selectMultiCoinRepositoryHist(HistoryParamVo historyParamVo);
	List<CoinRepositoryHistDto> selectMultiCoinRepositoryHistByCondition(ConditionParamVo conditionParamVo);
	
}
