<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>학생리스트</h3>
<%
/*
Controller로부터 list를 받아서 forEach 명령을 이용해서 list를 view에 구현한다

<c:forEach>를 사용해서 구현
items : controller에서 받은 list attribute 이름
var : items 요소를 하나씩 저장해서 받을 dto 이름
*/
%>
<c:forEach items="${STD_LIST}" var="std" varStatus="itemNum">

	<p>${itemNum.count}, ${itemNum.index} : ${std}

</c:forEach>
</body>
</html>