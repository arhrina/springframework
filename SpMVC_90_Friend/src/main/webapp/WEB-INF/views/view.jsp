<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		
		$("#modify-btn").click(function(){
			document.location.href = "${rootPath}/friend/modify?f_id=" + ${friendVO.f_id}
		})
		
		$("#delete-btn").click(function(){
			if(confirm("진짜로 삭제할거?")) {
				document.location.href = "${rootPath}/friend/delete?f_id=" + ${friendVO.f_id}
			}
		})
	})
</script>
<body>
<table>
	<tr>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>취미</th>
		<th>관계</th>
	</tr>
		<tr>
			<td>${friendVO.f_name}</td>
			<td>${friendVO.f_tel}</td>
			<td>${friendVO.f_addr}</td>
			<td>${friendVO.f_hobby}</td>
			<td>${friendVO.f_relat}</td>
		</tr>
</table>
<button id="modify-btn">수정</button>
<button id="delete-btn">삭제</button>
</body>
</html>