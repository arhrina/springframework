<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
/*
주석코드
주석코드를 사용할 때 꺽쇠 태그 사용금지
본문코드와 겹쳐서 Web에 보여질 때 의도하지 않는 모양으로 나타남. 느낌표 기호도 가급적 자제
*/
%>
<title>Insert title here</title>
</head>
<body>
<form action="https://search.naver.com/search.naver">
<label>네이버</label><input name="query">
</form>

<form action="https://google.com/search">
<label>구글</label><input name="q">
</form>

<form action="https://search.daum.net/search">
<label>구글</label><input name="q">
</form>
</body>
</html>