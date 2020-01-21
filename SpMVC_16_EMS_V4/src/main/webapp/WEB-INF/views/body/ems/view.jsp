<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<style>
	
</style>
<script>
	$(function(){
		$("#btn-modify").click(function(){
			document.location.href = "${rootPath}/ems/update/" + ${emailVO.emsSeq}
		})
		$("#btn-delete").click(function(){
			if(confirm("정말로 삭제하시겠습니까?")) {
				document.location.href = "${rootPath}/ems/delete/" + $(emailVO.emsSeq)
			}
		})
	})
</script>
</head>
<body>
	<table>
		<tr data-seq="${emailVO.emsSeq}">
			<th>보낸 주소</th><td>${emailVO.fromEmail}</td>
			<th>받는 주소</th><td>${emailVO.to_email}</td>
		</tr>
		<tr>
			<th>작성일자</th><td>${emailVO.sendDate}</td>
			<th>작성시각</th><td>${emailVO.sendTime}</td>
		</tr>
		<tr>
			<th>작성자 이름</th><td>${emailVO.fromName}</td>
			<th>수신인 이름</th><td>${emailVO.to_name}</td>
		</tr>
		<tr>
			<th>제목</th><td>${emailVO.subject}</td>
		</tr>
		<tr>
			<td>${emailVO.content}</td>
		</tr>
	</table>
	<div>
		<button id="btn-modify">수정</button>
		<button id="btn-delete">삭제</button>
	</div>
</body>
</html>