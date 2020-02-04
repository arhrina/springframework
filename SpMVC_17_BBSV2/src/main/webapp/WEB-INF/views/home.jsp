<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
	body {
		border: 1px solid #aaa; /* 레이아웃 보게 잠시 테스트용 */
	}
</style>
<script>
	$(function(){
		$("#btn-write").click(function(){
			document.location.href = '${rootPath}/bbs/input'
		})
		
		$(".bbs-content").click(function(){
			let bbs_id = $(this).attr("data-id")
			document.location.href="${rootPath}/bbs/view?bbs_id=" + bbs_id // get 방식으로 파라미터 직접 보내기
		})
	})
</script>
</head>
	
	<header class="jumbotron text-center">
		<h3>Build Board System</h3>
	</header>
	
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="${rootPath}/">Home</a></li>
			<li class="nav-item justify-content-end"><a href="${rootPath}/member/login" class="nav-link">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="${rootPath}/member/join">Join</a></li>
		</ul>
		
	<body class="container-fluid">
	
	<section>
		<c:choose>
			<c:when test="${BODY == 'BBS_INPUT'}">
				<%@ include file="/WEB-INF/views/include/bbs_input.jsp" %>
			</c:when>
			<c:when test="${BODY == 'VIEW'}">
				<%@ include file="/WEB-INF/views/include/bbs_view.jsp" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/include/bbs_list.jsp" %>
				
				<div class="input-group">
					<div class="input-group-btn">
						<button id="btn-write" class="btn btn-primary" type="button">작성</button>
					</div>
				</div>
				
			</c:otherwise>
		</c:choose>
	</section>
	
	

	</body>
</html>