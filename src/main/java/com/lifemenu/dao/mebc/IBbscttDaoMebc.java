package com.lifemenu.dao.mebc;

import com.lifemenu.dto.BbscttDto;

public interface IBbscttDaoMebc {
	
	BbscttDto selectOneBbsctt(String bbscttNo);


	void bbscttUpdate(BbscttDto bbscttDto);
	
	//새글등록
	void insertBbsctt(BbscttDto bbscttDto);
	
	//답글등록
	void insertComent(BbscttDto bbscttDto);
}
