package com.lifemenu.admin.dao.qebc;

import com.lifemenu.admin.vo.ConditionParamVo;

public interface IAdminCommonsDaoQebc {
	
	int selectTotalCountByTableName(String tableName);
	
	int selectTotalCountByTableNameWithCondition(String tableName, ConditionParamVo conditionParamVo);

}
