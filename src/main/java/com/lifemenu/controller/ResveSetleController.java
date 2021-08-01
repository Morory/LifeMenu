package com.lifemenu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.lifemenu.dto.ResveDto;
import com.lifemenu.dto.SetleDto;
import com.lifemenu.service.IResveSetleService;

@Controller
@RequestMapping("/ResveSetle")
public class ResveSetleController {

	@Autowired
	private IResveSetleService resveSetleService;
	
	//예약페이지
	@RequestMapping("/resveForm")
	public String resveForm(Model model, @RequestParam(value="VOUCHER_CODE") String voucherCode,
			@RequestParam(value="MBER_CODE") String mberCode) {
		
		return resveSetleService.selectOneVoucher(model, voucherCode, mberCode);
	}
	
	//예약진행
	@RequestMapping("insertResve")
	public String insertResve(Model model, ResveDto resveDto, 
			@RequestParam(value="VOUCHER_CODE") String voucherCode, 
			@RequestParam(value="SELER_CODE") String selerCode) throws Exception {
		
		return resveSetleService.insertResve(model, resveDto, voucherCode, selerCode);
	}
	
	//예약취소
	@RequestMapping("deleteResve")
	public String deleteResve(Model model, @RequestParam(value="RESVE_CODE") String resveCode) throws Exception {

		return resveSetleService.deleteOneResve(model, resveCode);
	}
	
	//결제페이지
	@RequestMapping("/setleForm")
	public String setleForm(Model model, @RequestParam(value="RESVE_CODE") String resveCode) {
		
		return resveSetleService.selectOneResve(model, resveCode);
	}
	
	//결제진행
	@RequestMapping("insertSetle")
	public String insertSetle(Model model, SetleDto setleDto, 
			@RequestParam(value="RESVE_CODE") String resveCode) throws Exception {
		
		return resveSetleService.insertSetle(model, setleDto, resveCode);
	}
	
	//구매확정
	@RequestMapping("puDcAt")
	public String purchsDcsnAt(Model model, @RequestParam(value="SETLE_CODE") String setleCode) throws Exception {
		return resveSetleService.purchsDcsn(model, setleCode);
	}
}
