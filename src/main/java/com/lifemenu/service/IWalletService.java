package com.lifemenu.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.WalletDtlsDto;
import com.lifemenu.dto.WalletDto;

public interface IWalletService {
	
	MberDto selectOneMber(String mberCode);	//회원 단건 조회용
	List<Object> selectMultiWalletDtls(String mberCode, Model model);	//지갑내역 멀티
	WalletDto selectOneMberWallet(String mberCode);		//지갑 회원코드로 찾는거
	String deposit(WalletDtlsDto walletDtlsDto) throws Exception;//입금
	String withdraw(WalletDtlsDto walletDtlsDto) throws Exception;//출금
	String updateMberAcountNo(String mberCode, String mberAcountNo, HttpSession session) throws Exception;	//회원 계좌번호 등록
	int changeMoney(int coinCo);	//코인을 돈으로 환산
	int changeCoin(int money);		//돈을 받아서 코인으로 환산
}
