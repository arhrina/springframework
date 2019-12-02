<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	
})$("#btn-insert").click(function(){
	document.location.href="${rootPath}/memo/insert"
})
</script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
}

body {
	width: 978px; /* 화면 크기를 바꿨을 경우 덜 흐트러지게 px로 고정. %는 흐트러진다 */
	margin: 0 auto;
	display: flex;
	flex-flow: column wrap;
}

header {
	background: #ccc;
	text-align: center;
	padding: 0.8rem;
}

footer {
	background: green;
	color: white;
	text-align: center;
	padding: 0.2rem;
	flex: 0 0 auto;
}

section#main-body {
	/*
	flex : 1 0 auto로 3개를 한줄로 할 수 있다
	화면 가득히 section box를 채우기 위한 설정
	*/
	flex-grow: 1; /* 최대화 했을 때 */
	flex-shrink: 0; /* 최소화 했을 때 */
	flex-basis: auto;
	background-color: #ddd;
	display: flex;
}

section#main-body article {
	flex: 5;
}

section#main-body aside { /* article과 aside를 5:1로 */
	flex: 1;
	border: 1px solid blue;
	background: white;
	padding: 16px;
	border-radius: 10px;
}

section#main-body ul {
	list-style: none;
	margin-left: 16px;
}

section#main-body li a {
	/*
	a tag에 width, height를 설정하기 위해서는
	display를 block이나 inline-block으로 설정해줘야한다
	설정해주지 않으면 동작하지 않는다
	 */
	width: 120px;
	display: inline-block;
	border-bottom: 1px solid blue;
	padding: 10px 16px;
	text-decoration: none;
}

section#main-body li a:hover {
	background-color: #ccc;
}

article {
	border: 2px solid green;
}

article.list {
	border: 1px solid red;
	height: 90%;
	overflow: auto;
}

div.b-box {
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0.8rem;
}

div.b-box button {
	background-color: orange;
	color: black;
	font-weight: bold;
	padding: 8px 16px;
	border: 0px;
}

div.b-box button:hover {
	background-color: #ddd;
}

div.s-box {
	width: 100%;
	border: 1px solid blue;
	margin-bottom: 5px;
}

div.s-box input {
	display: block;
	width: 90%
	margin: 10px auto
}
</style>
</head>
<body>
	<header>
		<h3>심플 메모장</h3>
	</header>
	<section id="main-body">
		<article>
			<div class="s-box">
				<form>
					<input type="text" name="search">
					<%
						// 검색하려고 만든 input box. search라는 이름으로 controller로 보낸다(POST 동작)
					%>
				</form>
			</div>
			<article class="list">
				<%@ include file="/WEB-INF/views/list.jsp" %>
				<div class="b-box">
					<button id="btn-insert">메모 작성</button>
				</div>
			</article>
		</article>
		<aside>
			<ul>
				<li><a href="#">오늘 할 일</a></li>
				<li><a href="#">약속</a></li>
				<li><a href="#">중요메모</a></li>
			</ul>
		</aside>
	</section>
	<footer>
		<address>CopyRight &copy; m@m.m</address>
	</footer>
</body>
</html>