package com.lifemenu.admin.dao.mebc;

import com.lifemenu.dto.BbscttDto;

// ver.1.0.0

public interface IBbsMngrDaoMebc {

	BbscttDto selectOneBbsMngr(String bbscttNo);
	
	void insertBbsMngr(BbscttDto bbscttDto);
	
	void updateBbsMngr(BbscttDto bbscttDto);
	
	void deleteBbsMngr(String bbscttNo);
	
}
