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
		
		//$(".cmt-item").click(function(){
		// ajax success에서 동적으로 그리고 있으므로 페이지가 나타나고 난 뒤 등록한 댓글은 click이 동작하지 않게 되므로
		// document 자체에 클릭 이벤트를 걸어주면 새로 생긴 댓글도 동작한다. 이벤트도 동적으로 동작
		$(document).on("click", ".cmt-item", function(){
			let id = $(this).data("id")
			alert(id)
		})
		
		$(document).on("click", ".cmt-item-del", function(event){
			
			// 콜백함수에 event란 매개변수를 주고 함수를 사용. 감싸고 있는 곳으로 이벤트가 전파되는 것을 방지하는 함수
			event.stopPropagation()
			
			if(!confirm("코멘트를 삭제하나요?")){
				return false
			}
			
			
			// this(자신의) closest 가장 인접한 div를 찾아서 data의 id를 가져오게 하는 함수
			// let c_id = $(this).closest("div").data("id")
			let c_id = $(this).parent("div").data("id") // 상위요소를 가져오는 parent 함수
			// alert("item-del : "c_id)
			$.ajax({
				url : "${rootPath}/comment/delete/",
				data : {c_id : c_id,
						b_id : "${BBS.b_id}"
						},
				type : "POST",
				success : function(result){
					$("div.cmt-list").html(result) // html 방식으로 result를 붙이기
				},
				error : function(){
					alert("서버통신오류")
				}
			})
		})
		
		
		$("button").click(function(){
			let txt = $(this).text()
			if(txt == '수정') {
				document.location.href="${rootPath}/update?b_id=${BBS.b_id}"
			} else if(txt == '삭제') {
				if(confirm("삭제할까요")) {
					document.location.replace("${rootPath}/delete/${BBS.b_id}")    
				}
			} else if(txt == '저장') {
			
				/*
				ajax 이용해서 form에 담긴 데이터를 컨트롤러로 보내기
				*/
				
				var formData = $("form").serialize() // jQuery 함수. form에 입력한 데이터를 문자열로해서 한줄로 나열하라
				$.ajax({
					url : "${rootPath}/comment/insert",
					data : formData,
					type : "POST",
					success : function(result){
						$("div.cmt-list").html(result)
					},
					error : function(){
						alert("서버 통신오류")
					}
				})
				
				
				
			} else {
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
				<button type="button" class="btn btn-success">저장</button>
			</div>
		</form>
		</div>

		<div class="p-2">
			<b>댓글 리스트</b>
		</div>
	
		<div class="p-4 cmt-list">
		
			<%@ include file="/WEB-INF/views/comment_list.jsp" %>
		
		</div>
		
	</section>


</body>
</html>
