package com.lifemenu.cmmn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifemenu.admin.dao.qebc.IAdminCommonsDaoQebc;
import com.lifemenu.admin.dto.hist.VochrHistDto;
import com.lifemenu.dto.VoucherDto;

@Component
public class AdminCommons {

	@Autowired
	private static IAdminCommonsDaoQebc adminCommonsDaoQebc;
	
	public static String convertCondition(String condition) {
		String resultCondition = "%" + condition + "%";
		return resultCondition;
	}
	
	// 목록 조회 시 페이징 처리를 위해 테이블 별로 전체 건 수 가져오기
	public int selectTotalCountByTableName(String tableName) {
		return adminCommonsDaoQebc.selectTotalCountByTableName(tableName);
	}
	
	// 식사권 히스토리 등록을 위한 Dto매핑 함수
	public VochrHistDto createVochrHist(VoucherDto voucherDto, String histSe) {
		
		VochrHistDto vochrHistDto = new VochrHistDto();
		
		vochrHistDto.setHistSe(histSe);
		
		vochrHistDto.setVochrCode( voucherDto.getVochrCode() );
		vochrHistDto.setMberCode( voucherDto.getMberCode() );
		vochrHistDto.setVochrPrice( voucherDto.getVochrPrice() );
		vochrHistDto.setMealPrearngeDt( voucherDto.getMealPrearngeDt() );
//		vochrHistDto.setVochrRegistDt( Date.valueOf(voucherDto.getVochrRegistDt()) );// null 값
		vochrHistDto.setMealTime( voucherDto.getMealTime() );
		vochrHistDto.setDelngAt( String.valueOf(voucherDto.getDelngAt() ));
		
		return vochrHistDto;
		
	}
	
}
