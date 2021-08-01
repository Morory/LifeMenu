package com.lifemenu.service;

import org.springframework.ui.Model;

import com.lifemenu.dto.ResveDto;
import com.lifemenu.dto.SetleDto;

public interface IResveSetleService {
	
	//식사권조회
	String selectOneVoucher(Model model, String voucherCode, String mberCode);
	
	//예약
	String insertResve(Model model, ResveDto resveDto, String voucherCode, String selerCode) throws Exception;
	
	//예약조회
	String selectOneResve(Model model, String resveCode);
	
	//예약삭제
	String deleteOneResve(Model model, String resveCode) throws Exception;
	
	//결제
	String insertSetle(Model model, SetleDto setleDto, String resveCode) throws Exception;
	
	//구매확정
	String purchsDcsn(Model modle, String setleCode) throws Exception;
	
}
