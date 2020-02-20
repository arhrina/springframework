<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<h2>학생 점수</h2>
<div><b>총점:</b>${SUM}</div> <!-- 하나의 변수만을 보내므로 SUM만 사용 -->
<div><b>평균:</b>${AVG}</div>
</body>
</html>