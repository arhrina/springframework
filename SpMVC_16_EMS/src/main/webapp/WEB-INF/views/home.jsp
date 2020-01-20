<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>MY EMS</title>
<!-- Email Management Dystem -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<style>
header {
	height: 100px;
	background-color: #78caf8;
	text-align: left;
	padding: 50px;
}

header a {
	color: black;
	text-decoration: none;
}

table {
	border-bottom: 1px solid #ddd; /* 테이블 아래 선긋기 */
	border-collapse: collapse;
}

td, th {
	white-space: nowrap;
	text-align: left;
	padding-left: 30px;
	padding-top: 15px;
	vertical-align: top;
}

tr:first-child { /* tr 첫번째자료 바로 위에 선긋기 */
	border-top: 1px solid #ddd;
}

tr:nth-child(even) { /* tr 짝수마다 적용 */
	background-color: #f1f1f1;
}

nav, section {
	margin: 0 20px;
}

.main-menu {
	list-style: none;
	display: flex;
	background-color: rgb(52, 152, 219);
}

.a-menu {
	display: inline-block;
	text-decoration: none;
	padding: 8px 10px;
	cursor: pointer;
	color: white;
	font-weight: bold;
}

.a-menu:hover {
	background-color: orange;
	cursor: pointer;
	transition: 0.5s;
}

#btn-input {
	display: flex;
	border: none;
	display: inline-block;
	padding: 8px 16px;
	vertical-align: middle;
	text-decoration: none;
	text-align: center;
	cursor: pointer;
	white-space: nowrap;
	color: inherit;
	background-color: inherit;
	border-radius: 5px;
	margin: 5px;
	color: #fff;
	background-color: green;
}
</style>
<script>
	$(function() {
		$(".a-menu").click(function() {

			let menu_href = $(this).attr("data-menu")
			location.href = "/" + menu_href

		})
	})

	$(function() {
		$(".bbs-row").click(function() {
			let bbs_seq = $(this).attr("data-seq")
			location.href = "/bbs/view?bbs_seq=" + bbs_seq
		})
	})
</script>
<body>

	<header>
		<h2>
			<a href="${rootPath}/">MY EMS</a>
		</h2>
	</header>
	<nav>
		<ul class="main-menu">
			<li><a href="javascript:void(0)" class="a-menu" data-menu="list">EMS</a></li>
			<li><a href="javascript:void(0)" class="a-menu"
				data-menu="bbs/free">자유게시판</a></li>
			<li><a href="javascript:void(0)" class="a-menu"
				data-menu="bbs/notice">공지사항</a></li>
			<li><a href="javascript:void(0)" class="a-menu"
				data-menu="member/login">로그인</a></li>
			<li><a href="javascript:void(0)" class="a-menu"
				data-menu="member/join">회원가입</a></li>
		</ul>
	</nav>
	<section>
		<table class="main-table">
			<thead>
				<tr>
					<th>NO</th>
					<th>받는Email</th>
					<th>받는사람</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>작성시각</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty List}">
						<tr>
							<td colspan="6">데이터가 없습니다</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${LIST}" var="VO" varStatus="in">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- inline style 지정방식 -->
		<div class="btn-box right" style="padding-right: 25px;">
			<!-- inline script 지정방식 -->
			<button id="btn-input" onclick="location.href='/ems/write'"
				class="bz-btn input">메일보내기</button>
		</div>
	</section>
</body>
</html>