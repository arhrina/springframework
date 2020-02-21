<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>나의 홈페이지</title>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<script>
	$(function(){
		
		$("#basket").click(function(){
			var p_id = $(this).attr("data-pid")

			if(로그인여부 & 권한이 관리자가 아님 체크)
				유저의 id로 장바구니에 상품 추가
			else
				document.location.href="" 로그인페이지로
		})
		
		$("#buy").click(function(){
			var p_id = $(this).attr("data-pid")

			if(로그인여부 & 권한이 관리자가 아님 체크)
			document.location.href="${rootPath}" 구매페이지로
			else
				document.location.href="" 로그인페이지로
		})
		
	})
</script>
</head>
<body>
<h3>상품정보 상세페이지</h3>

<div>
	${productVO.p_detail}
</div>

<div>
	<p>상품이름</p><br>
	${productVO.p_name}
</div>

<div>
	<p>판매가격</p><br>
	${productVO.p_oprice}
</div>

<div>
	<button id = basket type="button" data-pid="${productVO.id}">장바구니</button>
	<button id = buy type="button" data-pid="${productVO.id}">구매하기</button>
</div>


</body>
</html>