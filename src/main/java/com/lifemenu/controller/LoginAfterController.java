package com.lifemenu.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.WalletDto;
import com.lifemenu.service.ILoginAfterService;
import com.lifemenu.service.IWalletService;

@Controller
public class LoginAfterController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginAfterController.class);
    
    @Autowired
    private ILoginAfterService loginAfterService;
    @Autowired
    private IWalletService walletService;

    @RequestMapping(value="myPageView")
    public String myPageView(){	//회원정보수정 화면
    	logger.info("myPageView called ============");
        return "myPageView";
    }
    @RequestMapping(value="updateMberView")
    public String updateMberView(){	//회원정보수정 화면
    	logger.info("updateMberView called ============");
        return "updateMberView";
    }
    @PostMapping(value="update.do")
    public String updateMber(MberDto mberDto, Model model, HttpSession session) throws Exception{	// 회원수정.do
    	logger.info("update.do called ============");
    	String viewPage = "";
    	viewPage = loginAfterService.updateMber(mberDto, model, session);
    	return viewPage;
    }
    @RequestMapping(value="scencView")
    public String updateScencView(HttpSession session, Model model){	// 회원탈퇴 화면
    	logger.info("updateScencView called ============");
    	MberDto dto = (MberDto) session.getAttribute("dto");
    	String mberCode = dto.getMberCode();
    	
    	WalletDto walletDto = walletService.selectOneMberWallet(mberCode);
   	 	int havedCoinCo = walletDto.getCoinCo();
   	 	model.addAttribute("COIN", havedCoinCo);
    	
        return "scencView";
    }
    @PostMapping(value="updateScenc.do")
    public String updateScenc(MberDto mberDto, HttpSession session) throws Exception{	// 회원탈퇴 여부 변경.do
    	logger.info("updateScenc.do called ============");
    	String viewPage ="";
    	
    	viewPage = loginAfterService.updateScenc(mberDto, session);
        return viewPage;
    }
    //상은
    @RequestMapping(value="transactionList")
    public String transactionListForm(Model model, HttpSession session) {
    	
    	return loginAfterService.selectMultiMyTransaction(model, session);
    }

}
