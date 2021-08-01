package com.lifemenu.dao.mebc;

import com.lifemenu.dto.ResveDto;

public interface IResveDaoMebc {
	
	//예약
	void insertResve(ResveDto resveDto);
	
	//예약조회
	ResveDto selectOneResve(String resveCode);
	
	//예약삭제
	void deleteResve(String resveCode);

}
