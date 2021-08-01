package com.lifemenu.dao.qebc;

import com.lifemenu.dto.VoucherDto;
import com.lifemenu.dto.WalletDto;

public interface ISetleDaoQebc {
	
	//판매자코드조회
	String selectOneSelerCode(String resveCode);
	
	//회원코드로 식사권 단건조회
	VoucherDto selectOneVoucher(int mberCode);
	
	//예약코드로 식사권코드 조회
	String selectOneVoucherCode(String resveCode);
	
	//회원코드로 지갑코인조회
	int selectOneMberWalletCoin(String mberCode);
	
	//예약코드로 구매회원코드 조회
	String selectOneResveMberCode(int resveCode);
	
	//결제시 회원코드 차감
	void updateMberCoin(WalletDto walletDto);
	
	//결제시 식사권 거래가능여부 N
	void updateVoucherDelngAt(VoucherDto voucherDto);

}
