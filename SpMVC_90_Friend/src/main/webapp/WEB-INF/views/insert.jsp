<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	$("button#save-btn").click(function(){
		let fName = $("#f_name").val()
		let fTel = $("#f_tel").val()
		
		if(fName == ""){
			alert("이름은 반드시 입력해야합니다")
			$("#f_name").focus()
			return false
		}
		if(fTel == ""){
			alert("전화번호는 반드시 입력해야합니다")
			$("#f_tel").focus()
			return false
		}
		$("form").submit()
	})
})
</script>
<body>
<form method="POST">
	<input id="f_name" type="text" name="f_name" value="${vo.f_name}" placeholder="이름">
	<input id="f_tel" type="text" name="f_tel" value="${vo.f_tel}" placeholder="전화번호">
	<input type="text" name="f_addr" value="${vo.f_addr}" placeholder="주소">
	<input type="text" name="f_hobby" value="${vo.f_hobby}" placeholder="취미">
	<input type="text" name="f_relat" value="${vo.f_relat}" placeholder="관계">
	<button type="button" id="save-btn">입력하기</button>
</form>
</body>
</html>