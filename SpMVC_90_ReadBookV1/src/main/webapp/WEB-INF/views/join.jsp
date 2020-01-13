<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<script>
$(function(){
	$("#btn-insert").click(function(){
		let id = $("#u_id").val()
		if(id == "") {
			alert("아이디를 입력하세요")
			$("#u_id").focus()
			return false
		}
		
		let pass = $("#u_password").val()
		if(pass == "") {
			alert("비밀번호를 입력하세요")
			$("#u_password").focus()
			return false
		}
		
		let re_pass = $("#re_password").val()
		if(re_pass == "") {
			alert("비밀번호를 한번 더 입력해 주세요")
			$("#re_password").focus()
			return false
		}
		
		if(pass != re_pass) {
			alert("비밀번호와 비밀번호확인이 일치하지 않습니다\n"
					+ "다시 입력해 주세요")
			$("#u_password").val("")
			$("#re_password").val("")
			$("#u_password").focus()
			return false
		}
	})
})
</script>
<body>
	<form method="POST" action="${rootPath}/member/join" class="join-form">
		<h2>회원가입</h2>
		<input type="text" id="u_id" name="m_id" placeholder="사용자 ID">
		<input type="password" id="u_password" name="m_password" placeholder="비밀번호">
		<input type="password" id="re_password" name="m_re_password" placeholder="비밀번호확인">
		<button id="btn-insert">회원가입</button>
	</form>
</body>
</html>