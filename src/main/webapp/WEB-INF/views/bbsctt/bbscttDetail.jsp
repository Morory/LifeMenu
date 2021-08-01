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
<title>Lifemenu</title>
<style>
.bb-1:read-only{
	background: yellow;
}
</style>
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
				<div class="box-wrap">
					<div class="bbscttBox">
						<table>
							<tr>
								<td class="info_title">게시글번호</td>
								<td class="info_contents">${ BBSCTT.bbscttNo }</td>
								<td class="info_title">작성자</td>
								<td class="info_contents">${ BBSCTT.mberCode }</td>
								<td class="info_title">등록일</td>
								<td class="info_contents"><fmt:formatDate
										value="${BBSCTT.bbscttDt}" pattern="yy-MM-dd" /></td>
							</tr>
							<tr>
								<td class="info_title">제목</td>
								<td class="info_l_contents" colspan="5">${ BBSCTT.bbscttSj }</td>
							</tr>
						</table>
					</div>
					<div class="bbscttBox">
						<div class="bb-1"><textarea name="bbscttCn" rows="13" class="b-cn" readonly
												cols="40" style="ime-mode: inactive;">${BBSCTT.bbscttCn}</textarea>
						</div>
					</div>
					<form method="post" action="/LifeMenu/comentInsert.do?BBSCTT_NO=${BBSCTT.bbscttNo}">
					<div class="comentBox">
						<div class="cb-1"><textarea name="bbscttCn" rows="13" class="b-cn"
												cols="40" style="ime-mode: inactive;"></textarea></div>
						<div class="comentBtn"><input type="submit" value="확인" name=""></div>
					</div>
					</form>
					<div class="bbscttBox">
						<input type="button" value="목록"
							onclick="location.href='/LifeMenu/bbscttMulti.do?start=1&cnt=8'">
						<c:set var="code" value="${SS_MBER_DTO}"/>
						<c:if test="${SS_MBER_DTO.mberCode eq BBSCTT.mberCode}">
							<input type="button" value="수정"
								onclick="location.href='bbscttUpdate.do'">
						
							<input type="button" value="삭제"
								onclick="location.href='bbscttDelete.do'">
						</c:if>
					</div>
					<div class="otcComent">
							<table>
								<tr>
									<td>내용</td>
									<td>작성자</td>
									<td>작성일시</td>
								</tr>
								<c:forEach var="c" items="${COMENT_LIST}">
								<tr>
									<td>${c.bbscttCn}</td>
									<td>${c.mberCode}</td>
									<td>${c.bbscttDt}</td>
								</tr>
								</c:forEach>
							</table>
					</div>
				</div>
			</div>
        <footer id="footer">
			<jsp:include page="${request.contextPath}/footer"></jsp:include>
        </footer>
		</div>
	</div>
</body>
</html>