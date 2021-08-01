<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ver.1.0.4 -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link href="${path}/resources/css/common.css?ver=1" rel="stylesheet" type="text/css">
<link href="${path}/resources/css/admin-menu-ver.1.0.6.css?ver=2" rel="stylesheet" type="text/css">

<title>Lifemenu~administrator</title>

<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	// 태그 등록, 수정, 삭제 처리가 되면 메시지 출력
	var msg = "<c:out value='${ MSG }'/>";
	if (msg != "") {
		alert(msg);
	}

	// 태그 등록 버튼
	var insertTagToggle = 0;

	$('.insertTagForm').hide();
	$('#insertTag').click(function(){
		if (insertTagToggle%2 == 0){
			$('.insertTagForm').show();
			insertTagToggle++;
		}
		else {
			$('.insertTagForm').hide();
			insertTagToggle++;
		}
	});
	
	$("#resetTagBtn").click(function(){
		$("input[name='tagName']").val("");
	});
	
	$("input[name='uploadFile']").change(function(){
		var file = $("input[name='uploadFile']").val();
		alert("file="+file);
		$("#preview-image").attr("src", file);
	});
	
	
	// 수정, 삭제, 수정확인, 취소버튼
	$(".modifyBtn").each(function(i, e){
		$(this).click(function(){
			$(".updateBtn").eq(i).show();
			$(".cancelBtn").eq(i).show();
			$(".deleteBtn").eq(i).hide();
			$(".tagNm-data").eq(i).attr("readonly", false);
			$(".imageAdres-data").eq(i).attr("disabled", false);
			$(this).hide();
		});
	});
	$(".cancelBtn").each(function(i, e){
		$(this).click(function(){
			$(".updateBtn").eq(i).hide();
			$(".deleteBtn").eq(i).show();
			$(".modifyBtn").eq(i).show();
			$(".tagNm-data").eq(i).attr("readonly", true);
			$(".imageAdres-data").eq(i).attr("disabled", true);
			$(this).hide();
		});
	});
	$(".updateBtn").each(function(i, e){
		$(this).click(function(){
			$("#tagCode").val($(".tagCode-data").eq(i).val());
			$("#tagNm").val($(".tagNm-data").eq(i).val());
		});
	});

	$(".deleteBtn").each(function(i, e){
		$(this).click(function(){
			
			var tagCode = $(".tagCode-data").eq(i).val();
			var tagNm = $(".tagNm-data").eq(i).val();
			
			$("#tagCode").val(tagCode);
			$("#tagNm").val(tagNm);
			
		});
	});

	$(".updateBtn").hide();
	$(".cancelBtn").hide();
	
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
	var mapping 	= (isMulti == "Y")? "tagMngrMulti.do" : "tagMngrCondition.do";
	
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
	
	
	var file = $("input[type='file']");
	console.log(file);
	
	/* function 마지막 */
});

</script>

