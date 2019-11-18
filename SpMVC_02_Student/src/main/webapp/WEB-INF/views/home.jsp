<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!-- pageContext request에 contextPath는 /student/ context를 가지고 있다. 너무 길어서 rootPath로 세팅 -->
<%
/*
<c:set var="새로운 변수명" value="${기존변수}" />
기존변수(변수명이 매우 길어 불편)를 새로운 변수에 임시 할당하여 jsp 코드내에서 새로운 변수명으로 사용하는 방법

pageContext : jsp에 설정된 전역 객체, 여러가지 정보를 담고 있다
request : web에서 전송된 여러가지 정보가 담긴 sub 객체
contextPath : prj의 context 문자열을 가지고 있는 필드변수

String rootPath = pageContext.request.contextPath; java에서 이런 코드를 실행한 것과 동일
*/
%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	나의 홈페이지  
</h1>

<P>  서버에서 받은 시각 : ${serverTime}. </P>
<!-- anchor : 링크설정. 문자를 클릭하면 다른 페이지로 연결해주는 기능 -->
<p><a href="<c:url value='/list' /> ">학생리스트</a>

<!-- c url tag와 방식은 같지만 따옴표 등을 맞추는 것보다 사용하기 편하다 -->
<p><a href="${rootPath}/input">학생정보 입력</a></P>
<p><a href="<c:url value='/search' /> ">학생정보 검색</a></P>
<p><a href="<c:url value='/login' /> ">로그인</a></P>
<p><a href="<c:url value='/join' /> ">회원가입</a></P>
</body>
</html>
