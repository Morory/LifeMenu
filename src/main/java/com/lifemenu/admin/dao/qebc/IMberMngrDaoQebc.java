package com.lifemenu.admin.dao.qebc;

import java.util.List;

import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dto.MberDto;

public interface IMberMngrDaoQebc {

	List<MberDto> selectMultiMberMngr(PageParamVo pageParamVo);
	List<MberDto> selectMultiMberMngrByCondition(ConditionParamVo conditionParamVo);

	int selectMberTotalCount();
	
}
