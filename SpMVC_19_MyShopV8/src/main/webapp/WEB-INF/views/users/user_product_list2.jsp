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
		$(".pContent").click(function(){
			var p_id = $(this).attr("data-pid")
			// class를 지정해서 보내면 리스트의 제일 첫번째에 해당하는 것만 보낸다
			document.location.href = "${rootPath}/user/product/detail?pId=" + p_id
		})
	})
</script>
</head>
<body>
	<p>상세정보를 보시려면 상품을 클릭하세요</p>
	<table>
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
			<th>판매가격</th>
		</tr>
	<c:forEach var="p" items="${pList}">
		<tr class="pContent" data-pid = "${p.id}">
			<td>${p.p_name}</td>
			<td>${p.p_oprice}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>