</head>
<body>
<div id="frameOut">
    <div id="frameIn">
        <div class="sub_logo">
            <img src="resources/img/logoB.png" class="mlogo" alt="">
        </div>
        <header id="header"></header>
        <div id="content">
        	<form action="tagMngrCondition.do" method="POST" enctype="multipart/form-data">
	            <div class="box-wrap">
	            	<div class="title box">
	            		<h2>태그 관리</h2>
	            	</div>
	            	<div class="search box">
	            		<div class="home s_box">
	            			<a href="adminMain.do">홈으로</a>
	            		</div>
	            		<div class="combo s_box">
	            			<select name="conditionType">
	            				<option value="TAG_CODE" selected="selected">태그코드</option>
	            				<option value="TAG_NM">태그명</option>
	            			</select>
	            		</div>
	            		<div class="search_text s_box">
	            			<input type="text" name="condition" value="" placeholder="@검색어를 입력하세요" size="80">
	            		</div>
	            		<div class="search_btn s_box">
	            			<input type="submit" class="search-btn" value="검색">
	            			<input type="hidden" name="start" value="1">
	            			<input type="hidden" name="cnt" value="8">
	            		</div>
	            		<div class="insert_btn s_box">
	            			<input id="insertTag" class="search-btn" type="button" value="태그등록">
	            		</div>
	            	</div>
	            	<div class="insertTagForm box">
	            		<div class="tagNmTitle">태그명</div>
	            		<div class="tagNmInput"><input type="text" name="tagName" value="" size="20"></div>
	            		<div class="imageAdresTitle">태그이미지</div>
	            		<div class="f_box">
	            		    <div class="fileRegist">
		            			<input type="file" name="uploadFile" accept="image/*">
		            			<div id="imagePreview">
		            			    <img style="width:40px; height:40px;" id="preview-image" alt="" src="">
		            			</div>
	            			</div>
	            		</div>
	            		<div class="f_box"><input type="submit" id="insertTagBtn" value="등록" formaction="insertTagMngr.do"></div>
	            		<div class="f_box"><input type="button" id="resetTagBtn" value="취소"></div>
	            	</div>
	            	<div class="list box">
	            		<div class="l_title l_box">
	            			<div class="tagCode l_column">태그 번호</div>
	            			<div class="tagNm l_column">태그명</div>
	            			<div class="imageAdres l_column">이미지</div>
	            			<div class="tagReadCnt l_column">조회수</div>
	            			<div class="etc2 l_column">비고</div>
	            		</div>
	            		<c:if test="${ fn:length(LIST) == 0 }">
	            		    <div class="l_row l_box">
	            		        <div class="no_data">조회된 데이터가 없습니다</div>
	            		    </div>
	            		</c:if>
	            		<c:forEach var="tag" items="${ LIST }" varStatus="status">
	            			<div class="l_row l_box">
	            			    <div class="tagCode l_column">
	            			        <input type="text" class="tagCode-data" value="${tag.tagCode}" readonly />
	            			    </div>
	            			    <div class="tagNm l_column">
	            			        <input type="text" class="tagNm-data" value="${tag.tagNm}" readonly />
	            			    </div>
	            			    <div class="imageAdres l_column">
	            			        <div class="fileRegist">
	            			            <input type="file" class="imageAdres-data" name="imageAdres" value="${tag.imageAdres}" disabled>
	            			            <img class="imageIcon" src="${tag.imageAdres}" width="40" height="40">
	            			        </div>
	            			    </div>
	            			    <div class="tagReadCnt l_column">${tag.tagReadCnt}</div>
	            			    <div class="etc2 l_column">
	            			        <input type="button" class="modifyBtn" value="수정">
	            			        <input type="submit" class="updateBtn" value="수정완료" formaction="updateTagMngr.do">
	            			        <input type="submit" class="deleteBtn" value="삭제" formaction="deleteTagMngr.do">
	            			        <input type="button" class="cancelBtn" value="취소">
	            			    </div>
	            		    </div>
	            		</c:forEach>
	            		<input type="hidden" id="tagCode" name="tagCode">
	            		<input type="hidden" id="tagNm" name="tagNm">
	            		<div class="page_no l_box">
							<div id="first_pages" class="pages"></div>
							<div id="before_pages" class="pages"></div>
							<div id="view_pages" class="pages"></div>
							<div id="next_pages" class="pages"></div>
							<div id="last_pages" class="pages"></div>
						</div>
	            	</div>
	            </div> <%-- end box-wrap --%>
	        </form>
        </div>
        <footer id="footer">
            <div class="otc">
                <a href="#">회사소개</a>
                <a href="#">인재채용</a>
                <a href="#">제휴제안</a>
                <a href="#">이용약관</a>
                <a href="#">개인정보처리방침</a>
                <a href="#">고객센터</a>
                <a href="#">@LM Corp.</a>
            </div>
        </footer>
    </div>
</div>
</body>
</html>