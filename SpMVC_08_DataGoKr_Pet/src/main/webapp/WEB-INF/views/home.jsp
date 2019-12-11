<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<style>
	header{
		background-color: rgba(0, 255, 0, 0.3);
		color: white;
		text-align: center;
		padding: 0.5rem;
	}
	nav{
		background-color: green;
	}
	nav input{
		width: 300px;
		padding: 8px;
		margin: 10px;
		border: 1px solid orange;
		border-radius: 20px;
		position: relative;
	}
	nav input:hover:after{
		content: '동물병원 이름이나 주소를 입력하고 ENTER를 누르세요';
		position: absolute;
		top: 20px;
		left: 30px;
	}
</style>
</head>
<body>
<header>
	<h3>my pet life</h3>
</header>
<nav>
	<form>
		<input type="text" name="s_text" placeholder="검색어를 입력한 후 ENTER">
	</form>
</nav>
<section>
	<table>
		<tr>
			<th>동물병원의 이름</th>
			<th>도로명주소</th>
			<th>지번주소</th>
			<th>대행업소의 연락처</th>
			<th>위도</th>
			<th>경도</th>
			<th>데이터기준일</th>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${empty H_LIST}">
				<tr><td colspan="7">데이터가 없음</td>
				</c:when>
				<c:otherwise>
					<c:forEach items="${H_LIST}" var="hs" varStatus="index">
						<tr>
						<td>${hs.apiDongName}</td>
						<td>${hs.apiNewAddress}</td>
						<td>${hs.apiOldAddress}</td>
						<td>${hs.apiTel}</td>
						<td>${hs.apiLat}</td>
						<td>${hs.apiLng}</td>
						<td>${hs.apiRegDate}</td>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</table>
</section>
</body>
</html>