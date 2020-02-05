<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.card-header h3 {
	display: inline-block;
	width: auto;
	padding: 5px 15px;
}
</style>
<script>
	$(function(){
		$("button.btn-c-save").click(function(){
			let cmt_writer = $("#cmt_writer").val()
			let cmt_text = $("#cmt_text").val()
			
			if(cmt_writer == ""){
				alert("댓글 작성자를 입력하세요")
				$("#cmt_writer").focus()
				return false
			}
			
			if(cmt_text == ""){
				alert("댓글을 입력하세요")
				$("#cmt_text").focus()
				return false
			}
			
			// json 형태로 데이터 생성
			// 중괄호로 시작해서 json데이터임을 표기, key:data로 쌍을 이뤄 값을 보낸다. 문자열은 "" 안에. ajax에서 data에 실어주면
			// json 형태로 데이터가 전송된다
			let comment_data = {cmt_p_id:'${bbsVO.bbs_id}', cmt_writer:cmt_writer, cmt_text:cmt_text}
			
			$.ajax({
				url : '${rootPath}/bbs/cmt_write',
				method : 'POST',
				data : comment_data,
				success : function(result) {
					// alert(result)
					$("#cmt_list").html(result)
				},
				error : function() {
					alert("서버와 통신오류")
				}
			})
		})
		
		$.post("${rootPath}/bbs/cmt_list", {cmt_p_id:"${bbsVO.bbs_id}"}, function(result){
			$("#cmt_list").html(result) // cmt_list에 html방식의 결과값을 입력
		}) // 현재 페이지의 html 화면이 모두 그려지고 나면 바로 이 코드 동작을 수행
	})
</script>
<article class="card">
	<div class="card-header bg-primary text-white">
		<h3>${bbsVO.bbs_subject}</h3>
		(${bbsVO.bbs_date} : ${bbsVO.bbs_time})
	</div>
	<div class="card-body">${bbsVO.bbs_content}</div>
	<div class="card-footer bg-success text-white">CopyRight &copy;
		${bbsVO.bbs_writer}</div>
		
		<div class="card-body bg-info">
			<div id="cmt_list" class="form-group bg-white">
				댓글 리스트 보기
			</div>
			<div class="form_group">
				<input name="cmt_writer" class="form-control" placeholder="작성자" id="cmt_writer">
			</div>
			<div class="form_group">
				<input name="cmt_text" class="form-control" id="cmt_text">
				<button class="btn btn-dark btn-c-save">저장</button>
			</div>
		</div>
</article>
<div class="btn-group">
	<button type="button" class="btn btn-primary">리스트보기</button>
	<button type="button" class="btn btn-success">수정</button>
	<button type="button" class="btn btn-warning">삭제</button>
</div>

<c:if test="${bbsVO.bbs_p_id == 0}">
	<script>
		$(function() {
			$("button.btn-r-save").click(function(){
				let bbs_writer = $("#bbs_writer").val()
				let bbs_content = $("#bbs_content").val()
				
				// 유효성검사
				if(bbs_writer == "") {
					alert("작성자를 입력하세요")
					$("#bbs_writer").focus()
					return false
				}
				
				if(bbs_content == "") {
					alert("내용을 입력하세요")
					$("#bbs_content").focus()
					return false
				}
				
				$("form").submit()
			})
		})
	</script>
	<form:form action="${rootPath}/bbs/replay" modelAttribute="bbsVO">
	<div class="form-group">
		<input class="form-control" value="" name="bbs_writer" id="bbs_writer" placeholder="답글작성자 이름"/>
		<!-- input을 spring form에서 html로 바꾸면 id가 자동으로 생성되지 않으므로 id를 넣어줘야한다 -->
	</div>
		<textarea id="bbs_content" name="bbs_content" placeholder="답글을 입력하세요" cols=""
			rows="10" ></textarea>
		<button class="btn btn-info btn-r-save" type="button">답글저장</button>
	</form:form>
</c:if>