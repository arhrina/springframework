<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<script>
	$(function(){
		$("button#insert-btn").click(function(){
			document.location.href = "${rootPath}/insert"
		})
		
		$("tr.modify_id").click(function(){
			let id = $(this).data("id-val")
//			$(this).attr("data-id-val")
			document.location.href = "${rootPath}/view?f_id=" + id
		})
	})
</script>
<body>
<table>
	<tr>
		<th>이름</th>
		<th>전화번호</th>
	</tr>
				
	<c:if test="${not empty friend_list}">
		<c:forEach var="fList" items="${friend_list}">
			<tr data-id-val="${fList.f_id}" class="modify_id">
				<td>${fList.f_name}</td>
				<td>${fList.f_tel}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty friend_list}">
		<tr><td colspan="2">데이터가 없음</td></tr>
	</c:if>
</table>
<form action="${rootPath}/search" method="POST">
	<input type="text" name="fName" placeholder="이름으로 검색">
	<input type="text" name="fTel" placeholder="전화번호로 검색">
	<button>검색</button>
</form>
<button id="insert-btn" type="button">자료입력</button>
</body>
</html>