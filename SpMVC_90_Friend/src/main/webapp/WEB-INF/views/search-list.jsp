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
			let id = $(this).data("idVal")
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
				
	<c:if test="${not empty search_list}">
		<c:forEach var="fList" items="${search_list}">
			<tr data-idVal="${fList.f_id}" class="modify_id">
				<td>${fList.f_name}</td>
				<td>${fList.f_tel}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty search_list}">
		<tr><td colspan="2">검색 데이터가 없음</td></tr>
	</c:if>
</table>
</body>
</html>