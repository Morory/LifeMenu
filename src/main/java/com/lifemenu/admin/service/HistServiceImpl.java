package com.lifemenu.admin.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.qebc.ICoinRepositoryHistDaoQebc;
import com.lifemenu.admin.dao.qebc.IRefndHistDaoQebc;
import com.lifemenu.admin.dao.qebc.IResveSetleHistDaoQebc;
import com.lifemenu.admin.dao.qebc.IVochrHistDaoQebc;
import com.lifemenu.admin.dao.qebc.IWalletDtlsHistDaoQebc;
import com.lifemenu.admin.dto.HistMngrDto;
import com.lifemenu.admin.dto.hist.CoinRepositoryHistDto;
import com.lifemenu.admin.dto.hist.RefndHistDto;
import com.lifemenu.admin.dto.hist.ResveSetleHistDto;
import com.lifemenu.admin.dto.hist.VochrHistDto;
import com.lifemenu.admin.dto.hist.WalletDtlsHistDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.HistoryParamVo;

@Service("histService")
@Aspect
public class HistServiceImpl implements IHistService {
	
	@Autowired
	private IVochrHistDaoQebc 		vochrHistDaoQebc;
	@Autowired
	private IResveSetleHistDaoQebc 	resveSetleHistDaoQebc;
	@Autowired
	private IRefndHistDaoQebc 		refndHistDaoQebc;
	@Autowired
	private IWalletDtlsHistDaoQebc 	walletDtlsHistDaoQebc;
	@Autowired
	private ICoinRepositoryHistDaoQebc coinRepositoryHistDaoQebc;

	private static final Logger logger = LoggerFactory.getLogger(HistServiceImpl.class);
	
	private static final String VIEW_PAGE = "admin/hist/histMngr";
	private static final int 	PAGE_SIZE = 8;

