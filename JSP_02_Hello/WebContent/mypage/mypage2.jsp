<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String strName = request.getParameter("strName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3><%=strName%></h3>
	<h3>${param.strName}</h3>
	
	
	<%
	/*
	java주석
	<form>으로 감싼 input tag들은 사용자로부터 값을 받아서 서버의 어떤 페이지로 전송하는 역할
	
	여러 변수들을 하나의 박스에 담는 것과 같은 이치
	<form> 태그에 action 속성을 지정하면 원하는 주소(page, 서버)에 값을 전송할 수 있다
	form에 action을 지정하지 않으면 다시 자기 자신을 호출하는 효과가 있으며
	input tag에 입력된 값들을 query로 생성하여 현재 page에서 다시 수신할 수 있다
	
	*/%>
	<!-- html 주석 -->
	<form>
		<p>
			<label>숫자1</label><input name="num1">
		<p>
			<label>숫자2</label><input name="num2">
		<p>
			<button>덧셈수행</button>
	</form>
	<p>숫자1 ${param.num1}과 숫자2 ${param.num2}의 덧셈결과는 ${param.num1 + param.num2}
</body>
</html>