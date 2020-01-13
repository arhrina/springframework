<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="${rootPath}/member/join" class="join-form">
		<h2>회원가입</h2>
		<input type="text" name="m_id" placeholder="사용자 ID">
		<input type="password" name="m_password" placeholder="비밀번호">
		<input type="password" name="m_re_password" placeholder="비밀번호확인">
		<button>회원가입</button>
	</form>
</body>
</html>