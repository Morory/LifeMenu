package com.lifemenu.dao.qebc;

import java.util.List;

import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.TransactionListDto;

public interface ILoginAfterDaoQebc {
	
	MberDto selectOnePw(String code, String pw);  //회원탈퇴 할때 비밀번호 확인
	
	//상은
	List<TransactionListDto> selectMultiMyTransaction(String mberCode);
	//로그인한 사람의 판매 리스트 및 완료여부
	List<TransactionListDto> selectMultiMySellList(String mberCode);
}
