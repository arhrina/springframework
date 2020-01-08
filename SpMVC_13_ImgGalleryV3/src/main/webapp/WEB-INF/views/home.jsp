<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 갤러리</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
<!-- include summernote css/js -->

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="${rootPath}/javascript/summernote-ko-KR.js" type="text/javascript"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.contextMenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.0/jquery.ui.position.min.js"></script>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">


<style>
	* {
		box-sizing: border-box;
		margin:0px;
		padding:0px;
	}
	
	body {
		width:100%;
	}

	header {
		background-color: #41D3BD;
		margin:0;
		padding:1rem;
		color:white;
	}
	
	section {
		width:90%;
		margin:10px auto;
	}
	
	#img_box {
		margin:10px auto;
		border:1px solid green;
		display: flex;
		flex-wrap: wrap;
	}
	
	.img_panel {
		padding:0.01rem 16px;
		margin-top:16px;
		margin-bottom: 16px;
	}
	
	
	.img_card {
		width:200px;
		height: 200px;
		margin:10px;
		/* 이미지가 card box보다 클때 이미지 자르기 */
		overflow : hidden;
		
		display:flex;
		justify-content:center;
		flex-flow : column;
		
		box-shadow: 0 4px 10px 0 rgba(0,0,0,0.16),
 		0 4px 20px 0 rgba(0,0,0,0.19)
	}
	
	.img_card .img_title {
		padding:0.5rem;
		text-align: center;
	}
	
	
	.bz-button {
		border:none;
		display: inline-block;
		padding:8px 16px;
		margin:5px;
		vertical-align: middle;
		text-decoration: none;
		color:white;
		background-color: blue;
		text-align: center;
		cursor: pointer;
		white-space: nowrap;
	}
	
	.bz-button:hover {
		box-shadow: 0 8px 16px rgba(0,0,0,0.2),
			0 6px 20px 0 rgba(0,0,0,0.2)
	}
	
	a {
		text-decoration: none;
		color:inherit;
	}
	
	div.input_box {
		width:90%;
		margin:5px auto;
	}
	
	input[type="text"] {
		padding:8px;
		display: block;
		border: 1px solid #ccc;
		border-radius:5px;
		width:100%;
		margin:8px auto;
	}
	
	#d_d_box {
		width:100%;
		height:300px;
		color: #aaa;
		border:1px solid green;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	#img_view {
		display: none;
	}
	
	.img_list {
		display: flex;
		flex-wrap: wrap;
	}

	.img_view img {
		width:100px;
		height: 100px;
		margin: 5px;
	}
	
	div#login_modal {
		display: none;
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 5;
		background-color: rgba(0, 0, 0, 0.3);
	}
	#login_modal form{
	
		position: relative;
		padding: 10px;
		margin: auto;
		
		animation-name: form-ani;
		animation-duration: 0.9s;
		/* animation-fill-mode: forwards; */
	/* 이하는 브라우저별 호환성을 위한 코드. -webkit은 구글, 사파리, -moz는 firefox, -ms는 explorer, -o는 opera */ 
		-webkit-animation-name: form-ani;
		-webkit-animation-duration: 0.9s;
		-webkit-animation-fill-mode: forwards;
		
		-moz-animation-name: form-ani;
		-moz-animation-duration: 0.9s;
		-moz-animation-fill-mode: forwards;
		
		-ms-animation-name: form-ani;
		-ms-animation-duration: 0.9s;
		-ms-animation-fill-mode: forwards;
		
		-o-animation-name: form-ani;
		-o-animation-duration: 0.9s;
		-o-animation-fill-mode: forwards;
	}
/* 애니매이션을 실행하는 css */
	@keyFrames form-ani {
		from {
			top:-300px;
			opacity: 0;
		}
	
		to {
			top: 35%;
			opacity: 1;
		
		}
	}
	
	@-webkit-keyFrames form-ani {
		from {
			top:-300px;
			opacity: 0;
		}
	
		to {
			top: 35%;
			opacity: 1;
		
		}
	}
	
</style>
<script>
	// js파일에서 사용할 public 변수 선언
	var rootPath = "${rootPath}"
