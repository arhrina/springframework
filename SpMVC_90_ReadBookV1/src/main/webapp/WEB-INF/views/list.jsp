<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>도서코드</th>
			<th id="th-date">도서제목</th>
			<th>저자</th>
			<th>출판사</th>
			<th>구입일자</th>
			<th>구입가격</th>
		</tr>
		<c:choose>
			<c:when test="${empty BOOKLIST}">
				<tr>
					<td colspan="6">책 정보가 없음</td>
			</c:when>
			<c:otherwise>
				<c:forEach items="${BOOKLIST}" var="book" varStatus="index">
					<tr class="content-body" data-id="${book.b_code}"
						data-auth="${book.b_auther}">
						<td>${index.count}</td>
						<td>${book.b_name}</td>
						<td>${book.b_auther}</td>
						<td>${book.b_comp}</td>
						<td>${book.b_year}</td>
						<td>${book.b_iprice}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</table>
</body>
</html>