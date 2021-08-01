<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
							<h2>공지게시판</h2>
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
								<td id="title"></td>
								<td id="title">작성자</td>
								<td id="title">작성일</td>
							</tr>
							<c:forEach var="notice" items="${LIST}">
								<input id="bbscttNo" type="hidden" value="${notice.bbscttNo}">
								<tr>
									<td>${ notice.bbscttNo }</td>
									<td><a href="noticeDetail.do?bbscttNo=${notice.bbscttNo}">${notice.bbscttSj}</a></td>
									<td>${ MBER.mberCode }</td>
									<td><c:set var="pdAT" value="${t.purchsDcsnAt}" /> <c:set
											var="rfAT" value="${t.refndReqstAt}" /></td>
									<td class="center">${ notice.bbscttDt }</td>
								</tr>
							</c:forEach>
						</table>
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