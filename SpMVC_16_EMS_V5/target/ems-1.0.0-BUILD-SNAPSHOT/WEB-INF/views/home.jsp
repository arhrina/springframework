<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>MY EMS</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/static/css/main.css?ver=2020-01-20-002">
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/static/css/table-list.css?ver=2020-01-20">
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/static/css/email-write.css?ver=2020-01-20">

<style>
	div.login-modal { /* 원래있던 창에 fixed하고 100% 화면을 div가 차지하게해서 뒤에 클릭 안되게 */
		display: none; /* 로그인을 누르면 뜨게 만들기 위해 일단은 동작 안하게 막아두기 */
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.4)
	}
	
	div.login-modal form {
		position: relative;
		top: 200px;
		margin: 10px auto;
	}
</style>

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
<c:choose>
	<c:when test="${BODY == 'WRITE'}">
		<%@ include file="/WEB-INF/views/body/ems/write.jsp" %>
	</c:when>
	<c:when test="${BODY == 'VIEW'}">
		<%@ include file="/WEB-INF/views/body/ems/view-div.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/WEB-INF/views/body/ems/list.jsp" %>
	</c:otherwise>
</c:choose>

<div class="login-modal">
	<%@ include file="/WEB-INF/views/login.jsp" %>
</div>
</body>
</html>