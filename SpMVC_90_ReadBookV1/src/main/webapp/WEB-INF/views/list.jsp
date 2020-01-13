<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>도서정보</title>
</head>
<script>
	
	$(function(){
		
		$("#btn-insert").click(function(){
			document.location.href = "${rootPath}/books/insert"
		})
		
		$(".btn-modify").click(function(){
			var b_code = $(this).attr("data-id")
			document.location.href = "${rootPath}/books/update?b_code=" + b_code
		})
		
		$(".btn-delete").click(function(){
			if(confirm("삭제하시겠습니까?")){
				var b_code = $(this).attr("data-id")
				document.location.href = "${rootPath}/books/delete?b_code=" + b_code
			}
		})
		
		$(".content-body").click(function(){
				var b_code = $(this).attr("data-id")
				document.location.href = "${rootPath}/rbooks/rblist?b_code=" + b_code
			}
		)
	})
	
</script>
<body>
	<h2>도서 리스트 및 정보</h2>
	<table>
		<tr>
			<th>NO</th>
			<th id="th-date">도서제목</th>
			<th>저자</th>
			<th>출판사</th>
			<th>구입일</th>
			<th>페이지수</th>
			<th>구입가격</th>
		</tr>
		<c:choose>
			<c:when test="${empty bList}">
				<tr>
					<td colspan="7">책 정보가 없음</td>
			</c:when>
			<c:otherwise>
				<c:forEach items="${bList}" var="book" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td class="content-body" data-id="${book.b_code}">${book.b_name}</td>
						<td>${book.b_author}</td>
						<td>${book.b_comp}</td>
						<td>${book.b_year}</td>
						<td>${book.b_page}</td>
						<td>${book.b_iprice}</td>
						<td><button type="button" class="btn-modify" data-id="${book.b_code}">수정</button></td>
						<td><button type="button" class="btn-delete" data-id="${book.b_code}">삭제</button></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<button type="button" id="btn-insert">자료추가</button>
</body>
</html>