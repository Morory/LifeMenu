package com.lifemenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.dao.mebc.ICoinRepositoryDaoMebc;
import com.lifemenu.dao.mebc.IResveDaoMebc;
import com.lifemenu.dao.mebc.ISellerDaoMebc;
import com.lifemenu.dao.mebc.ISellerImageDaoMebc;
import com.lifemenu.dao.mebc.ISetleDaoMebc;
import com.lifemenu.dao.mebc.IVoucherDaoMebc;
import com.lifemenu.dao.qebc.ICoinRepositoryDaoQebc;
import com.lifemenu.dao.qebc.IResveDaoQebc;
import com.lifemenu.dao.qebc.ISetleDaoQebc;
import com.lifemenu.dto.CoinRepositoryDto;
import com.lifemenu.dto.MberDto;
import com.lifemenu.dto.ResveDto;
import com.lifemenu.dto.SellerDto;
import com.lifemenu.dto.SetleDto;
import com.lifemenu.dto.VoucherDto;
import com.lifemenu.dto.WalletDto;


@Service("resveSetleService")
public class ResveSetleServiceImpl implements IResveSetleService {
	
	@Autowired
	private IResveDaoMebc resveDaoMebc;
	@Autowired
	private IResveDaoQebc resveDaoQebc;
	@Autowired
	private ISetleDaoMebc setleDaoMebc;
	@Autowired
	private ISetleDaoQebc setleDaoQebc;
	@Autowired
	private IVoucherDaoMebc voucherDaoMebc;
	@Autowired
	private ISellerDaoMebc sellerDaoMebc;
	@Autowired
	private ISellerImageDaoMebc sellerImageDaoMebc;
	@Autowired
	private ICoinRepositoryDaoMebc coinRepositoryDaoMebc;
	@Autowired
	private ICoinRepositoryDaoQebc coinRepositoryDaoQebc;

	//예약페이지내에 출력
	@Override
	public String selectOneVoucher(Model model, String voucherCode, String mberCode) {
		
		VoucherDto voucherDto;
		SellerDto sellerDto;
		MberDto mberDtoSellerName;
		
		//파라미터회원코드로 판매자이름조회
		mberDtoSellerName = resveDaoQebc.selectSelerName(mberCode);
		//식사권조회
		voucherDto 	= voucherDaoMebc.selectOneVoucher(voucherCode);
		//판매자정보조회
		sellerDto 	= sellerDaoMebc.selectOneSeller(mberCode);
		String adres = selerImageSearch(mberCode);

		String voucherDay = voucherDto.getMealPrearngeDt();
		String voucherDayst = mealDtSearch(voucherDay);
		
		String bool = String.valueOf(voucherDto.getDelngAt());
		String delngAt;
		if("Y".equals(bool))
		{
			delngAt = "가능";
		}
		else
		{
			delngAt = "불가능";
		}

		model.addAttribute("SELER_NAME", mberDtoSellerName);
		model.addAttribute("SELECT_VOUCHER", voucherDto);
		model.addAttribute("SELECT_SELER", sellerDto);
		model.addAttribute("SELER_IMAGE", adres);
		model.addAttribute("SELECT_VOCHRDT", voucherDayst);
		model.addAttribute("DELNG_AT", delngAt);

		return "resve/resveForm";
	}
	
	//예약
	@Override
	@Transactional
	public String insertResve(Model model, ResveDto resveDto, String voucherCode, String selerCode) throws Exception {
		
		VoucherDto voucherDto;
		MberDto mberDtoSellerName;
		//판매자코드로 회원정보 조회
		mberDtoSellerName = resveDaoQebc.selectSelerName(selerCode);
		//식사권 정보 조회
		voucherDto = voucherDaoMebc.selectOneVoucher(voucherCode);
		
		String adres = selerImageSearch(selerCode);

		String voucherDay = voucherDto.getMealPrearngeDt();
		String voucherDayst = mealDtSearch(voucherDay);
		
		ResveDto resveDtoConfirmMulti = resveDaoQebc.selectOneResveRownum();
		if(resveDtoConfirmMulti == null)
		{
			resveDto.setResveCode("0001");
		}
		else
		{
			int resveCode = Integer.parseInt(resveDtoConfirmMulti.getResveCode()) + 1;
			String strResveCode = String.format("%04d", resveCode);
			resveDto.setResveCode(strResveCode);
		}
		System.out.println("예약번호 " + resveDto.getResveCode());
		resveDaoMebc.insertResve(resveDto);
		System.out.println("예약처리 완료");
		
		ResveDto resveDtoConfirm = resveDaoMebc.selectOneResve(resveDto.getResveCode());
		
		model.addAttribute("VOUCHER_MEALDT", voucherDayst);
		model.addAttribute("SELER_NAME",mberDtoSellerName);
		model.addAttribute("VOUCHER", voucherDto);
		model.addAttribute("SELECT_RESVE", resveDtoConfirm);
		model.addAttribute("SELER_IMAGE", adres);
		
		//예약확인페이지로 전환
		return "resve/resveConfirm";
	}
	
