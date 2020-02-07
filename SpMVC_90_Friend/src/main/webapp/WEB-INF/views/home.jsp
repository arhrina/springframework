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
</head>
<body>

	<tr>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>취미</th>
		<th>관계</th>
	</tr>
	<c:if test="${not empty friend_list}">
		<c:forEach var="fList" items="friend_list">
			<tr>
				<td>${fList.f_name}</td>
				<td>${fList.f_tel}</td>
				<td>${fList.f_addr}</td>
				<td>${fList.f_hobby}</td>
				<td>${fList.f_relat}</td>
			</tr>
		</c:forEach>
	</c:if>

</body>
</html>