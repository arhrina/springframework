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
	//$("#btn-write").click(function() {
	$("#btn-write").click( () => { // 화살표함수. arrow function. eclipse에서 못읽는 에러니 상관없음. 브라우저에서 해결
		document.location.href = "${rootPath}/rbook/insert"
	})
	$("#main-table tbody tr").click(function() {
		let seq = $(this).attr("data-id")
		document.location.href="${rootPath}/rbook/view/" + seq // pathVariable을 사용
		// pathVariable은 controller에서 value에 /{rb_seq} 방식으로 사용한다
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
					<th>사용자ID</th>
					<th>도서코드</th>
					<th>도서이름</th>
					<th>독서일자</th>
					<th>독서시각</th>
					<th>한줄평</th>
					<th>별점</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${RBOOKS}" var="rBook">
					<tr data-id="${rBook.rb_seq}">
						<td>사용자ID</td>
						<td>${rBook.rb_bcode}</td>
						<td>${rBook.rb_bname}</td>
						<td>${rBook.rb_date}</td>
						<td>${rBook.rb_stime}</td>
						<td>${rBook.rb_subject}</td>
						<td>${rBook.rb_star}</td>
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