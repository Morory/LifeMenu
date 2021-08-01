package com.lifemenu.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.dao.qebc.IVochrMngrDaoQebc;
import com.lifemenu.admin.dto.VochrMngrDto;
import com.lifemenu.admin.vo.ConditionParamVo;
import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.cmmn.AdminCommons;
import com.lifemenu.dao.mebc.IVoucherDaoMebc;
import com.lifemenu.dto.ResveDto;
import com.lifemenu.dto.SetleDto;
import com.lifemenu.dto.VoucherDto;

/*
 * 	Author 		: Lim
 * 
 *  Update Date : 2021-06-29
 * 	Version 	: 0.0.2
 * 
 * 	식사권, 예약, 결제, 환불 관리
 * 2021-06-09-0.0.1 	- 최초 작성
 * 2021-06-29-0.0.2 	- 연동 확인
 */

@Service("vochrMngrService")
public class VochrMngrServiceImpl implements IVochrMngrService {

	@Autowired
	private IVoucherDaoMebc voucherDaoMebc;
	@Autowired
	private IVochrMngrDaoQebc vochrMngrDaoQebc;
	
	private static final String VIEW_PAGE 			= "admin/vochr/vochrMngr";
	private static final String VIEW_DETAIL_PAGE 	= "admin/vochr/vochrDetail";
	
	
	@Override
	public String selectMultiVochr(Model model, PageParamVo pageParamVo) {
		
		List<VochrMngrDto> vochrList = vochrMngrDaoQebc.selectMultiVochr(pageParamVo);
		int totalCnt = vochrMngrDaoQebc.selectVochrMngrTotalCount();
		
		model.addAttribute("LIST", vochrList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("IS_MULTI", "Y");
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	@Override
	public String selectMultiVochrByCondition(Model model, ConditionParamVo conditionParamVo) {

		conditionParamVo.setCondition(AdminCommons.convertCondition(conditionParamVo.getCondition()));
		if("MBER_CODE".equals(conditionParamVo.getConditionType())) {
			conditionParamVo.setConditionType("V.MBER_CODE");
		}
		
		List<VochrMngrDto> vochrList = vochrMngrDaoQebc.selectMultiVochrByCondition(conditionParamVo);
		int totalCnt = vochrMngrDaoQebc.selectVochrMngrTotalCount();
		
		model.addAttribute("LIST", vochrList);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", conditionParamVo.getStart());
		model.addAttribute("CNT", conditionParamVo.getCnt());
		
		return VIEW_PAGE;
		
	}

	@Override
	public String selectMultiResveSetle(Model model, String vochrCode, PageParamVo pageParamVo) {

		VoucherDto voucherDto = voucherDaoMebc.selectOneVoucher(vochrCode);
		List<ResveDto> resveList = vochrMngrDaoQebc.selectMultiResve(vochrCode);
		List<SetleDto> setleList = vochrMngrDaoQebc.selectMultiSetle(vochrCode);
		int totalCnt = vochrMngrDaoQebc.selectResveSetleTotalCount();
		
		
		// resve와 setle는 1:1 관계이다.
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < resveList.size(); i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("RESVE", resveList.get(i));
			hm.put("SETLE", setleList.get(i));
			list.add(hm);
		}
		
		for (int i = 0; i < list.size(); i++) {
			ResveDto resve = (ResveDto)list.get(i).get("RESVE");
			System.out.println(resve.toString());
			SetleDto setle = (SetleDto)list.get(i).get("SETLE");
			System.out.println(setle.toString());
		}
		
		model.addAttribute("VOCHR", voucherDto);
//		model.addAttribute("RESVE_LIST", resveList);
//		model.addAttribute("SETLE_LIST", setleList);
		model.addAttribute("LIST", list);
		model.addAttribute("TOTAL_CNT", totalCnt);
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		
		return VIEW_DETAIL_PAGE;
		
	}

	@Override
	@Transactional
	public String blindVochr(Model model, String vochrCode, PageParamVo pageParamVo) throws Exception {

		// [방안1]
		// 식사권 삭제 절차
		// 1. 예약된 건이 있는 지 확인
		//   1.1. 예약 없을 때, 식사권 삭제 처리
		//   1.2. 예약 있을 때, 결제된 건이 있는 지 확인
		//     1.2.1. 결제 없을 때, 예약 취소 알림 발송 후, 예약 및 식사권 삭제 처리
		//     1.2.2. 결제 있을 때, 환불된 건이 있는 지 확인
		//       1.2.2.1. 환불 없을 때, 환불하고 예약 취소 알림 발송 후 , 결제 및 예약 및 식사권 삭제 처리
		//       1.2.2.2. 환불 있을 때, 환불 처리 완료 후, 1.2.2.1. 과정
		//       1.2.2.3. 환불한 경우 자동 결제 및 예약이 취소되므로 그 외의 경우, 예외처리
		// [방안2]
		// 거래 가능 여부 항목(DELNG_AT)을 update처리
		VoucherDto isVochr = voucherDaoMebc.selectOneVoucher(vochrCode);
		if (isVochr == null) {
			throw new Exception();
		}

		String msg;
		if('Y' == isVochr.getDelngAt()) {
			isVochr.setDelngAt('N'); // 거래 불가능으로 처리, [미구현]추가로 알림 발송할 것!
			msg = vochrCode + "식사권이 블라인드 처리 되었습니다";
		}
		else {
			isVochr.setDelngAt('Y'); // 거래 가능으로 처리, [미구현]추가로 알림 발송할 것!
			msg = vochrCode + "식사권이 블라인드 해제 되었습니다";
		}
		voucherDaoMebc.updateVoucher(isVochr); 
		model.addAttribute("MSG", msg);
		
		return "redirect:/vochrMngrMulti.do?start=" + pageParamVo.getStart() + "&cnt=" + pageParamVo.getCnt();
		
	}
		
}
