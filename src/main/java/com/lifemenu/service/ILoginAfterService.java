package com.lifemenu.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lifemenu.dto.MberDto;

public interface ILoginAfterService {
	
	public MberDto selectOneMber(String code);

    public MberDto selectOnePw(String code, String pw);

    public String updateMber(MberDto mberDto, Model model,HttpSession session) throws Exception;

    public String updateScenc(MberDto mberDto,HttpSession session) throws Exception;
    //상은
    public String selectMultiMyTransaction(Model model, HttpSession session); 

}
