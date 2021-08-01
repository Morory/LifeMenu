<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}"/>

<link href="${path}/resources/css/common.css?ver=1" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin.css?ver=2" rel="stylesheet" type="text/css">
<link href="${path}/resources/js/admin-contents.js" rel="javascript" type="text/js">
<title>Lifemenu~administrator</title>
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
	        <div id="content_container">
	            <div class="line_menu">
	               	<span class="menu"><a class="link" href="mberMngrMulti.do?start=1&cnt=8">회원 관리</a></span>
	               	<span class="menu"><a class="link" href="mngrInqryMulti.do?start=1&cnt=8">관리자문의</a></span>
	               	<span class="menu"><a class="link" href="noticeMngrMulti.do?start=1&cnt=8">공지사항<br>관리</a></span>
	               	<span class="menu"><a class="link" href="bbsMngrMulti.do?start=1&cnt=8">자유게시판<br>관리</a></span>
	           	</div>
		        <div class="line_menu">
		           <span class="menu"><a class="link" href="tagMngrMulti.do?start=1&cnt=8">태그 관리</a></span>
		           <span class="menu"><a class="link" href="excclcMngr.do">정산 관리</a></span>
		           <span class="menu"><a class="link" href="vochrMngrMulti.do?start=1&cnt=8">식사권 관리</a></span>
		           <span class="menu"><a class="link" href="histMngrMulti.do?start=1&cnt=8">히스토리 관리</a></span>
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