<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- https://search.naver.com/search.naver?query=~~~~~로 전송 -->
<p><form action="https://search.naver.com/search.naver" target="_NEW">
<label>네이버 검색</label>
<input name="query">
</form>
<p><form action="https://search.daum.net/search">
<label>다음 검색</label>
<input name="q">
<p><form action="https://www.google.com/search">
<label>구글 검색</label>
<input name="q">
</form>
</body>
</html>