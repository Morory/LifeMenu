<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.1 -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin-menu-ver.1.0.6.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/column-width.css" rel="stylesheet" type="text/css">

<title>Lifemenu~administrator</title>

<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(function(){
	
	// jqurey 동작 확인용
	alert("jquery check!")

	// 필터 기본 설정(리스트 출력 제외 항목)
	var filterClickCnt 	= 0;
	$("#filter_contents").hide();
	$(".vochrCode").hide();
	$(".delngCode").hide();
	$(".resveSetleCode").hide();
	$(".mberCode").hide();
	$(".delngDt").hide();
	$(".delngSe").hide();
	$(".delngAmount").hide();
	$(".repositoryCoinCo").hide();
	$(".rcppayDt").hide();
	$(".rcppayCl").hide();
	$(".rcppayAmount").hide();
	$(".excngCoinCo").hide();
	$(".coinCo").hide();
	$(".vochrPrice").hide();
	$(".mealPrearngeDt").hide();
	$(".vochrRegistDt").hide();
	$(".mealTime").hide();
	$(".delngAt").hide();
	$(".resvePc").hide();
	$(".resveDt").hide();
	$(".setlePc").hide();
	$(".setleDt").hide();
	$(".setleAt").hide();
	$(".purchsDcsnAt").hide();
	$(".refndReqstAt").hide();
	$(".refndPc").hide();
	$(".refndDt").hide();
	$(".refndAt").hide();
	$(".refndResn").hide();
	
	// 필터로 원하는 컬럼값 출력
	// ----필터 on/off
	$("#filter").click(function(){
		if(filterClickCnt%2 == 0){
			$("#filter_contents").show();
			filterClickCnt++;
		}
		else{
			$("#filter_contents").hide();
			filterClickCnt++;
		}
	});
	// ----전체 on/off
	$("#chkAll").change(function(){
		if($("#chkAll").is(":checked")){
			$("#chkVochrHist").prop("checked", true);
			$("#chkResveSetleHist").prop("checked", true);
			$("#chkRefndHist").prop("checked", true);
			$("#chkWalletDtlsHist").prop("checked", true);
			$("#chkCoinRepositoryHist").prop("checked", true);
			$(".rcordDt").show();
			$(".histSe").show();
			$(".code").show();
			$(".history").show();
			$(".vochrCode").hide();
			$(".delngCode").hide();
			$(".resveSetleCode").hide();
			$(".mberCode").hide();
			$(".delngDt").hide();
			$(".delngSe").hide();
			$(".delngAmount").hide();
			$(".repositoryCoinCo").hide();
			$(".rcppayDt").hide();
			$(".rcppayCl").hide();
			$(".rcppayAmount").hide();
			$(".excngCoinCo").hide();
			$(".coinCo").hide();
			$(".vochrPrice").hide();
			$(".mealPrearngeDt").hide();
			$(".vochrRegistDt").hide();
			$(".mealTime").hide();
			$(".delngAt").hide();
			$(".resvePc").hide();
			$(".resveDt").hide();
			$(".setlePc").hide();
			$(".setleDt").hide();
			$(".setleAt").hide();
			$(".purchsDcsnAt").hide();
			$(".refndReqstAt").hide();
			$(".refndPc").hide();
			$(".refndDt").hide();
			$(".refndAt").hide();
			$(".refndResn").hide();
		}
		else {
			$(".l_column").hide();
			$("#chkVochrHist").prop("checked", false);
			$("#chkResveSetleHist").prop("checked", false);
			$("#chkRefndHist").prop("checked", false);
			$("#chkWalletDtlsHist").prop("checked", false);
			$("#chkCoinRepositoryHist").prop("checked", false);
		}
	});
	// ----식사권이력 on/off
	$("#chkVochrHist").change(function(){
		if($("#chkVochrHist").is(":checked")){
			$(".vochrCode").show();
			$(".mberCode").show();
			$(".vochrPrice").show();
			$(".mealPrearngeDt").show();
			$(".vochrRegistDt").show();
			$(".mealTime").show();
			$(".delngAt").show();
		}
		else {
			$(".vochrCode").hide();
			$(".mberCode").hide();
			$(".vochrPrice").hide();
			$(".mealPrearngeDt").hide();
			$(".vochrRegistDt").hide();
			$(".mealTime").hide();
			$(".delngAt").hide();
		}
	});
	// ----예약 및 결제이력 on/off
	$("#chkResveSetleHist").change(function(){
		if($("#chkResveSetleHist").is(":checked")){
			$(".resveSetleCode").show();
			$(".mberCode").show();
			$(".vochrCode").show();
			$(".resvePc").show();
			$(".resveDt").show();
			$(".setlePc").show();
			$(".setleDt").show();
			$(".setleAt").show();
			$(".refndReqstAt").show();
			$(".purchsDcsnAt").show();
		}
		else {
			$(".resveSetleCode").hide();
			$(".mberCode").hide();
			$(".vochrCode").hide();
			$(".resvePc").hide();
			$(".resveDt").hide();
			$(".setlePc").hide();
			$(".setleDt").hide();
			$(".setleAt").hide();
			$(".refndReqstAt").hide();
			$(".purchsDcsnAt").hide();
		}
	});
	// ----환불이력 on/off
	$("#chkRefndHist").change(function(){
		if($("#chkRefndHist").is(":checked")){
			$(".setleCode").show();
			$(".refndPc").show();
			$(".refndDt").show();
			$(".refndAt").show();
			$(".refndResn").show();
		}
		else {
			$(".setleCode").hide();
			$(".refndPc").hide();
			$(".refndDt").hide();
			$(".refndAt").hide();
			$(".refndResn").hide();
		}
	});
	// ----지갑내역 on/off
	$("#chkWalletDtlsHist").change(function(){
		if($("#chkWalletDtlsHist").is(":checked")){
			$(".mberCode").show();
			$(".rcppayDt").show();
			$(".rcppayCl").show();
			$(".rcppayAmount").show();
			$(".excngCoinCo").show();
			$(".coinCo").show();
		}
		else {
			$(".mberCode").hide();
			$(".rcppayDt").hide();
			$(".rcppayCl").hide();
			$(".rcppayAmount").hide();
			$(".excngCoinCo").hide();
			$(".coinCo").hide();
		}
	});
	// ----코인저장소이력 on/off
	$("#chkCoinRepositoryHist").change(function(){
		if($("#chkCoinRepositoryHist").is(":checked")){
			$(".delngCode").show();
			$(".mberCode").show();
			$(".setleCode").show();
			$(".delngDt").show();
			$(".delngSe").show();
			$(".delngAmount").show();
			$(".repositoryCoinCo").show();
		}
		else {
			$(".delngCode").hide();
			$(".mberCode").hide();
			$(".setleCode").hide();
			$(".delngDt").hide();
			$(".delngSe").hide();
			$(".delngAmount").hide();
			$(".repositoryCoinCo").hide();
		}
	});
	
	// ----도움말 보기 on/off
	var helpClickCnt 	= 0;
	$(".help").hide();
	$("#helpBtn").click(function(){
		if(helpClickCnt%2 == 0){
			$(".help").show();
			helpClickCnt++;
		}
		else{
			$(".help").hide();
			helpClickCnt++;
		}
	});
	
	
	/* 페이지 처리 */
	
	var total 	= "<c:out value='${ TOTAL_CNT }'/>";
	var isMulti = "<c:out value='${ IS_MULTI }'/>";
	var start 	= "<c:out value='${ START }'/>";
	var cnt 	= "<c:out value='${ CNT }'/>";
	
	var pageSize 	= cnt;	// 한 페이지에 출력할 갯수
	var pageCnt 	= 10;	// 출력할 페이지 갯수
	var firstNo 	= 1;
	var startNo 	= start - ( start%(pageSize*pageCnt) ) + 1;
	var lastNo 		= total - ( total%(pageSize*pageCnt) ) + 1;
	var pageNo 		= (Number(startNo)-1)/Number(pageSize) + 1;
	var lastPageNo 	= total/pageCnt + ( ( total%pageCnt > 0 )? 1 : 0 );
	var mapping 	= (isMulti == "Y")? "mberMngrMulti.do" : "mberMngrCondition.do";
	
	var beforeHtml = "<a class='pageBtn' href='" + mapping + "?start=" + firstNo + "&cnt=" + cnt + "'>[첫페이지]</a>";
	$('#first_pages').html(beforeHtml);
	if(pageNo != 1){
		var beforeHtml = "<a class='pageBtn' href='" + mapping + "?start=" + (Number(startNo) - Number(pageSize*pageCnt)) + "&cnt=" + cnt + "'>[이전]</a>";
		$('#before_pages').html(beforeHtml);
	}
	$('#view_pages').html("");
	for(var i = 1; i <= pageCnt && pageNo < lastPageNo; i++){
		var pageHtml = "<a class='pageBtn' href='" + mapping + "?start=" + startNo + "&cnt=" + cnt + "'>&nbsp;" + pageNo + "</a>";
		$('#view_pages').append(pageHtml);
		startNo = Number(startNo) + Number(cnt);
		pageNo = Number(pageNo) + 1;
	}
	if(pageNo < lastPageNo){
		var nextHtml = "<a class='pageBtn' href='" + mapping + "?start=" + Number(startNo) + "&cnt=" + cnt + "'>&nbsp;[다음]</a>";
		$('#next_pages').html(nextHtml);
	}
	var lastHtml = "<a class='pageBtn' href='" + mapping + "?start=" + lastNo + "&cnt=" + cnt + "'>[끝페이지]</a>";
	$('#last_pages').html(lastHtml);
	
	/* function 마지막 */
});

