<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {

		$("#btn_all").click(function(){
			$.ajax({
				url : '${rootPath}/plist',
				success : function(proList){
					$('article.detail').html(proList)
					/*
					proList.forEach(function(vo){
						$("article.detail").append(
							// $("<p/>", {text : vo.p_name})
							"<p><span>" + vo.p_code + "</span> :"
							+ "<p><span>" + vo.p_name + "</span> :"
							+ "<p><span>" + vo.p_iprice + "</span> :"
							+ "<p><span>" + vo.p_oprice + "</span> :"
								)
					})
					*/
				}
			})
		})
		
		$("#btn_search").click(function() {
			var in_p_code = $("#p_code").val()
			$.ajax({
				url : '${rootPath}/pname',
				data : {
					p_code : in_p_code
				},
				success : function(result) {
					// '<p>' + result + '</p>'
					$("article.detail").append($("<p/>", {
						html : result,
						class : 'pc' // class를 추가해주는 json
					}
					))
				},
				error : function(error) {
					$("article.detail").text(error)
				}
			})
			/*
			$("#p1").click(function() {
				$("#p2").text("대한민국만세")
				let i1 = $("#input-1").val() // input box에선 값을 val로 뽑는다
				$("#p3").text(i1)
			})
			$("#ajax").click(function() { /* web browser 내에는 HttpConnection 객체가 내장되어있다. Ajax Connection에
				rootPath/nation.jsp에 명령을 보내면 200 code를 return한다. 일반적인 통신 client - server의 가운데 ajax가 있다.
				ajax가 중간에서 중개해주면서 서버에서 response를 받으면 success를 실행한다
				비동기식. 내부에 1개뿐
			 */
			/* $.ajax({
				url : "${rootPath}/nation",
				data : {str : $("#input-1").val()},
				success : function(result) {
					$("#p4").text(result)
				}
			}) 
			}) */
		})
	})
</script>
<style>
* { /* 꽉찬 화면 만드는 코드 */ <!--
	width: 100%; -->
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

header {
	background-color: green;
	color: white;
	padding: 0.8rem;
}

nav {
	background-color: rgba(0, 255, 0, 0.3);
	padding: 10px;
}

article {
	border: 1px solid blue;
	margin: 10px auto;
}

table.p-main {
	width: 95%;
	margin: 10px auto;
	border-collapse: collapse;
	border: 1px solid green;
}
table.p-list{
	width: 95%;
	margin: 10px auto;
	border-collapse: collapse;
	border: 1px solid green;
}

ul {
	display: flex;
	list-style: none;
}

ul a {
	text-decoration: none;
	display: inline-block;
	padding: 14px 10px;
}

<!-- 범위제한두기 -->
article.detail{
	height: 500px;
	overflow: auto;
}
</style>
</head>
<body>
	<header>
		<h3>Product</h3>
	</header>
	<nav>
		<ul>
			<li><a href="#">Logo</a></li>
			<li><input id="p_code"><button id="btn_search">검색</button>
			<li><button id="btn_all">상품리스트 가져오기</button>
		</ul>
	</nav>
	<section>
		<article class="main">
			<table class="p-main">
				<tr>
					<th>상품코드</th>
					<th>상품이름</th>
				</tr>
				<tr class="p-list">
					<td></td>
					<td></td>
				</tr>
			</table>
		</article>
		<article class="detail"></article>
	</section>
</body>
</html>