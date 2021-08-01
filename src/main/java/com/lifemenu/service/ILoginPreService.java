package com.lifemenu.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lifemenu.dto.MberDto;

public interface ILoginPreService {
	
	public String selectOneLogin(HttpSession session, Model model, String id, String pw); //로그인
	
    public MberDto selectOneMber(String mberCode); //pk 단건조회
    
    public MberDto selectOneIdCheck(String id);//회원가입 아이디 체크용
    
    public MberDto selectOneNcnmCheck(String ncnm);//회원가입 닉네임 체크용
    
    public MberDto selectOneEmailCheck(String email);//회원가입 이메일 체크용

    public String insertMber(MberDto mberDto,int coinCo);	//회원등록

    public String findUpdatePw(String mberPw, String mberCode) throws Exception;	//비밀번호 재설정

    public String selectOnefindId(String mberRlnm, String mberEmail, Model model); //아이디 찾기

    public String selectOnefindPw(String mberId, String mberEmail, Model model);	//비밀번호 찾기

}
