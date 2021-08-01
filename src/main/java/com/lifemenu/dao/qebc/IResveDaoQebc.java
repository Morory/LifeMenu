package com.lifemenu.dao.qebc;

import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.ResveDto;

public interface IResveDaoQebc {

	//마지막예약조회
	ResveDto selectMultiResve();
	
	//마지막데이터 조회
	ResveDto selectOneResveRownum();
	
	//판매자이름 조회
	MberDto selectSelerName(String mberCode);
	
}