	@Override
	public String selectMultiHist(Model model, HistoryParamVo historyParamVo) {
		
		// 출력해야할 목록
		// list.1. 식사권 히스토리
		// list.2. 예약결제 히스토리
		// list.3. 환불 히스토리
		// list.4. 지갑내역 히스토리
		// list.5. 코인저장소 히스토리
		
		
		// 1. historyParamVo의 검색조건을 확인한다(디폴트 값 세팅)

		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sToday = format.format(today);
		String sYear = sToday.split(" ")[0].split("-")[0];
		String sMonth = sToday.split(" ")[0].split("-")[1];
		String sDayOfMonth = sToday.split(" ")[0].split("-")[2];
		String sHour = sToday.split(" ")[1].split(":")[0];
		String sMinute = sToday.split(" ")[1].split(":")[1];
		String sSecond = sToday.split(" ")[1].split(":")[2];
		String sEndRcordDt = sYear + "-" + sMonth + "-" + sDayOfMonth + " " + sHour + ":" + sMinute + ":" + sSecond + ".0";
		String sStartRcordDt = sYear + "-" + sMonth + "-" + "01" + " " + "00" + ":" + "00" + ":" + "00.0";
		System.out.println("sStartRcordDt="+sStartRcordDt);
		System.out.println("sEndRcordDt="+sEndRcordDt);
		
		Timestamp startRcordDt = Timestamp.valueOf(sStartRcordDt);
		historyParamVo.setStartRcordDt(startRcordDt);
		Timestamp endRcordDt = Timestamp.valueOf(sEndRcordDt);
		historyParamVo.setEndRcordDt(endRcordDt);
		System.out.println("step1 : "+historyParamVo.toString());
		
		// 2. 각 히스토리들을 가져온다
		List<VochrHistDto> vochrHistList = vochrHistDaoQebc.selectMultiVochrHist(historyParamVo);
		List<ResveSetleHistDto> resveSetleHistList = resveSetleHistDaoQebc.selectMultiResveSetleHist(historyParamVo);
		List<RefndHistDto> refndHistList = refndHistDaoQebc.selectMultiRefndHist(historyParamVo);
		List<WalletDtlsHistDto> walletDtlsHistList = walletDtlsHistDaoQebc.selectMultiWalletDtlsHist(historyParamVo);
		List<CoinRepositoryHistDto> coinRepositoryHistList = coinRepositoryHistDaoQebc.selectMultiCoinRepositoryHist(historyParamVo);
		System.out.println("step2-vochrHist : "+vochrHistList.toString());
		System.out.println("step2-resveSetleHist : "+resveSetleHistList.toString());
		System.out.println("step2-refndHist : "+refndHistList.toString());
		System.out.println("step2-walletDtlsHist : "+walletDtlsHistList.toString());
		System.out.println("step2-coinRepositoryHist : "+coinRepositoryHistList.toString());

		// 3. 각 히스토리들을 전체 히스토리로 합친다
		List<HistMngrDto> list = new ArrayList<HistMngrDto>();
		if (vochrHistList != null) {
//			list.addAll(vochrHistList);
			for(int i = 0; i < vochrHistList.size(); i++) {
				HistMngrDto histMngrDto = new HistMngrDto();
				
				VochrHistDto vochrHistDto = vochrHistList.get(i);
				
				histMngrDto.setRcordDt(vochrHistDto.getRcordDt());
				String[] histSe = vochrHistDto.getHistSe().split("-");
				histMngrDto.setHistSeTable(histSe[0]);
				histMngrDto.setHistSeExec(histSe[1]);
				histMngrDto.setSeCode(vochrHistDto.getVochrCode());// 식사권코드, 히스토리 필터 시 사용
				histMngrDto.setHistBrief(vochrHistDto.toString());
				histMngrDto.setContents(vochrHistDto);
				
				list.add(histMngrDto);
			}
		}
		if (resveSetleHistList != null) {
//			list.addAll(resveSetleHistList);
			for(int i = 0; i < resveSetleHistList.size(); i++) {
				HistMngrDto histMngrDto = new HistMngrDto();
				
				ResveSetleHistDto resveSetleHistDto = resveSetleHistList.get(i);
				
				histMngrDto.setRcordDt(resveSetleHistDto.getRcordDt());
				String[] histSe = resveSetleHistDto.getHistSe().split("-");
				histMngrDto.setHistSeTable(histSe[0]);
				histMngrDto.setHistSeExec(histSe[1]);
				histMngrDto.setSeCode(resveSetleHistDto.getResveSetleCode());// 예약결제코드, 히스토리 필터 시 사용
				histMngrDto.setHistBrief(resveSetleHistDto.toString());
				histMngrDto.setContents(resveSetleHistDto);
				
				list.add(histMngrDto);
			}
		}
		if (refndHistList != null) {
//			list.addAll(refndHistList);
			for(int i = 0; i < refndHistList.size(); i++) {
				HistMngrDto histMngrDto = new HistMngrDto();
				
				RefndHistDto refndHistDto = refndHistList.get(i);
				
				histMngrDto.setRcordDt(refndHistDto.getRcordDt());
				String[] histSe = refndHistDto.getHistSe().split("-");
				histMngrDto.setHistSeTable(histSe[0]);
				histMngrDto.setHistSeExec(histSe[1]);
				histMngrDto.setSeCode(refndHistDto.getSetleCode());// 결제코드, 히스토리 필터 시 사용
				histMngrDto.setHistBrief(refndHistDto.toString());
				histMngrDto.setContents(refndHistDto);
				
				list.add(histMngrDto);
			}
		}
		if (walletDtlsHistList != null) {
//			list.addAll(walletDtlsHistList);
			for(int i = 0; i < walletDtlsHistList.size(); i++) {
				HistMngrDto histMngrDto = new HistMngrDto();
				
				WalletDtlsHistDto walletDtlsHistDto = walletDtlsHistList.get(i);
				
				histMngrDto.setRcordDt(walletDtlsHistDto.getRcordDt());
				String[] histSe = walletDtlsHistDto.getHistSe().split("-");
				histMngrDto.setHistSeTable(histSe[0]);
				histMngrDto.setHistSeExec(histSe[1]);
				histMngrDto.setSeCode(walletDtlsHistDto.getMberCode());// 회원코드, 히스토리 필터 시 사용
				histMngrDto.setHistBrief(walletDtlsHistDto.toString());
				histMngrDto.setContents(walletDtlsHistDto);
				
				list.add(histMngrDto);
			}
		}
		if (coinRepositoryHistList != null) {
//			list.addAll(coinRepositoryHistList);
			for(int i = 0; i < coinRepositoryHistList.size(); i++) {
				HistMngrDto histMngrDto = new HistMngrDto();
				
				CoinRepositoryHistDto CoinRepositoryHistDto = coinRepositoryHistList.get(i);
				
				histMngrDto.setRcordDt(CoinRepositoryHistDto.getRcordDt());
				String[] histSe = CoinRepositoryHistDto.getHistSe().split("-");
				histMngrDto.setHistSeTable(histSe[0]);
				histMngrDto.setHistSeExec(histSe[1]);
				histMngrDto.setSeCode(CoinRepositoryHistDto.getDelngCode());// 거래코드, 히스토리 필터 시 사용
				histMngrDto.setHistBrief(CoinRepositoryHistDto.toString());
				histMngrDto.setContents(CoinRepositoryHistDto);
				
				list.add(histMngrDto);
			}
		}
		System.out.println("step3 : "+list.toString());
		
		// 4. 히스토리를 rcordDt 기준으로 내림차순으로 정렬한다
		list.sort(Comparator.reverseOrder());
		System.out.println("step4 정렬후 : "+list);
		int totalCnt = list.size();
		
		// 5. 히스토리 리스트의 크기를 페이지 크기에 맞춘다
		if(list.size() > PAGE_SIZE) {
			list.subList(0, PAGE_SIZE);
		}
		System.out.println("step5 list의 사이즈 : "+list.size());
		
		model.addAttribute("LIST", list);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "Y");
		model.addAttribute("START", historyParamVo.getStart());
		model.addAttribute("CNT", historyParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	@Override
	public String selectMultiHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		return VIEW_PAGE;
		
	}

