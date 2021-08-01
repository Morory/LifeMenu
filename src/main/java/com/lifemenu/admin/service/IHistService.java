package com.lifemenu.admin.service;

import org.springframework.ui.Model;

import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-07-02
 * 	Version 	: 0.0.4
 * 
 * 	히스토리
 * 2021-06-17-0.0.2 	- bbsNotice
 * 2021-06-21-0.0.3 	- bbsNotice 삭제, Hist, VochrHist, ResveSetleHist, WalletDtlsHist, CoinRepositoryHist 서비스 명세 및 확인
 * 2021-07-02-0.0.4 	- insert메소드 삭제, aop로 히스토리 insert
 */

public interface IHistService {

	String selectMultiHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiVochrHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiVochrHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiResveSetleHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiResveSetleHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiRefndHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiRefndHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiWalletDtlsHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiWalletDtlsHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
	String selectMultiCoinRepositoryHist(Model model, HistoryParamVo historyParamVo);
	String selectMultiCoinRepositoryHistByCondition(Model model, ConditionParamVo conditionParamVo);
	
}
