package com.lifemenu.dao.qebc;

import java.util.List;

import com.lifemenu.dto.RefndDto;
import com.lifemenu.dto.VoucherDto;

public interface IRefndDaoQebc {
	
	//결제코드로 식사권코드 조회
	String selectOneVoucherCode(String setleCode);
	
	//결제코드로 판매자코드 조회
	String selectOneSelerCode(String setleCode);
	
	//환불신청리스트 조회(처리여부 N)
	List<RefndDto> selectMultiRefndAtList();
	
	//환불전체 리스트 조회
	List<RefndDto> selectMultiRefndAllList();
	
	//식사권 거래가능여부 Y업데이트
	void updateVoucherDelngAt(VoucherDto voucherDto);
	
	//환불불가
	void updateRefndAt(RefndDto refndDto);
}
