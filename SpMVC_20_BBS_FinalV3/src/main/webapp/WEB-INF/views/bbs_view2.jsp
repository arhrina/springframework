<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
	<script>
	$(function(){
		
		$("button").click(function(){
			let txt = $(this).text()
			if(txt == '수정') {
				document.location.href="${rootPath}/update?b_id=${BBS.b_id}"
			} else if(txt == '삭제') {
				if(confirm("삭제할까요")) {
					document.location.replace("${rootPath}/delete/${BBS.b_id}")    
				}
			} /* else if(txt == '저장') {
				var writer = $("#commentForm").attr("id-writer")
				var subject = $("#commentForm").attr("data-subject")
				document.location.href="${rootPath}/comment/insert?c_writer=" + writer + "&c_subject=" + subject
						
						
						저장에 담긴 데이터를 form에서 POST로 보낸 jsp페이지
			}*/ else {
				document.location.href="${rootPath}/list"
			}
			
		})
		
		
	})
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section class="container-fluid">
		<h2 class="p-3">${BBS.b_subject}</h2>
		<div class="">
			<small class="m-3">${BBS.b_writer}</small>
			<small class="m-3">${BBS.b_date_time}</small>
		</div>
		<hr/>
		<div class="p-3">
		${BBS.b_content}
		</div>
	</section>
	<div class="form-group d-flex justify-content-end">
		<button class="btn btn-primary mr-3">수정</button>
		<button class="btn btn-danger mr-3">삭제</button>
		<button class="btn btn-success">목록으로</button>
	</div>
	<hr/>
	<section class="container-fluid p-4">
		<div class="p-2">
			<b>댓글을 남겨주세요</b>
		</div>
		<div class="row p-4 bg-light">
		<form id="commentForm" action="${rootPath}/comment/insert" method="POST">
			<div class="col-2">
				<input class="form-control" placeholder="작성자" name="c_writer">
			</div>
			<div class="col-8">
				<input class="form-control" placeholder="댓글을 입력하세요" name="c_subject">
			</div>
			<input type="hidden" name="c_b_id" value="${BBS.b_id}">
			<div class="col-2  d-flex justify-content-center">
				<button class="btn btn-success">저장</button>
			</div>
		</form>
		</div>

		<div class="p-2">
			<b>댓글 리스트</b>
		</div>
	
		<div class="p-4 cmt-list">
		<c:forEach var="c" items="${cList}">
		<div class="row p-2 bg-light ">
			<div class="col-2"><b>${c.c_writer}</b></div>
			<div class="col-10">${c.c_subject}</div>
			</div>
		</c:forEach>
		</div>
		
	</section>


</body>
</html>
