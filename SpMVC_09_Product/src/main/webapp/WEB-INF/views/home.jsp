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
<style>
p {
	border: 1px solid blue;
	padding: 16px;
	margin: 10px;
}
</style>
<script>
	$(function() {
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
			$.ajax({
				url : "${rootPath}/nation",
				data : {str : $("#input-1").val()},
				success : function(result) {
					$("#p4").text(result)
				}
			})
		})
	})
</script>
</head>
<body>
	<header>
		<h3>Product</h3>
	</header>
	<section>
		<p class="pc" id="p1">클릭1</p>
		<p>
			<input id="input-1" name="in_01">
		<p class="pc" id="ajax">Ajax</p>
		<p class="pc" id="p3">클릭3</p>
		<p class="pc" id="p4">클릭4</p>
		<p class="pc" id="p5">클릭5</p>
		<p class="pc" id="p6">클릭6</p>
	</section>
</body>
</html>