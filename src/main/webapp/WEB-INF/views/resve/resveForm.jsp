<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet">
<link href="${path}/resources/css/resve.css?v=4" rel="stylesheet">
<script type="text/javascript" src="${path}/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/logheader.js"></script>
<script src="https://kit.fontawesome.com/9691fcfe8c.js"></script>
<title>resve</title>
<script>
function atConfirm(){
	if(confirm("이대로 예약을 진행 하시겠습니까?")){
		alert("예약확인 페이지로 이동합니다.");
	}
	else{
		return false;
	}
}
</script>
</head>
<body>
<div id="frameOut">
    <div id="frameIn">
        <div class="sub_logo">
            <a href="/LifeMenu/seller/list"><img src="${path}/resources/img/logoB.png" class="mlogo" alt=""></a>
        </div>
        <header id="header">
        	<jsp:include page="${request.contextPath}/header"></jsp:include>
        </header>
        <div id="content">
        <form method="post" action="/LifeMenu/ResveSetle/insertResve?SELER_CODE=${SELECT_SELER.mberCode}&VOUCHER_CODE=${SELECT_VOUCHER.vochrCode}">
            <div class="resveHeader">
                <div class="prevbtn">
                    <a href="/LifeMenu/seller/list" class="prevbtn_a"><i class="fas fa-chevron-circle-left fa-2x"></i></a>
                </div>
                <div class="resveTitle"><h1>예 약</h1></div>
            </div>
            <div class="vochrResve">
                <div class="vochrInfo">
                    <div class="vi">
                         <div class="vi-1">
                              <div class="resve_selerImage"><img src="http://localhost:8085/LifeMenu/display?fileName=${SELER_IMAGE}"></div>
                                </div>
                                <div class="vi-2">
                                <div class="resve_selerAprsm"><span>${SELECT_SELER.selerAprsm}</span></div>
                                <div class="resve_selerName"><span>성명</span><div class="rtbar"></div><span class="vi-2-text">${SELER_NAME.mberRlnm}</span></div>
                                <div class="resve_selerSelf"><span>자기소개</span><div class="rtbar"></div><span class="vi-2-text">${SELECT_SELER.selerSelfIntrcn}</span></div>
                                <div class="resve_selerCareer"><span>경력사항</span><div class="rtbar"></div><span class="vi-2-text">${SELECT_SELER.selerCareer}</span></div>
                         </div>
                    </div>
                </div>
                <div class="resveTime">
                    <div class="rt">
                         <div class="resve_vochrInfo"><h1><i class="fas fa-utensils"></i>식사권 정보</h1></div>
                         <div class="resve_vochrPc"><span>식사권가격</span><div class="rtbar"></div><span class="rt-text-1">${SELECT_VOUCHER.vochrPrice}코인</span></div>
                         <div class="resve_MealDt"><span>식사권예정일</span><div class="rtbar"></div><span class="rt-text-2">${SELECT_VOCHRDT}</span></div>
                         <div class="resve_delngAt"><span>구매가능여부</span><div class="rtbar"></div><span class="rt-text-3">${DELNG_AT}</span></div>
                    </div>
                    <input type="hidden" id="mberCode" name="mberCode" value="${dto.mberCode}">
                    <input type="hidden" id="vochrCode" name="vochrCode" value="${SELECT_VOUCHER.vochrCode}">
                    <input type="hidden" id="resvePc" name="resvePc" value="${SELECT_VOUCHER.vochrPrice}">
                </div>
                <div class="resveButton">
                    <button type="submit" class="custom-btn-resve" onclick="atConfirm()"><span>예약</span></button>
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