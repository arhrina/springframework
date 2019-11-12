<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String strName = "대한민국";
strName = request.getParameter("strName");
String strNum1 = request.getParameter("num1");
String strNum2 = request.getParameter("num2");
int intNum1 = Integer.valueOf(strNum1);
int intNum2 = Integer.valueOf(strNum2);
int sum = intNum1 + intNum2;
/*
request : jsp에서만 사용 가능한 jsp의 singletone static 객체
이미 jsp 파일이 만들어지면서 객체 로그 생성, 그 정보를 받을 때까지 사용
*/
%>
<html>
<head>
<meta charset="UTF-8">
<title>Ma Page</title>
</head>
<body>
<h3>나의 페이지</h3>
<p>Welcome to ma page
<p>This page is wrote by JSP
<p>나는                                                                   우리나라</p>
<p>웹페이지에서 빈칸은 개수에 관계없이 하나만을 인식하고<br/>나머지는 무시
<!-- &...; 형식의 문자열은 특수코드 문자열이다
&nbsp; 임의의 빈칸을 표기 -->
<p>나는 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;대한민국
<p>Copy Right &copy; @E-mail@E-mail
<p><%= intNum1 %>+<%= intNum2 %> = <%= sum %></p> 
</body>
</html>