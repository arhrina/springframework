<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-title.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>
	<!-- 모든걸 home으로 보내고 home에서 작업. 한눈에 어떤 것을 불러와야되는지 확인할 수 있다 -->
	<!-- BODY에 문자열 STUDENT-LIST가 있으면 file을 불러라. if에 else는 존재하지 않음 -->
	<!-- otherwise 아무것도 해당되는 것이 없다면 -->
	<c:choose>
		<c:when test="${BODY == 'STUDENT-LIST' }">
			<%@ include file="/WEB-INF/views/body/student/list.jsp"%>
		</c:when>
		<c:when test="${BODY == 'DEPT' }">
			<p>학과정보페이지</p>
		</c:when>
		<c:when test="${BODY == 'LOGIN' }">
			<p>로그인페이지</p>
		</c:when>
		<c:when test="${BODY == 'JOIN' }">
			<p>회원가입</p>
		</c:when>
		<c:otherwise>
			<p>시작페이지</p>
		</c:otherwise>
	</c:choose>
</body>
</html>