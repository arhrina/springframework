<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- anchor. 다른 곳으로 연결해주는 링커. hyper text의 꽃. 해당 문자열에 링크주소를 걸어줌 -->
<a href="https://search.naver.com/search.naver?query=미국">네이버검색</a>
<p><a href="https://search.naver.com/search.naver?q=대한민국">네이버검색</a>
<p>네이버검색</p>

<form action="/JSP_01_Hello/home/home1.jsp">
<!-- input box, input tag -->
<p><input name="query">
<p><input name="num1">
<p><input name="num2">

<!-- 누름단추 -->
<button>검색</button>
</form>
</body>
</html>