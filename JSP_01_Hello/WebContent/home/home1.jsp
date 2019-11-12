<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String strName = request.getParameter("strName");
String strNum1 = request.getParameter("num1");
String strNum2 = request.getParameter("num2");

int intNum1 = Integer.valueOf(strNum1);
int intNum2 = Integer.valueOf(strNum2);

int intSum = intNum1 + intNum2;

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3><%= intSum %></h3>
</body>
</html>