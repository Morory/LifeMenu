package com.lifemenu.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lifemenu.admin.vo.PageParamVo;
import com.lifemenu.dao.mebc.IBbscttDaoMebc;
import com.lifemenu.dao.qebc.IBbscttDaoQebc;
import com.lifemenu.dto.BbscttDto;
import com.lifemenu.dto.MberDto;

@Service("bbscttService")
public class BbscttServiceImpl implements IBbscttService {

	@Autowired
	private IBbscttDaoMebc bbscttDaoMebc;
	@Autowired
	private IBbscttDaoQebc bbscttDaoQebc;
	
//	private static final String VIEW_PAGE 			= "bbsctt/bbscttList";
//	private static final String VIEW_DETAIL_PAGE 	= "bbsctt/bbscttDetail";
//	private static final int 	DEFAULT_START 		= 1;
//	private static final int 	DEFAULT_CNT 		= 8;
	
	// 첫 화면 및 전체 공지사항 목록 조회
	@Override
	public String selectMultiBbsctt(Model model, PageParamVo pageParamVo) {

		List<BbscttDto> bbscttList = bbscttDaoQebc.selectMultiBbsctt(pageParamVo);
		int totalCnt = bbscttDaoQebc.selectBbsTotalCount();
		
		model.addAttribute("START", pageParamVo.getStart());
		model.addAttribute("CNT", pageParamVo.getCnt());
		model.addAttribute("TNT", totalCnt);
		model.addAttribute("IS_MULTI", "Y");
		model.addAttribute("LIST", bbscttList);
		
		return "bbsctt/bbscttList";
		
	}
	
	// 게시글 상세 조회
		@Override	
		public String selectOneBbsctt(Model model, HttpSession session, String bbscttNo) {
			
			BbscttDto bbscttDto = bbscttDaoMebc.selectOneBbsctt(bbscttNo);
			MberDto ssMberDto = (MberDto) session.getAttribute("dto");
	    	String upNo = bbscttNo;
	    	
	    	List<BbscttDto> list = bbscttDaoQebc.selectMultiComent(upNo);
			
	    	model.addAttribute("COMENT_LIST", list);
			model.addAttribute("BBSCTT", bbscttDto);
			model.addAttribute("SS_MBER_DTO", ssMberDto);
//			model.addAttribute("START", pageParamVo.getStart());
//			model.addAttribute("CNT", pageParamVo.getCnt());
			
			return "bbsctt/bbscttDetail";
			
		}
		
		//수정 
		@Override
		@Transactional
		public String bbscttUpdate(Model model, BbscttDto bbscttDto) throws Exception {

			BbscttDto isBbsctt = bbscttDaoMebc.selectOneBbsctt(bbscttDto.getBbscttNo());
			if (isBbsctt == null) {
				throw new Exception();
			}
			
			bbscttDaoMebc.bbscttUpdate(bbscttDto);
			
			return "redirect:/bbscttDetail.do?bbscttNo=" + bbscttDto.getBbscttNo();
			
		}
		
		//새글 등록
		@Override
		public String insertBbsctt(Model model, HttpSession session, BbscttDto bbDto) {
			
			MberDto ssMberDto = (MberDto) session.getAttribute("dto");
			
			BbscttDto bbscttDto = bbscttDaoQebc.selectOneBbscttNoRownum();
			
			int bbNo = Integer.parseInt(bbscttDto.getBbscttNo()) + 1;
			String strBbNo = String.format("%04d", bbNo);
			bbDto.setBbscttNo(strBbNo);
			bbDto.setMberCode(ssMberDto.getMberCode());
			
			//새글등록
			bbscttDaoMebc.insertBbsctt(bbDto);
			
			return "redirect:/bbscttMulti.do?start=1&cnt=8";
		}
		
		//답급등록
		@Override
		public String insertComent(Model model, HttpSession session, BbscttDto bbscDto, String bbscttNo) {
			
			MberDto ssMberDto = (MberDto) session.getAttribute("dto");
			
			BbscttDto bbscttDto = bbscttDaoQebc.selectOneBbscttNoRownum();
			
			int bbNo = Integer.parseInt(bbscttDto.getBbscttNo()) + 1;
			String strBbNo = String.format("%04d", bbNo);

			bbscDto.setBbscttNo(strBbNo);
			bbscDto.setMberCode(ssMberDto.getMberCode());
			bbscDto.setUpBbscttNo(bbscttNo);
			
			//답글등록
			bbscttDaoMebc.insertComent(bbscDto);
			
			model.addAttribute("COMENT", bbscDto);
			
			return "redirect:/bbscttDetail.do?BBSCTT_NO=" + bbscttNo;
		}
}
