<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/depositView.css" rel="stylesheet" type="text/css">
<!--  jQuery Framework 참조하기 -->
<script type="text/javascript" src="${path}/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/logheader.js"></script>
<title>입금</title>

<script>
$(function deposit(){
	var result = document.getElementById("rcppayAmount");

	$('#depositBtn').click(function(){
		if($('#rcppayAmount').val() == 'none'){
			alert('입금 금액을 선택해주세요.');
			return false;
		}
		if(confirm( result.options[result.selectedIndex].text + "을 충전 하시겠습니까?")){
			alert("충전이 완료 되었습니다.");
		}
		else{
			alert("취소하셨습니다.");
			return false;
		}
	});
});

$(function coin(){
	var havedCoin = "<c:out value='${COIN }'/>";	//가지고 있는 코인
	havedCoin = parseInt(havedCoin);	//숫자로 변환
	$('#yoteiCoin').val(havedCoin);
	
	$('#rcppayAmount').click(function(){
		var rcppayAmount = $("#rcppayAmount option:selected").val();
		
		if(rcppayAmount == 'none'){
			$('#yoteiCoin').val(havedCoin);
		}
		if(rcppayAmount == 10000){
			$('#yoteiCoin').val(havedCoin+1);
		}
		if(rcppayAmount == 20000){
			$('#yoteiCoin').val(havedCoin+2);
		}
		if(rcppayAmount == 30000){
			$('#yoteiCoin').val(havedCoin+3);
		}
		if(rcppayAmount == 50000){
			$('#yoteiCoin').val(havedCoin+5);
		}
		if(rcppayAmount == 100000){
			$('#yoteiCoin').val(havedCoin+10);
		}
		if(rcppayAmount == 200000){
			$('#yoteiCoin').val(havedCoin+20);
		}
		
	});
});
</script>
</head>
<body>
<div id="frameOut">
    <div id="frameIn">
        <div class="sub_logo">
        	<a href="/LifeMenu/seller/list"><img src="resources/img/logoB.png"  class="mlogo" alt="로그인화면으로 이동"></a> 
        </div>
        <header id="header">
				<jsp:include page="${request.contextPath}/header"></jsp:include>
		</header>
        <div id="content">
        	<div class="content_container">
        		<div class="line_menu">
        			 <div class="depositHeader">
				   		 <div class="wh-1">
							<h2 align="left">입금</h2>
						</div>
						<div class="wh-2">
							<h3>보유코인 ${COIN } 개</h3>
						</div>
				  	</div>
				  	<div class="depositBox">
				  		<div class="depositLeftBox">
        				<form action="deposit.do" onsubmit='return deposit()'>
				        			입금금액 &nbsp;<select id="rcppayAmount" name="rcppayAmount" style="width:210px;height:24px;font-size:15px; vertical-align: middle; text-align-last: center " >
				        			<option value="none" selected>======  선택  ====== </option>
				        			<option value="10000"> 10000 원 </option>
				        			<option value="20000"> 20000 원 </option>
				        			<option value="30000"> 30000 원</option>
				        			<option value="50000"> 50000 원 </option>
				        			<option value="100000"> 100000 원  </option>
				        			<option value="200000"> 200000 원  </option>	
				        		</select>
				        		<br>
				        		<br>
				        		예상 보유 코인 : <b><input type="text" name=yoteiCoin id="yoteiCoin" value="0개" readonly style="width:40px; border:0 solid black; font-size:20px;"></b>
				        	<div class="depositBtn">
				        		<input type="submit" class="btn" id="depositBtn" value="입금">&nbsp;<input type="button" class="btn" id="cancelBtn" value="취소"onclick="document.location.href='walletView'">
		       				</div>
		       				<input type="hidden" name="mberCode" value="${dto.mberCode}"> <!-- 회원코드 -->
		       			   <!-- <input type="hidden" name="rcppayDt" value=""> 				  입출금 일시 -->
		       			    <input type="hidden" name="rcppayCl" value="D"> 			 <!-- 입출금 분류 -->
		       			    <input type="hidden" name="excngCoinCo" value="0"> 			 <!-- 환전 코인 수 -->
			        	</form>
			        	</div>
						<%-- 			        		
				        	결제수단선택<br>
				        	<input type="button" id="generalPayment" name=generalPayment value="일반결제">
				        	<input type="button" id="simplePayment" name=simplePayment value="간편결제">
				    		
				        	<div class=generalPayment>
								<input type="radio" name="chk_info" value="cellphone" checked>휴대폰
								<input type="radio" name="chk_info" value="virtualAccount">가상계좌
								<input type="radio" name="chk_info" value="creditCard">신용카드<br>
								<input type="radio" name="chk_info" value="accountTransfer">계좌이체
								<input type="radio" name="chk_info" value="giftCard">상품권
				        	</div>
				        	<div class=simplePayment></div>
				        	--%>
				        	<div class="depositRightBox">
			        				<textarea rows="25" cols="50" style="resize: none;"readonly>
								주의사항 !
										삐링삐링뽀롱뽀롱뿎뿎
			        				</textarea>
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
</body>
</html>