	@Override
	public String selectMultiVochrHist(Model model, HistoryParamVo historyParamVo) {
		
		logger.info("selectMultiVochrHist");
		
		List<VochrHistDto> vochrHistList = vochrHistDaoQebc.selectMultiVochrHist(historyParamVo);
		
		model.addAttribute("LIST", vochrHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiVochrHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		logger.info("selectMultiVochrHistByCondition");
		
		List<VochrHistDto> vochrHistList = vochrHistDaoQebc.selectMultiVochrHistByCondition(conditionParamVo);
		
		model.addAttribute("LIST", vochrHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiResveSetleHist(Model model, HistoryParamVo historyParamVo) {

		logger.info("selectMultiResveSetleHist");
		
		List<ResveSetleHistDto> resveSetleHistList = resveSetleHistDaoQebc.selectMultiResveSetleHist(historyParamVo);
		
		model.addAttribute("LIST", resveSetleHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiResveSetleHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		logger.info("selectMultiResveSetleHistByCondition");
		
		List<ResveSetleHistDto> resveSetleHistList = resveSetleHistDaoQebc.selectMultiResveSetleHistByCondition(conditionParamVo);
		
		model.addAttribute("LIST", resveSetleHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiRefndHist(Model model, HistoryParamVo historyParamVo) {

		logger.info("selectMultiRefndHist");
		
		List<RefndHistDto> refndHistList = refndHistDaoQebc.selectMultiRefndHist(historyParamVo);
		
		model.addAttribute("LIST", refndHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiRefndHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		logger.info("selectMultiRefndHistByCondition");
		
		List<RefndHistDto> refndHistList = refndHistDaoQebc.selectMultiRefndHistByCondition(conditionParamVo);
		
		model.addAttribute("LIST", refndHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiWalletDtlsHist(Model model, HistoryParamVo historyParamVo) {

		logger.info("selectMultiWalletDtlsHist");
		
		List<WalletDtlsHistDto> walletDtlsHistList = walletDtlsHistDaoQebc.selectMultiWalletDtlsHist(historyParamVo);
		
		model.addAttribute("LIST", walletDtlsHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiWalletDtlsHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		logger.info("selectMultiWalletDtlsHistByCondition");
		
		List<WalletDtlsHistDto> walletDtlsHistList = walletDtlsHistDaoQebc.selectMultiWalletDtlsHistByCondition(conditionParamVo);
		
		model.addAttribute("LIST", walletDtlsHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiCoinRepositoryHist(Model model, HistoryParamVo historyParamVo) {

		logger.info("selectMultiCoinRepositoryHist");
		
		List<CoinRepositoryHistDto> coinRepositoryHistList = coinRepositoryHistDaoQebc.selectMultiCoinRepositoryHist(historyParamVo);
		
		model.addAttribute("LIST", coinRepositoryHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

	@Override
	public String selectMultiCoinRepositoryHistByCondition(Model model, ConditionParamVo conditionParamVo) {

		logger.info("selectMultiCoinRepositoryHistByCondition");
		
		List<CoinRepositoryHistDto> coinRepositoryHistList = coinRepositoryHistDaoQebc.selectMultiCoinRepositoryHistByCondition(conditionParamVo);
		
		model.addAttribute("LIST", coinRepositoryHistList);
		
		return "redirect:/"+VIEW_PAGE;
		
	}

}
