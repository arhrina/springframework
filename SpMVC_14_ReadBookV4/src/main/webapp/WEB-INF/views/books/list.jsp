<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-head.jspf" %> <!-- include하는 파일들이 많아졌으므로 파일로 따로 분리해서 로드 -->
<script>
$(function(){
	// $("#btn-write").click(function() {
	$("#btn-write").click(() => { // 화살표함수. arrow function
		document.location.href = "${rootPath}/book/write"
	})
})
</script>
</head>
<body>
	<header>
		<h2>MY READ BOOK</h2>
	</header>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
	<section id="main-list">
		<table id="main-table">
			<thead>
				<tr>
					<th>도서코드</th>
					<th>도서이름</th>
					<th>출판사</th>
					<th>저자</th>
					<th>구입일자</th>
					<th>구입가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${b_List}" var="book">
					<tr>
						<td>${book.b_code}</td>
						<td>${book.b_name}</td>
						<td>${book.b_comp}</td>
						<td>${book.b_auther}</td>
						<td>${book.b_year}</td>
						<td>${book.b_iprice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<section>
		<div id="main-button">
			<button id="btn-write" class="biz-blue flex-right">독서록 작성</button>
		</div>
	</section>
</body>
</html>