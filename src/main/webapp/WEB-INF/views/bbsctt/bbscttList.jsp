<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}" />
<link href="${path}/resources/css/common.css" rel="stylesheet"
	type="text/css">
<link href="${path}/resources/css/transactionList.css?v=3"
	rel="stylesheet" type="text/css">
<link href="${path}/resources/css/bbsctt.css" rel="stylesheet"
	type="text/css">
<link href="${path}/resources/css/bbsctt" rel="stylesheet"
	type="text/css">
<!--  jQuery Framework 참조하기 -->
<script type="text/javascript"
	src="${path}/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/logheader.js"></script>
<script src="https://kit.fontawesome.com/9691fcfe8c.js"></script>
<script type="text/javascript">

$(function(){
	
	//회원의 정보 수정 및 삭제(탈퇴) 처리가 되면 메시지 출력
	var msg = "<c:out value='${ MSG }'/>";
	if (msg) {
		alert(msg);
	}
	
	
	/* 페이지 처리 */
	
	var total 	= "<c:out value='${ TNT }'/>";
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
	var mapping 	= (isMulti == "Y")? "bbscttMulti.do" : "bbscttDetail.do";
	
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
<style>
.page_no {
	font-size: 14px;
	display: flex;
	flex-direction: row;
	justify-content: center;
}
.pageBtn{
	width: 100px;
	height: ;
}
</style>
<title>notice</title>

</head>
<div id="frameOut">
	<div id="frameIn">
		<div class="sub_logo">
			<!---->
			<a href="loginMain.do"><img src="${path}/resources/img/logoB.png"
				class="mlogo" alt="로그인화면으로 이동"></a>

		</div>
		<header id="header">
			<jsp:include page="${request.contextPath}/header"></jsp:include>
		</header>
		<div id="content">
			<div class="content_container">
				<div class="line_menu">
					<div class="resveHeader">
						<div class="bbscttTitle">
							<h2>자유게시판</h2>
						</div>
					</div>
					<div class="list">
						<span><i class=""></i></span>
						<div class="text">
							<input type="text" name="condition" value=""
								placeholder="@검색어를 입력하세요" size="80"> <input
								type="submit" value="검색">
						</div>
						<div class="transactionTable">
							<table class="tb-1">
								<tr class="tr-1">
									<td id="title">게시글번호</td>
									<td id="title">제목</td>
									<td id="title">작성자</td>
									<td id="title">작성일</td>
								</tr>
								<c:forEach var="bbsctt" items="${LIST}">
									<input id="bbscttNo" type="hidden" value="${bbsctt.bbscttNo}">
									<tr>
										<td>${bbsctt.bbscttNo}</td>
										<td><a href="bbscttDetail.do?BBSCTT_NO=${bbsctt.bbscttNo}">${bbsctt.bbscttSj}</a></td>
										<td>${bbsctt.mberCode}</td>
										<td class="center"><fmt:formatDate
												value="${bbsctt.bbscttDt}" pattern="yy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</table>
							<div class="page_no l_box">
								<div id="before_pages"></div>
								<div id="view_pages"></div>
								<div id="next_pages"></div>
							</div>
							<div align="right">
								<input type="button" value="글쓰기"
									onclick="location.href='bbscttInsertForm.do'">
							</div>
						</div>
					</div>
				</div>
			</div>
			<footer id="footer">
				<jsp:include page="${request.contextPath}/footer"></jsp:include>
			</footer>
		</div>
	</div>
</div>
</body>
</html>