</script>

</head>
<body>
<div id="frameOut">
    <div id="frameIn">
        <div class="sub_logo">
			<a href="adminMain.do"><img src="${path}/resources/img/logoB.png" class="mlogo" alt=""></a>
		</div>
		<header id="header">
			<jsp:include page="${request.contextPath}/header"></jsp:include>
		</header>
        <div id="content">
            <form action="histMngrCondition.do">
                <div class="box-wrap">
            	    <div class="title box">
            		    <h2>히스토리 관리</h2>
            	    </div>
            	    <div class="search box">
            	        <div class="home s_box">
	            			<a href="adminMain.do">홈으로</a>
	            		</div>
            		    <div class="combo s_box">
            			    <select name="conditionType">
            				    <option value="" selected="selected">히스토리 일시</option>
            			    </select>
            		    </div>
            		    <div class="search_text s_box">
            			    <input type="text" name="condition" value="" placeholder="@YYYYMMDDHHMISS-YYYYMMDDHHMISS" size="80">
            		    </div>
            		    <div class="search_btn s_box">
            			    <input type="submit" value="검색">
            			    <input type="hidden" name="start" value="1">
            			    <input type="hidden" name="cnt" value="8">
            		    </div>
            		    <div class="search_option s_box">
							<input type="button" id="filter" value="필터">
						</div>
						<div id="filter_contents">
							<div>
								<input type="checkbox" id="chkAll" checked>전체 이력 
								<input type="checkbox" id="chkVochrHist" checked>식사권 이력 
								<input type="checkbox" id="chkResveSetleHist" checked>예약 및 결제 이력 
								<input type="checkbox" id="chkRefndHist" checked>환불 
								<input type="checkbox" id="chkWalletDtlsHist" checked>지갑내역 이력 
								<input type="checkbox" id="chkCoinRepositoryHist" checked>코인 저장소 이력 
							</div>
						</div>
            		    <div class="help_btn s_box">
            			    <input type="button" id="helpBtn" value="도움말">
            		    </div>
            	    </div>
            	    <div class="help box">
            	        <p>
            	            'YYYYMMDDHHMISS'로 검색시 해당 날짜의 히스토리만 검색됩니다.<br>
            	            'YYYYMMDDHHMISS-YYYYMMDDHHMISS'로 검색시 해당 범위의 히스토리가 검색됩니다.
            	        </p>
	            	</div>
            	    <div class="list box">
            		    <div class="l_title l_box">
            			    <div class="rcordDt l_column">히스토리 등록일시</div>
            			    <div class="histSe l_column">히스토리 구분</div>
            			    <div class="code l_column">전체코드</div>
            			    <div class="history l_column">히스토리</div>
            			    <div class="vochrCode l_column">식사권코드</div>
            			    <div class="delngCode l_column">거래 코드</div>
            			    <div class="resveSetleCode l_column">예약결제코드</div>
            			    <div class="mberCode l_column">회원코드</div>
            			    <div class="delngDt l_column">거래 일시</div>
            			    <div class="delngSe l_column">거래 분류</div>
            			    <div class="delngAmount l_column">거래 금액</div>
            			    <div class="repositoryCoinCo l_column">저장 코인 수</div>
            			    <div class="rcppayDt l_column">입출금 일자</div>
            			    <div class="rcppayCl l_column">입출금 분류</div>
            			    <div class="rcppayAmount l_column">입출금 금액</div>
            			    <div class="excngCoinCo l_column">환전 코인 수</div>
            			    <div class="coinCo l_column">코인 수</div>
            			    <div class="vochrPrice l_column">식사권 가격</div>
            			    <div class="mealPrearngeDt l_column">식사 예정 일시</div>
            			    <div class="vochrRegistDt l_column">식사권 등록 일시</div>
            			    <div class="mealTime l_column">식사 시간</div>
            			    <div class="delngAt l_column">거래 가능 여부</div>
            			    <div class="resvePc l_column">예약 가격</div>
            			    <div class="resveDt l_column">예약 일시</div>
            			    <div class="setlePc l_column">결제 가격</div>
            			    <div class="setleDt l_column">결제 일시</div>
            			    <div class="setleAt l_column">결제 완료 여부</div>
            			    <div class="purchsDcsnAt l_column">구매 확정 여부</div>
            			    <div class="refndReqstAt l_column">환불 신청 여부</div>
            			    <div class="refndPc l_column">환불 가격</div>
            			    <div class="refndDt l_column">환불 일시</div>
            			    <div class="refndAt l_column">환불 완료 여부</div>
            			    <div class="refndResn l_column">환불 사유</div>
            		    </div>
            		    <c:if test="${ fn:length(LIST) == 0 }">
	            		    <div class="l_row l_box">
	            		        <div class="no_data">조회된 데이터가 없습니다</div>
	            		    </div>
	            		</c:if>
            		    <c:forEach var="hist" items="${ LIST }" varStatus="status">
            		        <div class="l_row l_box">
            			        <div class="rcordDt l_column">${hist.rcordDt}</div>
            			        <div class="histSe l_column">${hist.histSeTable}-${hist.histSeExec}</div>
            			        <div class="code l_column">${hist.seCode}</div>
            			        <div class="history l_column">${hist.histBrief}</div>
            			        <div class="vochrCode l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.vochrCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.vochrCode }
            			            </c:if>
            			        </div>
            			        <div class="delngCode l_column">
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.delngCode }
            			            </c:if>
            			        </div>
            			        <div class="resveSetleCode l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.resveSetleCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.setleCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'REFND' }">
            			                ${hist.contents.setleCode }
            			            </c:if>
            			        </div>
            			        <div class="mberCode l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.mberCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.mberCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.mberCode }
            			            </c:if>
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.mberCode }
            			            </c:if>
            			        </div>
            			        <div class="delngDt l_column">
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.delngDt }
            			            </c:if>
            			        </div>
            			        <div class="delngSe l_column">
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.delngSe }
            			            </c:if>
            			        </div>
            			        <div class="delngAmount l_column">
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.delngAmount }
            			            </c:if>
            			        </div>
            			        <div class="repositoryCoinCo l_column">
            			            <c:if test="${hist.histSeTable == 'COIN_REPOSITORY' }">
            			                ${hist.contents.repositoryCoinCo }
            			            </c:if>
            			        </div>
            			        <div class="rcppayDt l_column">
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.rcppayDt }
            			            </c:if>
            			        </div>
            			        <div class="rcppayCl l_column">
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.rcppayCl }
            			            </c:if>
            			        </div>
            			        <div class="rcppayAmount l_column">
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.rcppayAmount }
            			            </c:if>
            			        </div>
            			        <div class="excngCoinCo l_column">
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.excngCoinCo }
            			            </c:if>
            			        </div>
            			        <div class="coinCo l_column">
            			            <c:if test="${hist.histSeTable == 'WALLET_DTLS' }">
            			                ${hist.contents.coinCo }
            			            </c:if>
            			        </div>
            			        <div class="vochrPrice l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.vochrPrice }
            			            </c:if>
            			        </div>
            			        <div class="mealPrearngeDt l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.mealPrearngeDt }
            			            </c:if>
            			        </div>
            			        <div class="vochrRegistDt l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.vochrRegistDt }
            			            </c:if>
            			        </div>
            			        <div class="mealTime l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.mealTime }
            			            </c:if>
            			        </div>
            			        <div class="delngAt l_column">
            			            <c:if test="${hist.histSeTable == 'VOCHR' }">
            			                ${hist.contents.delngAt }
            			            </c:if>
            			        </div>
            			        <div class="resvePc l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.resvePc }
            			            </c:if>
            			        </div>
            			        <div class="resveDt l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.resveDt }
            			            </c:if>
            			        </div>
            			        <div class="setlePc l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.setlePc }
            			            </c:if>
            			        </div>
            			        <div class="setleDt l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.setleDt }
            			            </c:if>
            			        </div>
            			        <div class="setleAt l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.setleDt }
            			            </c:if>
            			        </div>
            			        <div class="purchsDcsnAt l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.purchsDcsnAt }
            			            </c:if>
            			        </div>
            			        <div class="refndReqstAt l_column">
            			            <c:if test="${hist.histSeTable == 'RESVE_SETLE' }">
            			                ${hist.contents.refndReqstAt }
            			            </c:if>
            			        </div>
            			        <div class="refndPc l_column">
            			            <c:if test="${hist.histSeTable == 'REFND' }">
            			                ${hist.contents.refndPc }
            			            </c:if>
            			        </div>
            			        <div class="refndDt l_column">
            			            <c:if test="${hist.histSeTable == 'REFND' }">
            			                ${hist.contents.refndDt }
            			            </c:if>
            			        </div>
            			        <div class="refndAt l_column">
            			            <c:if test="${hist.histSeTable == 'REFND' }">
            			                ${hist.contents.refndAt }
            			            </c:if>
            			        </div>
            			        <div class="refndResn l_column">
            			            <c:if test="${hist.histSeTable == 'REFND' }">
            			                ${hist.contents.refndResn }
            			            </c:if>
            			        </div>
            		        </div>
            		    </c:forEach>
            		    <div class="page_no l_box">
							<div id="before_pages"></div>
							<div id="view_pages"></div>
							<div id="next_pages"></div>
						</div>
            	    </div>
                </div>
				<%-- end box-wrap --%>
            </form>
        </div>
        <footer id="footer">
            <div class="otc">
                <a href="#">회사소개</a>
                <a href="#">인재채용</a>
                <a href="#">제휴제안</a>
                <a href="#">이용약관</a>
                <a href="#">개인정보처리방침</a>
                <a href="#">고객센터</a>
                <a href="#">@LM Corp.</a>
            </div>
        </footer>
    </div>
</div>
</body>
</html>