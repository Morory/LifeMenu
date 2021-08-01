package com.lifemenu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.WalletDtlsDto;
import com.lifemenu.dto.WalletDto;
import com.lifemenu.service.IWalletService;

@Controller
public class WalletController {
    
    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    
    @Autowired
    private IWalletService walletService;

    
    @RequestMapping(value="walletView")	//지갑페이지로 이동
    public String walletView(HttpSession session, Model model){	//지갑 화면
    	logger.info("walletView called ============");
    	 
    	 MberDto dto = (MberDto) session.getAttribute("dto");
    	
    	 String mberCode = dto.getMberCode();
    	 WalletDto walletDto = walletService.selectOneMberWallet(mberCode);
    	 int havedCoinCo = walletDto.getCoinCo();

    	 List<Object> list = walletService.selectMultiWalletDtls(mberCode, model);
    	 model.addAttribute("COIN", havedCoinCo);
    	 model.addAttribute("LIST", list);
    	

    	return "walletView";
    }
    
    @RequestMapping(value="depositView")	//입금 화면으로 이동
    public String depositView(HttpSession session, Model model){	//입금 화면
    	logger.info("depositView called ============");
   
    	 MberDto dto = (MberDto) session.getAttribute("dto");
    	 
    	 String mberCode = dto.getMberCode();
    	 WalletDto walletDto = walletService.selectOneMberWallet(mberCode);
    	 int coinCo = walletDto.getCoinCo();
    	 model.addAttribute("COIN", coinCo);
    	return "depositView";
    }

    @RequestMapping(value="withDrawView")	//출금 화면으로 이동
    public String withDrawView(HttpSession session, Model model){	//출금 화면
    	logger.info("withDrawView called ============");
    	 MberDto dto = (MberDto) session.getAttribute("dto");
    	 System.out.println("월렛컨트롤러에서 출금화면으로 보낼 때 멤버 계좌번호"+dto.getMberAcountNo());
    	 String mberCode = dto.getMberCode();	//멤버코드 구하기
    	 WalletDto walletDto = walletService.selectOneMberWallet(mberCode);	//해당코드의 지갑 구하기
    	 int coinCo = walletDto.getCoinCo();	//가지고 있는 코인 수
    	 model.addAttribute("COIN", coinCo);
    	 
    	 int money = walletService.changeMoney(coinCo);	//출금가능 금액을 위한 변환
    	 model.addAttribute("MONEY", money);
    	 
    	 MberDto chkDto = walletService.selectOneMber(mberCode);
    	 if(chkDto.getMberAcountNo() == null)
    	 {
    		 return "updateMberAcountNo";
    	 }
    	

    	return "withDrawView";
    }
 
    @RequestMapping(value="deposit.do")
    public String deposit(WalletDtlsDto walletDtlsDto) throws Exception{	//입금
        String viewPage = "";
        String mberCode = walletDtlsDto.getMberCode();	//멤버 코드 구하기

       	MberDto dto = walletService.selectOneMber(mberCode);	//멤버코드로  회원 단건조회
       
        if(dto == null) {
        	System.out.println("해당 회원코드의 회원은 존재하지 않음.");
        	throw new Exception();
        }
        
       
        viewPage = walletService.deposit(walletDtlsDto);	//지갑 거래내역에 인서트하면서 지갑에 코인도 바꾸기
       
    	return viewPage;
    }
    
    @RequestMapping(value="withDraw.do" , method= RequestMethod.POST)
    public String withDraw(WalletDtlsDto walletDtlsDto) throws Exception{//출금
        String viewPage = "";
        
        String mberCode = walletDtlsDto.getMberCode();	//멤버 코드 구하기
        
        MberDto dto = walletService.selectOneMber(mberCode);	//멤버코드로  회원 단건조회
        
        if(dto == null) {
        	System.out.println("해당 회원코드의 회원은 존재하지 않음.");
        	throw new Exception();
        }
   	 	
        viewPage = walletService.withdraw(walletDtlsDto);
        
        
    	return viewPage;
    }
    
    @RequestMapping(value="mberAcountNoUpdate.do" , method= RequestMethod.POST)
    public String mberAcountNoInsert(HttpSession session, String mberAcountNo) throws Exception{//계좌번호 등록(업데이트 처리)
        String viewPage = "";
        MberDto dto = (MberDto) session.getAttribute("dto");
   	 
   	 	String mberCode = dto.getMberCode();
        
        viewPage = walletService.updateMberAcountNo(mberCode, mberAcountNo, session);
        
        
    	return viewPage;
    }
    


}