	//예약삭제
	@Override
	public String deleteOneResve(Model model, String resveCode) throws Exception {
		
		ResveDto resveDto = resveDaoMebc.selectOneResve(resveCode);
		if(resveDto == null)
		{
			model.addAttribute("RESVE_DTO", resveDto);
			return "err/resveSetleErr";
		}
		
		resveDaoMebc.deleteResve(resveCode);
		System.out.println("예약번호: " + resveCode + " 가 삭제 처리되었습니다.");
		
		return "redirect:/seller/list";
	}
	
	//결제페이지내에 출력
	@Override
	public String selectOneResve(Model model, String resveCode) {
		
		ResveDto resveDtoConfirm = resveDaoMebc.selectOneResve(resveCode);
		if(resveDtoConfirm == null)
		{
			model.addAttribute("RESVE_DTO", resveDtoConfirm);
			return "err/resveSetleErr";
		}
		
		String selerCode = setleDaoQebc.selectOneSelerCode(resveCode); 
		MberDto mberDto = resveDaoQebc.selectSelerName(selerCode);
		//판매자 코드로 조회하는게 아닌 식사권 코드로  식사권 조회
		String vochrCode = setleDaoQebc.selectOneVoucherCode(resveCode);
		VoucherDto voucherDto = voucherDaoMebc.selectOneVoucher(vochrCode);
		
		String adres = selerImageSearch(selerCode);

		String voucherDay = voucherDto.getMealPrearngeDt();
		String voucherDayst = mealDtSearch(voucherDay);
		
		model.addAttribute("SELECT_RESVE", resveDtoConfirm);
		model.addAttribute("SELER_NAME", mberDto);
		model.addAttribute("VOUCHER_INFO", voucherDto);
		model.addAttribute("VOUCHER_MEALDT",voucherDayst);
		model.addAttribute("SELER_IMAGE", adres);
		
		return "resve/setleForm";
	}
	
	//결제
	@Override
	@Transactional
	public String insertSetle(Model model, SetleDto setleDto, String resveCode) throws Exception {
		
		ResveDto resveDto = resveDaoMebc.selectOneResve(resveCode);
		if(resveDto == null)
		{
			model.addAttribute("RESVE_DTO", resveDto);
			return "err/resveSetleErr";
		}
		else
		{
			setleDto.setSetleCode(resveCode);
		}
		
		//예약번호로 판매자코드조회
		String selerCode = setleDaoQebc.selectOneSelerCode(resveCode);
		//판매자코드로 회원코드조회
		MberDto mberDto = resveDaoQebc.selectSelerName(selerCode);
		//판매자 코드로 조회하는게 아닌 식사권 코드로  식사권 조회
		String vochrCode = setleDaoQebc.selectOneVoucherCode(resveCode);
		VoucherDto voucherDto = voucherDaoMebc.selectOneVoucher(vochrCode);
		
		String adres = selerImageSearch(selerCode);

		String voucherDay = voucherDto.getMealPrearngeDt();
		String voucherDayst = mealDtSearch(voucherDay);

		setleDaoMebc.insertSetle(setleDto);
		SetleDto setleDtoConfirm = setleDaoMebc.selectOneSetle(setleDto.getSetleCode());
		System.out.println("결제번호 " + setleDtoConfirm.getSetleCode() + " 회원번호 " + resveDto.getMberCode());
		
		//결제시 회원지갑 코인 차감
		String mberCode = setleDaoQebc.selectOneResveMberCode(Integer.parseInt(resveCode));
		int mberCoin = setleDaoQebc.selectOneMberWalletCoin(mberCode);
		
		//구매자 지갑코인이 0코인일 경우
		if(mberCoin == 0)
		{
			setleDaoMebc.deleteSetle(resveCode);
			resveDaoMebc.deleteResve(resveCode);
			model.addAttribute("MBER_COIN", mberCoin);
			return "err/resveSetleErr";
		}
		
		WalletDto walletDto = new WalletDto();
		walletDto.setMberCode(mberCode);
		walletDto.setCoinCo(mberCoin - setleDtoConfirm.getSetlePc());
		
		setleDaoQebc.updateMberCoin(walletDto);
		System.out.println("회원번호 " + mberCode + " 남은코인 " + walletDto.getCoinCo());
		
		
		
		//결제완료시 식사권 거래가능여부 N으로 업뎃
		setleDaoQebc.updateVoucherDelngAt(voucherDto);
		
		System.out.println("해당 식사권의 거래가능여부가 N처리 되었습니다.");
		
		//저장소내역
		CoinRepositoryDto repositoryDtlsDtoConfirmMulti = coinRepositoryDaoQebc.selectOneRepositoryDtlsRownum();
		CoinRepositoryDto rdDto = new CoinRepositoryDto();
		if(repositoryDtlsDtoConfirmMulti == null)
		{
			rdDto.setDelngCode("000001");
			rdDto.setRepositoryCoinCo(10000 + setleDtoConfirm.getSetlePc());
			
		}
		else
		{
			int rdCode = Integer.parseInt(repositoryDtlsDtoConfirmMulti.getDelngCode()) + 1;
			String strRdCode = String.format("%06d", rdCode);
			rdDto.setDelngCode(strRdCode);
			int lastCoin = coinRepositoryDaoQebc.selectOneCoinRepositoryCoin();
			rdDto.setRepositoryCoinCo(lastCoin + setleDtoConfirm.getSetlePc());
		}
		
		rdDto.setMberCode(resveDto.getMberCode());
		rdDto.setSetleCode(setleDtoConfirm.getSetleCode());
		//저장소 입금 D 출금 W
		rdDto.setDelngSe('D');
		rdDto.setDelngAmount(setleDtoConfirm.getSetlePc());
		coinRepositoryDaoMebc.insertCoinRepository(rdDto);
		
		System.out.println("거래번호 " + rdDto.getDelngCode() + " 거래금액 " + rdDto.getDelngAmount());
		System.out.println("저장소 총 코인 " + rdDto.getRepositoryCoinCo());
		System.out.println("결제처리 완료");
		
		model.addAttribute("SELECT_SETLE", setleDtoConfirm);
		model.addAttribute("SELER_IMAGE", adres);
		model.addAttribute("SELECT_SELER", mberDto);
		model.addAttribute("VOUCHER_DT", voucherDayst);
		
		return "resve/setleConfirm";
	}
	