</script>
<script src="${rootPath}/javascript/image_upload.js"></script>
<script src="${rootPath}/javascript/images_upload.js"></script>
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
	
	$("#img_text").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop : true
	})
	
	$("#btn_img_up").click(function(){
		document.location.href = "${rootPath}/image/upload"
	})
	
	$("#d_d_box").on('dragover',function(e) {
		$("#d_d_box h3").text("파일을 내려 놓으세요!!!")
		return false;
	})
	
	/*
	file 1개를 Drag and Drop으로 업로드 수행하기
	*/
	$("#d_d_box").on('drop',function(e) {
		$("#d_d_box h3").text("파일 업로드 중!!!")
		
		// drop한 파일리스트 추출
		let files = e.originalEvent
						.dataTransfer.files
		let fileLen = files.length
						
		// if(fileLen > 1) {
			
			// 파일업로드를 위한 객체만들기
			let formData = new FormData()
			
			// Drop한 파일들을 모두 추가
			for(let i = 0; i < files.length; i++){
				formData.append('files',files[i])
			}
			files_up(formData)
			return false;
		
		/*	
		} else {				

			// 리스트에서 첫번째 파일만 추출				
			let file = files[0]	
			
			// 추출된 파일정보를 서버에 먼저 업로드
			
			// js FormData 클래스를 
			// 사용해서 서버에 파일 업로드 준비
			let formData = new FormData()
			formData.append('file',file)
			
			file_up(formData)
		}
		*/
		return false
	})
	
	var contextCallBack = function(key,options) {
		if(key == 'edit') {
			let img_seq = $(this).attr("data-seq");
			document.location.href ="${rootPath}/image/update/" + img_seq
			// alert("수정 클릭" + $(this).attr("data-seq"))
		}
		
		if(key == 'delete') {
			if(confirm("이미지를 삭제할까요?")) {
				let img_seq = $(this).attr("data-seq");
				
				$.ajax({
					url : "${rootPath}/image/delete",
					// data : img_seq
					// data : img_seq = 3
					data : {img_seq : img_seq},
					type:'POST',
					success : function(result) {
						if(result < 1)
							alert("삭제도중 문제발생")
						else // 삭제된 것 반영
							document.location.replace("${rootPath}/")
					},
					error : function() {
						alert("서버 통신오류")
					}
				})
				
				// 이벤트 버블링 금지
				return false;
				// document.location.href ="${rootPath}/image/delete/" + img_seq
				
			}
		}
	}
	
	/*
	jqueury 응용 
	마우스를 제어 해서 context menu(오른쪽마우스 클릭 메뉴)를
	만들어주는 tool
	
	*/
	$.contextMenu({
		selector:'.img_card',
		callback : contextCallBack,
		items : {
			'edit' : {name:'수정',icon:'edit'},
			'delete' : {name:'삭제',icon:'delete'}
		}
		
	})
	
	var img_context = function(key,option) {
		let id = $(this).attr("data-id") // #1 image file sequence가 data-id
		if(key == "delete") {
			if(confirm(id + " 이미지를 삭제할까요")) {
				$.post("${rootPath}/rest/image_delete",
						{img_id:id},
						function(result){
							if(result == "OK")
								// document.location.replace("${rootPath}/")
								document.location.replace(document.location.href)
							else {
								alert("파일을 삭제할수 없음")
								// 현재화면을 새로고침하세요
								document.location.replace(document.location.href)
							}
						})
			}
		} else if (key == "copy") {
			window.open("${rootPath}/rest/file_down/" + id, target="_new") // GET으로 rest 컨트롤러로 보내기
			// id는 img_file_seq값으로 #1에서 만들었음
		} else if (key == "main") {
			let img_pcode = $(this).attr("data-pcode") // 게시물의 seq값
			// 클릭된 div tag가 가지고 있는 자료중 img tag를 찾아서 src값 가져오기. 이미지의 이름. 파일경로 포함된
			let img_src = $(this).find("img").attr("src")
			let img_srcs = img_src.split("/") // 경로를 빼고 파일이름만을 찾기 위해 src값을 /로 분해
			let img_srcs_len = img_srcs.length // 분해된 배열이 몇개인가
			let img_file = img_srcs[img_srcs_len - 1] // /로 분해한 배열의 제일 마지막 값이 파일 이름
			$.post("${rootPath}/rest/main_image", // rest controller로 값을 post하고 main_image라는 경로로 보냄

// ajax와 구조가 다르다. 호출이 success하면 function이 값을 받는다. ajax는 error tag를 통해 문제가 있을 때 받았지만
// 여기선 실패는 받지 못한다. function이 끝나고 error를 한다. jquery 1.5부터 지원
					{img_pcode : img_pcode, img_file : img_file},
					function(result) { // ajax에 success랑 동일
						document.location.replace(document.location.href) // 현재화면 새로고침
					}).error(function(){alert('통신오류')})
		} else if(key == "loading") {
			let file = $(this).find("img").attr("src")
			window.open(file,"target=_new")
		}
	}
	
	$.contextMenu({
		selector:'.img_view',
		callback : img_context,
		items : {
			'loading' : {name:'확대보기',icon:'loading'},
			'copy' : {name:'다운로드',icon:'copy'},
			'delete' : {name:'삭제',icon:'delete'},
			'main' : {name:'대표이미지설정', icon:'far fa-check-circle'}
		}
	})
	
	if("${MODAL}" == 'LOGIN' || "${MODAL}" == 'JOIN') {
		$("#login_modal").css("display", "block")
	}

})

</script>

</head>
<body>
<header>
	<h3>이미지 갤러리</h3>
	<p><a href="${rootPath}/member/logout">${MEMBER.u_id}</a></p>
</header>

<c:choose>
	<c:when test="${BODY == 'UPLOAD'}">
		<section>
		<%@ include file="/WEB-INF/views/body/img_upload.jsp" %>
		</section>
	</c:when>
	<c:otherwise>
		<section id="img_box">
		<c:forEach items="${imgList}" var="img">
			<%@ include file="/WEB-INF/views/include/img_card.jsp" %>
		</c:forEach>
		</section>
	</c:otherwise>
</c:choose>

<section>
	<button id="btn_img_up" class="bz-button">이미지 올리기</button>
</section>

<div id="login_modal">
	<c:if test="${MODAL == 'JOIN'}">
		<%@ include file="/WEB-INF/views/body/join.jsp" %>
	</c:if>
	<c:if test="${MODAL == 'LOGIN'}">
		<%@ include file="/WEB-INF/views/body/login.jsp" %>
	</c:if>
</div>
</body>
</html>