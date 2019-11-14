<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// HelloServlet의 doGet()에서 sendRedirect로 보내준 데이터를 받아서 변수에 담은 것
String st_name = request.getParameter("st_name");
String st_dept = request.getParameter("st_dept");
String st_grade = request.getParameter("st_grade");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>학생정보</h3>
<p>이름 : <%= st_name %>
<p>학과 : <%= st_dept %>
<p>학년 : <%= st_grade %>
</body>
</html>