	//구매확정
	@Override
	public String purchsDcsn(Model model, String setleCode) throws Exception {
		
		SetleDto setleDtoConfirm = setleDaoMebc.selectOneSetle(setleCode);
		if(setleDtoConfirm == null)
		{
			model.addAttribute("SETLE_DTO", setleDtoConfirm);
			return "err/resveSetleErr";
		}
		
		setleDtoConfirm.setPurchsDcsnAt('Y');
		String strDate = setleDtoConfirm.getSetleDt();
		String setleDate = strDate.substring(0, 10);
		setleDtoConfirm.setSetleDt(setleDate);
		
		System.out.println(setleDate);
		setleDaoMebc.updatePurchsDcsn(setleDtoConfirm);
		
		System.out.println("구매확정이 완료되었습니다.");
		
		//코인저장소의 마지막데이터 및 총 코인갯수 조회
		CoinRepositoryDto crDto = coinRepositoryDaoQebc.selectOneRepositoryDtlsRownum();
		CoinRepositoryDto setCrDto = new CoinRepositoryDto();
		
		int crCode = Integer.parseInt(crDto.getDelngCode()) + 1;
		String strCrCode = String.format("%06d", crCode);
		
		setCrDto.setDelngCode(strCrCode);

		int lastCoin = crDto.getRepositoryCoinCo();
		//결제한 코인
		int setleCoin = setleDtoConfirm.getSetlePc();
		//코인저장소의 마지막코인 - 결제한 코인
		setCrDto.setRepositoryCoinCo(lastCoin - setleCoin);
		String selerCode = setleDaoQebc.selectOneSelerCode(setleCode);
		setCrDto.setMberCode(selerCode);
		setCrDto.setDelngSe('W');
		setCrDto.setSetleCode(setleCode);
		setCrDto.setDelngAmount(setleCoin);
		//판매자 코드로 코인저장소 insert
		coinRepositoryDaoMebc.insertCoinRepository(setCrDto);
		
		System.out.println("코인저장소의 코인: " + lastCoin + " 결제한 코인: " + setleCoin);
		System.out.println("판매자 코드로 코인저장소에 저장되었습니다.");
		
		//판매자 지갑 코인 갯수 증감
		WalletDto walletDto = new WalletDto();
		walletDto.setMberCode(selerCode);
		int mberCoin = setleDaoQebc.selectOneMberWalletCoin(selerCode);
		walletDto.setCoinCo(mberCoin + setleCoin);
		setleDaoQebc.updateMberCoin(walletDto);
		
		System.out.println("판매자 번호: " + selerCode);
		System.out.println("판매자 코인: " + mberCoin + " 결제코인: " + setleCoin);
		System.out.println("판매자지갑 코인이 업데이트 되었습니다.");
		
		return "redirect:/transactionList";
	}
	
	//판매자 첫번째 이미지
	public String selerImageSearch(String mberCode) {
		
		//판매자코드로 판매자이미지조회
		String adres = sellerImageDaoMebc.selectOneSellerImage(mberCode);
		adres = adres.replace("\\", "/");
		
		
		return adres;
	}
	
	//화면 날짜변환
	public String mealDtSearch(String voucherDay) {
		
		String voucherDayst;
		voucherDayst = voucherDay.substring(0, 4) + "년 " + voucherDay.substring(4, 6) + "월 "
				+ voucherDay.substring(6, 8) + "일 " + voucherDay.substring(8, 10) + "시 " + 
				voucherDay.substring(10, 12) + "분 ";
		
		return voucherDayst;
	}
}
