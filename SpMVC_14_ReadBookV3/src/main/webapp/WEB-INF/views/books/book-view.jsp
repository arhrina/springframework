<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- formatting tag lib. 숫자, 날짜 등에 기호표기를 도와줌 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-head.jspf" %> <!-- include하는 파일들이 많아졌으므로 파일로 따로 분리해서 로드 -->
<style>

	article {
		display: flex;
		flex-flow: wrap;
	}
	
	.book-title, .book-body {
		display: inline-block;
		width: 23%;
		padding: 5px 16px;
		margin: 5px;
		background-color: #eee;
	}
	
	div.book-title {
		background-color: #ccc;
		text-align: right;
	}
	
</style>
</head>
<body>
	<section>
		<article>
			<div class="book-title">도서코드</div>
			<div class="book-body">${book.b_code}</div>
			
			<div class="book-title">도서명</div>
			<div class="book-body">${book.b_name}</div>
			
			<div class="book-title">출판사</div>
			<div class="book-body">${book.b_comp}</div>
			
			<div class="book-title">저자</div>
			<div class="book-body">${book.b_auther}</div>
			
			<div class="book-title">구입일</div>
			<div class="book-body">${book.b_year}</div>
			
			<div class="book-title">구입가격</div>
			<div class="book-body">
			
			<fmt:formatNumber value="${book.b_iprice}" type="number" maxFractionDigits="3" var="commaPrice"/>
			<!-- 3자리마다 , 추가해서 문자열을 만들고 commaPrice라는 변수명에 추가 -->
			${commaPrice}
			
			<fmt:formatNumber value="${book.b_iprice}" type="currency" maxFractionDigits="3" var="curPrice"/>
			<!-- 통화기호를 붙여서 추가하는 type currency -->
			${curPrice}
			
			<fmt:setLocale value="en_US"/>
			<fmt:formatNumber value="${book.b_iprice}" type="currency" maxFractionDigits="3"/>
			<!-- var를 넣지 않으면 즉시 화면에 출력하는 확장방식 -->
			</div>
			
			<fmt:formatNumber value="0.1" type="percent" maxFractionDigits="3" var="commaPrice"/>
			<!-- 백분율 표기 -->
			
		</article>
	</section>
</body>
</html>