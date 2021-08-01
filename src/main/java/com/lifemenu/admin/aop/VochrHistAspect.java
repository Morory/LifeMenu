package com.lifemenu.admin.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifemenu.admin.dao.mebc.IVochrHistDaoMebc;
import com.lifemenu.admin.dto.hist.VochrHistDto;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.dto.VoucherDto;

public class VochrHistAspect {

	public static int seq = 0;
	
	@Autowired
	private IVochrHistDaoMebc vochrHistDaoMebc;
	@Autowired
	private AdminCommons adminCommons;
	
	public void afterReturning(JoinPoint jp) {
		
		System.out.println(++seq + "--" + jp.getSignature().getName() + "실행----");
		VoucherDto voucherDto = (VoucherDto)jp.getArgs()[0];
		
		// 파라미터 확인
		System.out.println(++seq + " parameter= " + voucherDto.toString());
		
//		// insert된 데이터 가져오기 (isVoucherDto값이 null, DB에서 commit전이라 해당 데이터가 없는 상태로 추정 ->)
//		VoucherDto isVoucherDto = voucherDaoMebc.selectOneVoucher(voucherDto.getMberCode());
//		System.out.println(++seq + " insert-data= " + isVoucherDto.toString());
		
		// Hist객체 생성
		if (adminCommons == null) {
			System.out.println("오류 발생!");
		}
		VochrHistDto vochrHistDto = adminCommons.createVochrHist(voucherDto, "VOCHR-INSERT");
		System.out.println(++seq + " histDto= " + vochrHistDto.toString());
		
		// insert vochrHistDto
		vochrHistDaoMebc.insertVochrHist(vochrHistDto);
		
	}
	
}
