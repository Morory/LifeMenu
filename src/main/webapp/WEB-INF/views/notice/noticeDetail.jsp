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
<link href="${path}/resources/css/bbsctt.css" rel="stylesheet"
	type="text/css">
<title>Lifemenu</title>
</head>
<body>
	<div id="frameOut">
		<div id="frameIn">
			<div class="logo">
				<img src="resources/img/logoB.png" class="mlogo" alt="">
			</div>
			<header id="header">lifemenu</header>
			<div id="content">
				<div class="box-wrap">
					<div class="bbscttBox">
						<table>
							<tr>
								<td class="info_title">공지글</td>
								<td class="info_contents">${ NOTICE.bbscttNo }</td>
								<td class="info_title">작성자</td>
								<td class="info_contents">${ MBER.mberCode }</td>
								<td class="info_title">등록일</td>
								<td class="info_contents">${NOTICE.bbscttDt}</td>
							</tr>
							<tr>
								<td class="info_title">제목</td>
								<td class="info_l_contents" colspan="5">${ NOTICE.bbscttSj }</td>
							</tr>
						</table>
					</div>
					<div class="bbscttBox">
						<textarea rows="22" cols="180" readonly>${NOTICE.bbscttCn}</textarea>
					</div>
					<div class="button-group bbscttBox">
						<input type="button" value="목록"
							onclick="location.href='noticeMulti.do'">
					</div>
				</div>
			</div>
			<footer id="footer">
				<div class="otc">
					<a href="#">회사소개</a> <a href="#">인재채용</a> <a href="#">제휴제안</a> <a
						href="#">이용약관</a> <a href="#">개인정보처리방침</a> <a href="#">고객센터</a> <a
						href="#">@LM Corp.</a>
				</div>
			</footer>
		</div>
	</div>
</body>
</html>