<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.2 -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin-voucher-detail-ver.1.0.0.css?ver=1" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/column-width.css?ver=2" rel="stylesheet" type="text/css">
<title>Lifemenu~administrator</title>

<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

var start 	= "<c:out value='${ START }'/>";
var cnt 	= "<c:out value='${ CNT }'/>";

$(document).ready(function(){
	
	alert("Hellw, 식사권 상세!");
	
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
         	<form action="" method="POST">
	            <div class="box-wrap">
	            	<div class="title box">
	            		<div><h3>식사권 상세</h3></div>
	            	</div>
	            	<div class="content box">
	            	    <div class="voucher box">
		          		    <div class="info_box">
		          		        <h4>식사권 정보</h4>
		          		        <span>meal voucher information</span>
		          		    </div>
		            	    <div class="info_box">
		            	        <span class="col">식사권코드</span>
		            	        <span class="col-vochr-data">${ VOCHR.vochrCode }</span>
		            	        <span class="col">판매자코드</span>
		            	        <span class="col-vochr-data">${ VOCHR.mberCode }</span>
		            	    </div>
		            	    <div class="info_box">
		            	        <span class="col">식사권 가격</span>
		            	        <span class="col-vochr-data">${ VOCHR.vochrPrice }</span>
		            	        <span class="col">식사권 등록 일시</span>
		            	        <span class="col-vochr-data">${ VOCHR.vochrRegistDt }</span>
		            	    </div>
		            	    <div class="info_box">
		            	        <span class="col">식사 예정 일시</span>
		            	        <span class="col-vochr-data">${ VOCHR.mealPrearngeDt }</span>
		            	        <span class="col">식사 시간</span>
		            	        <span class="col-vochr-data">${ VOCHR.mealTime }</span>
		            	        <span class="col">거래 가능 여부</span>
		            	        <span class="col-vochr-data">${ VOCHR.delngAt }</span>
		            	    </div>
		            	</div>
		            	<div class="resveSetle box">
		            	    <div class="info_box">
		            	        <h4>예약 결제 정보</h4>
		            	        <span>reservation and setle information</span>
		            	    </div>
							<div class="l_title l_box">
								<div class="resveCode l_column">예약결제코드</div>
								<div class="mberCode l_column">구매자코드</div>
								<div class="resvePc l_column">예약가격</div>
								<div class="resveDt l_column">예약일시</div>
								<div class="setlePc l_column">결제가격</div>
								<div class="setleDt l_column">결제일시</div>
								<div class="setleAt l_column">결제완료여부</div>
								<div class="refndReqstAt l_column">환불신청여부</div>
								<div class="purchsDcsnAt l_column">구매확정여부</div>
								<div class="etc l_column">비고</div>
							</div>
							<c:if test="${ fn:length(LIST) == 0 }">
		            		    <div class="l_row l_box">
		            		        <div class="no_data">조회된 데이터가 없습니다</div>
		            		    </div>
		            		</c:if>
							<c:forEach var="resveSetle" items="${ LIST }">
								<div class="l_row l_box">
									<div class="resveCode l_column">${ resveSetle.RESVE.resveCode }</div>
									<div class="mberCode l_column">${ resveSetle.RESVE.mberCode }</div>
									<div class="resvePc l_column">${ resveSetle.RESVE.resvePc }</div>
									<div class="resveDt l_column">${ resveSetle.RESVE.resveDt }</div>
									<div class="setlePc l_column">${ resveSetle.SETLE.setlePc }</div>
									<div class="setleDt l_column">${ resveSetle.SETLE.setleDt }</div>
									<div class="setleAt l_column">${ resveSetle.SETLE.setleAt }</div>
									<div class="refndReqstAt l_column">${ resveSetle.SETLE.refndReqstAt }</div>
									<div class="purchsDcsnAt l_column">${ resveSetle.SETLE.purchsDcsnAt }</div>
									<div class="etc l_column">
									    <c:if test="${ resveSetle.SETLE.purchsDcsnAt == 'N'.charAt(0) }">
									        <button onclick="purchsDcsnAtPopup.do?setleCode=${ resveSetle.SETLE.setleCode }">구매확정</button>
									    </c:if>
									    <c:if test="${ resveSetle.SETLE.purchsDcsnAt == 'Y'.charAt(0) }">
									        <div>구매확정완료</div>
									    </c:if>
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