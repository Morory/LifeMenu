<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.2. -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin-menu-ver.1.0.5.css" rel="stylesheet" type="text/css">

<title>Lifemenu~administrator</title>

<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(function(){
	
	//회원의 정보 수정 및 삭제(탈퇴) 처리가 되면 메시지 출력
	var msg = "<c:out value='${ MSG }'/>";
	if (msg) {
		alert(msg);
	}
	
	
	/* 페이지 처리 */
	
	var total 	= "<c:out value='${ TOTAL_CNT }'/>";
	var start 	= "<c:out value='${ START }'/>";
	var cnt 	= "<c:out value='${ CNT }'/>";
	
	var pageSize 	= cnt;	// 한 페이지에 출력할 갯수
	var pageCnt 	= 10;	// 출력할 페이지 갯수
	var firstNo 	= 1;
	var startNo 	= start - ( start%(pageSize*pageCnt) ) + 1;
	var lastNo 		= total - ( total%(pageSize*pageCnt) ) + 1;
	var pageNo 		= (Number(startNo)-1)/Number(pageSize) + 1;
	
	if(pageNo != 1){
		var beforeHtml = "<a class='pageBtn' href='mngrInqryMulti.do?start=" + firstNo + "&cnt=" + cnt + "'>[첫페이지]</a>";
		$('#before_pages').html(beforeHtml);
	}
	$('#view_pages').html("");
	for(var i = 1; i <= pageCnt && pageNo < lastNo; i++){
		var pageHtml = "<a class='pageBtn' href='mngrInqryMulti.do?start=" + startNo + "&cnt=" + cnt + "'>&nbsp;" + pageNo + "</a>";
		$('#view_pages').append(pageHtml);
		startNo = Number(startNo) + Number(cnt);
		pageNo = Number(pageNo) + 1;
	}
	if(pageNo < lastNo){
		var nextHtml = "<a class='pageBtn' href='mngrInqryMulti.do?start=" + Number(startNo) + "&cnt=" + cnt + "'>&nbsp;[다음페이지]</a>";
		$('#next_pages').html(nextHtml);
	}
	
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
            <form action="mngrInqryCondition.do" method="POST">
	            <div class="box-wrap">
	            	<div class="title box">
	            		<h2>관리자문의</h2>
	            	</div>
	            	<div class="search box">
	            		<div class="home s_box">
	            			<a href="adminMain.do">홈으로</a>
	            		</div>
	            		<div class="combo s_box">
	            			<select name="conditionType">
	            				<option value="MBER_CODE" selected="selected">회원코드</option>
								<option value="MBER_NCNM">닉네임</option>
								<option value="MBER_RLNM">실명</option>
								<option value="MBER_EMAIL">이메일</option>
	            				<option value="INQRY_SJ">제목</option>
	            			</select>
	            		</div>
	            		<div class="search_text s_box">
	            			<input type="text" name="condition" value="" placeholder="@검색어를 입력하세요" size="80">
	            		</div>
	            		<div class="search_btn s_box">
	            			<input type="submit" value="검색"> 
							<input type="hidden" name="start" value="1"> 
							<input type="hidden" name="cnt" value="8">
	            		</div>
	            	</div>
	            	<div class="list box">
	            		<div class="l_title l_box">
	            			<div class="mngrInqryNo l_column">문의번호</div>
	            			<div class="mberCode l_column">회원코드</div>
	            			<div class="mberNcnm l_column">닉네임</div>
	            			<div class="mberRlnm l_column">실명</div>
	            			<div class="mberEmail l_column">이메일</div>
	            			<div class="inqrySj l_column">제목</div>
	            			<div class="inqryDt l_column">작성일시</div>
	            			<div class="etc l_column">비고</div>
	            		</div>
	            		<c:if test="${ fn:length(LIST) == 0 }">
	            		    <div class="l_row l_box">
	            		        <div class="no_data">조회된 데이터가 없습니다</div>
	            		    </div>
	            		</c:if>
	            		<c:forEach var="inqryMngr" items="${ LIST }">
	            			<div class="l_row l_box">
	            			    <div class="mngrInqryNo l_column">${ inqryMngr.mngrInqryDto.mngrInqryNo }</div>
	            			    <div class="mberCode l_column">${ inqryMngr.mngrInqryDto.mberCode }</div>
	            			    <div class="mberNcnm l_column">
	            			        <c:set var="textMberNcnm" value="${ inqryMngr.mberNcnm }" />
	            			        <c:if test="${ fn:length(textMberNcnm) > 10 }">
	            			            ${ fn:substring(textMberNcnm, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberNcnm) <= 10 }">
	            			            ${ inqryMngr.mberNcnm }
	            			        </c:if>
	            			    </div>
	            			    <div class="mberRlnm l_column">
	            			        <c:set var="textMberRlnm" value="${ inqryMngr.mberRlnm }" />
	            			        <c:if test="${ fn:length(textMberRlnm) > 10 }">
	            			            ${ fn:substring(inqryMngr.mberRlnm, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberRlnm) <= 10 }">
	            			            ${ inqryMngr.mberRlnm }
	            			        </c:if>
	            			    </div>
	            			    <div class="mberEmail l_column">
	            			        <c:set var="textMberEmail" value="${ inqryMngr.mberEmail }" />
	            			        <c:if test="${ fn:length(textMberEmail) > 10 }">
	            			            ${ fn:substring(inqryMngr.mberEmail, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberEmail) <= 10 }">
	            			            ${ inqryMngr.mberEmail }
	            			        </c:if>
	            			    </div>
	            			    <div class="inqrySj l_column">
	            			        <c:set var="textInqrySj" value="${ inqryMngr.mngrInqryDto.inqrySj }" />
	            			        <c:if test="${ fn:length(textInqrySj) > 10 }">
	            			            ${ fn:substring(textInqrySj, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textInqrySj) <= 10 }">
	            			            ${ inqryMngr.mngrInqryDto.inqrySj }
	            			        </c:if>
	            			    </div>
	            			    <div class="inqryDt l_column"><fmt:formatDate value="${ inqryMngr.mngrInqryDto.inqryDt }" pattern="yyyy-MM-dd"/></div>
	            			    <div class="etc2 l_column">
	            			        <a href="mngrInqryDetail.do?mngrInqryNo=${ inqryMngr.mngrInqryDto.mngrInqryNo }&start=${ START }&cnt=${ CNT }">상세보기</a>
	            			        <c:if test="${ inqryMngr.mngrInqryDto.mngrAnswerNo == null}">
	            			            <a href="insertMngrInqryForm.do?mngrInqryNo=${ inqryMngr.mngrInqryDto.mngrInqryNo }&start=${ START }&cnt=${ CNT }">답변등록</a>
	            			        </c:if>
	            			        <c:if test="${ inqryMngr.mngrInqryDto.mngrAnswerNo != null }">
	            			            <a href="mngrInqryDetail.do?mngrInqryNo=${ inqryMngr.mngrInqryDto.mngrAnswerNo }&start=${ START }&cnt=${ CNT }&isAnswer=y">답변보기</a>
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
			<jsp:include page="${request.contextPath}/footer"></jsp:include>
        </footer>
    </div>
</div>
</body>
</html>