<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="formm"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-lite.css" rel="stylesheet"><!-- summernote css 가져오기 -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-lite.js"></script><!-- summernote css 가져오기 -->
<script src="${rootPath}/resources/js/summernote-ko-KR.js"></script> <!-- 복사한 js폴더 파일 링크걸기 -->

<script>
$(function(){
	var toolbar = [
		['style',['bold','italic','underline'] ],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','hr','picture']],
		['view',['fullscreen','codeview']]
		
	]
	
	$("#content").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop : true
	})
})
</script>

<fieldset class="email-write-box">

	<formm:form modelAttribute="emailVO" enctype="multipart/form-data">
		<!-- 컨트롤러에서 보내는 attribute랑 같게 -->

		<div class="in-box">
			<label for="from_email">보내는 Email</label>
			<formm:input path="from_email" />
		</div>

		<div class="in-box">
			<label for="to_email">받는 Email</label>
			<formm:input path="to_email" />
		</div>

		<div class="in-box">
			<label for="send_date">작성일자</label>
			<formm:input path="send_date" />
		</div>

		<div class="in-box">
			<label for="send_time">작성시각</label>
			<formm:input path="send_time" />
		</div>

		<div class="in-box">
			<label for="from_name">작성자</label>
			<formm:input path="from_name" />
		</div>

		<div class="in-box">
			<label for="to_name">수신자</label>
			<formm:input path="to_name" />
		</div>

		<div class="in-box">
			<label for="subject">제목</label>
			<formm:input path="subject" />
		</div>

		<div class="in-box">
			<formm:textarea path="content" />
		</div>
		
		<div class="in-box">
			<button>메일 보내기</button>
		</div>

	</formm:form>
	
</fieldset>