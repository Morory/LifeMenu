package com.lifemenu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifemenu.admin.service.IHistService;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-12
 * 	Version 	: 0.0.1
 * 
 * 	히스토리 관리
 * 2021-06-21-0.0.2 	-VochrHist 추가
 */

@Controller
public class HistController {

	@Autowired
	@Qualifier("histService")
	private IHistService histService;
	
	@RequestMapping(value="histMngrMulti.do")
	public String selectMultiHist(Model model, HistoryParamVo historyParamVo) {
		
		return histService.selectMultiHist(model, historyParamVo);
		
	}
	
	@RequestMapping(value="histMngrCondition.do")
	public String histMngrMultiByCondition(Model model, ConditionParamVo conditionParamVo) {

		return histService.selectMultiHistByCondition(model, conditionParamVo);
		
	}
	
}
