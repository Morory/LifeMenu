package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.dto.hist.WalletDtlsHistDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

public interface IWalletDtlsHistDaoQebc {

	List<WalletDtlsHistDto> selectMultiWalletDtlsHist(HistoryParamVo historyParamVo);
	List<WalletDtlsHistDto> selectMultiWalletDtlsHistByCondition(ConditionParamVo conditionParamVo);
	
}
