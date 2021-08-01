package com.lifemenu.admin.dao.mebc;

import com.lifemenu.dto.MberDto;

// ver.1.0.0

public interface IMberMngrDaoMebc {

	MberDto selectOneMberMngr(String mberCode);
	
	void insertOneMberMngr(MberDto mberDto);
	
	void updateMberMngr(MberDto mberDto);
	
	void deleteMberMngr(String mberCode);
	
}
