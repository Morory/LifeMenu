<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.3 -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin-menu-ver.1.0.5.css?ver=1" rel="stylesheet" type="text/css">

<title>Lifemenu~administrator</title>

<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(function(){
	
	//회원의 정보 수정 및 삭제(탈퇴) 처리가 되면 메시지 출력
	var msg = "<c:out value='${ MSG }'/>";
	if (msg) {
		alert(msg);
	}
	
	var start = "<c:out value='${ START }'/>";
	var cnt = "<c:out value='${ CNT }'/>";
	
	$("#registBtn").click(function(){
		document.location.href="insertNoticeForm.do?start=" + start + "&cnt=" + cnt;
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
	var mapping 	= (isMulti == "Y")? "noticeMngrMulti.do?" : "noticeMngrCondition.do?";
	
	var beforeHtml = "<a class='pageBtn' href='" + mapping + "start=" + firstNo + "&cnt=" + cnt + "'>[첫페이지]</a>";
	$('#first_pages').html(beforeHtml);
	if(pageNo != 1){
		var beforeHtml = "<a class='pageBtn' href='" + mapping + "start=" + (Number(startNo) - Number(pageSize*pageCnt)) + "&cnt=" + cnt + "'>[이전]</a>";
		$('#before_pages').html(beforeHtml);
	}
	$('#view_pages').html("");
	for(var i = 1; i <= pageCnt && pageNo < lastPageNo; i++){
		var pageHtml = "<a class='pageBtn' href='" + mapping + "start=" + startNo + "&cnt=" + cnt + "'>&nbsp;" + pageNo + "</a>";
		$('#view_pages').append(pageHtml);
		startNo = Number(startNo) + Number(cnt);
		pageNo = Number(pageNo) + 1;
	}
	if(pageNo < lastPageNo){
		var nextHtml = "<a class='pageBtn' href='" + mapping + "start=" + Number(startNo) + "&cnt=" + cnt + "'>&nbsp;[다음]</a>";
		$('#next_pages').html(nextHtml);
	}
	var lastHtml = "<a class='pageBtn' href='" + mapping + "start=" + lastNo + "&cnt=" + cnt + "'>[끝페이지]</a>";
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
            <form action="noticeMngrCondition.do" method="POST">
	            <div class="box-wrap">
	            	<div class="title box">
	            		<h2>공지사항 관리</h2>
	            	</div>
	            	<div class="search box">
	            		<div class="home s_box">
	            			<a href="adminMain.do">홈으로</a>
	            		</div>
	            		<div class="combo s_box">
	            			<select name="conditionType">
	            				<option value="BBSCTT_SJ" selected="selected">제목</option>
	            				<option value="BBSCTT_CN">내용</option>
	            				<option value="BBSCTT_SJ_CN">제목+내용</option>
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
	            		<div class="regist s_box">
	            			<input id="registBtn" type="button" value="등록">
	            		</div>
	            	</div>
	            	<div class="list box">
	            		<div class="l_title l_box">
	            			<div class="bbscttNo l_column">게시글 번호</div>
	            			<div class="mberCode l_column">작성자 번호</div>
	            			<div class="mberNcnm l_column">닉네임</div>
	            			<div class="bbscttSj l_column">제목</div>
	            			<div class="bbscttDt l_column">작성일시</div>
	            			<div class="etc l_column">비고</div>
	            		</div>
	            		<c:if test="${ fn:length(LIST) == 0 }">
	            		    <div class="l_row l_box">
	            		        <div class="no_data">조회된 데이터가 없습니다</div>
	            		    </div>
	            		</c:if>
	            		<c:forEach var="noticeMngr" items="${ LIST }">
	            			<div class="l_row l_box">
	            			    <div class="bbscttNo l_column">${ noticeMngr.bbscttNo }</div>
	            			    <div class="mberCode l_column">${ noticeMngr.mberCode }</div>
	            			    <div class="mberNcnm l_column">
	            			        <c:set var="textMberNcnm" value="${ noticeMngr.mberNcnm }" />
	            			        <c:if test="${ fn:length(textMberNcnm) > 10 }">
	            			            ${ fn:substring(textMberNcnm, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberNcnm) <= 10 }">
	            			            ${ noticeMngr.mberNcnm }
	            			        </c:if>
	            			    </div>
	            			    <div class="bbscttSj l_column">
	            			        <c:set var="textBbscttSj" value="${ noticeMngr.bbscttSj }" />
	            			        <c:if test="${ fn:length(textBbscttSj) > 10 }">
	            			            ${ fn:substring(textBbscttSj, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textBbscttSj) <= 10 }">
	            			            ${ noticeMngr.bbscttSj }
	            			        </c:if>
	            			    </div>
	            			    <div class="bbscttDt l_column"><fmt:formatDate value="${ noticeMngr.bbscttDt }" pattern="yyyy-MM-dd"/></div>
	            			    <div class="etc l_column">
	            			        <a href="noticeMngrDetail.do?bbscttNo=${ noticeMngr.bbscttNo }&start=${ START }&cnt=${ CNT }">상세보기</a>
	            			    </div>
	            		    </div>
	            		</c:forEach>
	            		<div class="page_no l_box">
							<div id="first_pages" class="pages"></div>
							<div id="before_pages" class="pages"></div>
							<div id="view_pages" class="pages"></div>
							<div id="next_pages" class="pages"></div>
							<div id="last_pages" class="pages"></div>
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