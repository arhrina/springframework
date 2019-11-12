<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*
	index.jsp
	보통 도메인 네임을 브라우저에 입력하고 enter를 눌렀을 때 최초로 보여지는 화면을 구현한 파일
	static방식으로 구현된 서버 index.html, index.htm,
	dynamic방식으로 구현된 서버에선 index.php, index.asp, index.jsp 등으로 사용
	
	static : 문서파일 형태로 화면을 구현하고 누구에게나 거의 비슷한 화면을 보임
	dynamic : DB, Application과 연동되어 사용자의 요구사항에 따라 화면을 다르게 보이는 능동적방식
	*/
String strName = request.getParameter("strName");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>나의 홈페이지</h3>
<p>나(<%= strName %>)의 홈페이지에 오신 것을 환영합니다
<p><a href="https://naver.com/">네이버</a>
<p><a href="https://daum.net/">다음</a>
<p><a href="https://google.com/">구글</a>
<!-- strName query로 전달받은 값을 page.jsp에게 전달 -->
<p><a href="http://localhost:8080/JSP_02_Hello/page.jsp?strName=<%=strName%>">나의 정보</a>
<p><a href="/JSP_02_Hello/page.jsp?strName=<%=strName%>">나의 정보</a>
<p><a href="/JSP_02_Hello/mypage/mypage1.jsp?strName=성춘향">성춘향의 정보</a>
</body>
</html>