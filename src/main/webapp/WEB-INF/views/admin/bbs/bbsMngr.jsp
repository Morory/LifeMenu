<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.4. -->

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
            <form action="bbsMngrCondition.do" method="POST">
	            <div class="box-wrap">
	            	<div class="title box">
	            		<h2>게시판 관리</h2>
	            	</div>
	            	<div class="search box">
	            	    <div class="home s_box">
	            			<a href="adminMain.do">홈으로</a>
	            		</div>
	            		<div class="combo s_box">
	            			<select name="conditionType">
	            			    <option value="BBSCTT_NO">게시글번호</option>
	            				<option value="MBER_CODE" selected="selected">회원코드</option>
	            				<option value="MBER_NCNM">닉네임</option>
	            				<option value="MBER_RLNM">실명</option>
	            				<option value="MBER_EMAIL">이메일</option>
	            				<option value="BBSCTT_SJ">제목</option>
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
	            	</div>
	            	<div class="list box">
	            		<div class="l_title l_box">
	            			<div class="bbscttNo l_column">게시글번호</div>
	            			<div class="mberCode l_column">회원코드</div>
	            			<div class="mberNcnm l_column">닉네임</div>
	            			<div class="mberRlnm l_column">실명</div>
	            			<div class="mberEmail l_column">이메일</div>
	            			<div class="bbscttSj l_column">제목</div>
	            			<div class="bbscttDt l_column">작성일시</div>
	            			<div class="etc l_column">비고</div>
	            		</div>
	            		<c:if test="${ fn:length(LIST) == 0 }">
	            		    <div class="l_row l_box">
	            		        <div class="no_data">조회된 데이터가 없습니다</div>
	            		    </div>
	            		</c:if>
	            		<c:forEach var="bbsMngr" items="${ LIST }">
	            			<div class="l_row l_box">
	            			    <div class="bbscttNo l_column">${ bbsMngr.bbscttDto.bbscttNo }</div>
	            			    <div class="mberCode l_column">${ bbsMngr.bbscttDto.mberCode }</div>
	            			    <div class="mberNcnm l_column">
	            			        <c:set var="textMberNcnm" value="${ bbsMngr.mberNcnm }" />
	            			        <c:if test="${ fn:length(textMberNcnm) > 10 }">
	            			            ${ fn:substring(textMberNcnm, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberNcnm) <= 10 }">
	            			            ${ bbsMngr.mberNcnm }
	            			        </c:if>
	            			    </div>
	            			    <div class="mberRlnm l_column">
	            			        <c:set var="textMberRlnm" value="${ bbsMngr.mberRlnm }" />
	            			        <c:if test="${ fn:length(textMberRlnm) > 10 }">
	            			            ${ fn:substring(textMberRlnm, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberRlnm) <= 10 }">
	            			            ${ bbsMngr.mberRlnm }
	            			        </c:if>
	            			    </div>
	            			    <div class="mberEmail l_column">
	            			        <c:set var="textMberEmail" value="${ bbsMngr.mberEmail }" />
	            			        <c:if test="${ fn:length(textMberEmail) > 10 }">
	            			            ${ fn:substring(textMberEmail, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textMberEmail) <= 10 }">
	            			            ${ bbsMngr.mberEmail }
	            			        </c:if>
	            			    </div>
	            			    <div class="bbscttSj l_column">
	            			        <c:set var="textBbscttSj" value="${ bbsMngr.bbscttDto.bbscttSj }" />
	            			        <c:if test="${ fn:length(textBbscttSj) > 10 }">
	            			            ${ fn:substring(textBbscttSj, 0, 8) }..
	            			        </c:if>
	            			        <c:if test="${ fn:length(textBbscttSj) <= 10 }">
	            			            ${ bbsMngr.bbscttDto.bbscttSj }
	            			        </c:if>
	            			    </div>
	            			    <div class="bbscttDt l_column"><fmt:formatDate value="${ bbsMngr.bbscttDto.bbscttDt }" pattern="yyyy-MM-dd"/></div>
	            			    <div class="etc l_column">
	            			        <a href="bbsMngrDetail.do?bbscttNo=${ bbsMngr.bbscttDto.bbscttNo }&start=${ START }&cnt=${ CNT }">상세보기</a>
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
            </form>
        </div>
        <footer id="footer">
			<jsp:include page="${request.contextPath}/footer"></jsp:include>
        </footer>
    </div>
</div>
</body>
</html>