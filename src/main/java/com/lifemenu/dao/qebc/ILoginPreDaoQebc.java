package com.lifemenu.dao.qebc;

import java.util.List;

import com.lifemenu.dto.MberDto;

public interface ILoginPreDaoQebc {

    public MberDto selectOneLogin(String id, String pw);	//로그인용

    public MberDto selectOneIdCheck(String id);//회원가입 아이디체크용
    
    public MberDto selectOneNcnmCheck(String ncnm);//회원가입 닉네임체크용
    
    public MberDto selectOneEmailCheck(String email);//회원가입 이메일체크용

    public MberDto selectOnefindId(String mberRlnm, String mberEmail);//아이디찾기
    
    public MberDto selectOnefindPw(String mberId, String mberEmail);//비번찾기
    
    public List<MberDto> selectMultiMber();	//회원 전체조회
    
    public MberDto selectOneMberRownum(); //회원코드 마지막데이터 조회

}
