<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<%@ include file="/WEB-INF/views/include/include-head.jspf" %> <!-- include하는 파일들이 많아졌으므로 파일로 따로 분리해서 로드 -->
<script>
$(function() {
	
		$("#rb_bname").focus(function(){ // 내용이 있을 때 클릭되면(focus가 위치하면)
			$(this).select() // 전체블록 선택되어 새로운 검색어를 입력하면 삭제되도록
		})
	
		$("#rb_bname").keypress(function(event) { // 책이름에서 엔터키를 누르면 function 동작
			if(event.keyCode == 13) { // 엔터키는 13
				let strExt = $(this).val() // 변수 strText에 값을 담는다
				if(strExt == "") {
					alert("도서 이름을 입력한 후 엔터를 누르시오")
					return false
				}
				$("#modal-box").css('display', 'block') // modal-box 보이기
				// alert($(this).val()) 테스트용코드
				$.post("${rootPath}/book/search", {strText:strExt}, function(result){ // POST 동작을 스크립트로 지시
					$("#modal-content").html(result) // html에 result값을 보내주기
				})
			}
		})
		
		$(".modal-header span").click(function(){
			$("#modal-box").css('display', 'none') // modal-box 안보이게
		})
		
		$("#btn-write").click(function() {
			if($("#rb_bcode").val() == "") { // 유효성검사. rb_bcode의 값(val)이 비어있을시 alert 동작
				alert("도서코드는 반드시 입력해야 합니다")
				$("#rb_bcode").focus()
				return false
			}
			if(parseInt($("#rb_rtime").val()) < 1) { // java의 Integer.valueOf와 같은 parseInt
				alert("독서시간은 반드시 입력해야 합니다")
				$("#rb_rtime").focus()
				return false
			}
			if($("#rb_subject").val() == ""){
				alert("한줄평은 반드시 입력해야 합니다")
				$("#rb_subject").focus()
				return false
			}
			$("form").submit() // 버튼을 클릭하면 submit 동작
		})	
	
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
	
	$("#rb_text").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop : true
	})
	
	$("#rb_star").change(function() { // rb_star 값으로 실시간으로 크기 변경코드
		let a = $(this).val() // this.val은 rb_star
		a = a * 10
		$("span.star-red").css("width", a + "%")
		$("span#star-v").text($(this).val())
	})
})
</script>
<style>
	.input-box {
		display: flex;
		width: 80%;
		margin: 5px auto;
	}
	
	.input-box input {
		color: black;
		border: none;
		border-bottom: 1px solid rgb(52, 152, 219);
		display: block;
		font-size: 0.9rem;
		width: 100%;
		padding: 8px;
	}
	
	#rb_bcode{
		width: 50%;
		margin-right: 5px;
	}
	
	#rb_star {
		width: 40%;
	}
	
	span.star-box {
		width: 100px;
	}
	
	span.star-box, span.star-red { /* 이미지로 별을 가져와서 별을 표시 */
		display: inline-block;
		height: 20px;
		overflow: hidden;
		background: url("${rootPath}/image/star.png") no-repeat;
		background-size: 100px 40px; /* 배경이미지의 width는 10px, heigh를 40px로 */
	}
	
	span.star-red {
		background-position: left bottom; /* 배경이미지를 채울 때, 왼쪽 아래 꼭지점을 기준으로 배치 */
		line-height: 0;
		vertical-align: top;
		width: 50%;
	}
	
</style>
</head>
<body>
	<header>
		<h2>MY READ BOOK</h2>
	</header>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
<%
/*
글쓰기를 시작할 때 controller에서 get으로 path(URL)을 받았을 때 현재 view를 보여주고 input box에 데이터를 입력한 후 submit button을
클릭하면 controller에 POST로 데이터가 전송된다

path는 view를 열 때 사용했던 path가 그대로 적용된다

그렇게 사용하지 않을 경우에는 별도로 action을 지정해야 한다

/rbooks/book/insert path(URL)를 실행하면 controller의 GET method가 이를 수신해서 현재 input.jsp를 보여주고(controller에서 return input)
데이터를 입력하고 저장 버튼을 클릭하면 /rbooks/book/insert path(URL)의 POST method로 데이터가 전송된다
자료값은 @Param("변수명")으로 1개씩 변수를 받거나, @ModelAttribute를 통해 여러개의 자료가 있는 것을 받을 수 있다

특별히 action을 지정하지 않으면 위와 같은 방식으로 사용되는 것이 GET, POST이다

기본값으로 사용하지 않을 경우에는 action을 지정하여 사용할 수 있다

spring form tag(http://www.springframework.org/tags/form)를 사용하는 경우는 기본 method가 POST이고
html form tag는 기본 method는 GET이다

button은 type을 지정하지 않으면 type="submit"이 기본값이고 버튼을 클릭하면 form에 입력한 데이터를 controller가 있는 서버로 전송하는 기능

button에 특별히 event를 적용하고 싶으면, type="button" 등으로 지정해서 사용할 수 있다

button은 3가지 타입이 있다
submit : 아무것도 지정하지 않았을 때 기본값. form 안에 버튼이 있을 때 클릭하면 form에 담긴 데이터를 서버로 전송
		 input box가 1개만 있을 경우 키보드의 Enter키에 반응하여 버튼을 클릭한 것처럼 동작
button : 모든 기능을 제거하고 별도의 event를 설정할 때 사용. script로 event 지정
reset : form 안에 있는 경우 form의 input box에 작성한 내용들을 모두 초기화
	
중첩된 form의 action은 어떤 것을 선택할 것인가?
*/
%>
	<section id="main-writer">
		<article>
			<form:form modelAttribute="rBookVO"> <!-- spring form태그는 생략해도 method="POST"이 입력된 상태 -->
				<div class="input-box">
					<form:input type="text" path="rb_bcode" placeholder="도서코드" />
					<input id="rb_bname" name="rb_bname" placeholder="도서이름을 입력한 후 엔터">
				</div>
				<div class="input-box">
					<form:input type="date" path="rb_date" placeholder="독서일자" />
				</div>
				<div class="input-box">
					<form:input type="time" path="rb_stime" placeholder="독서시작시간" />
				</div> <!-- 스프링폼태그에서 path로 이름을 맞춰서 자료를 가져온다 -->
				<div class="input-box">
					<form:input type="text" path="rb_rtime" placeholder="독서시간" />
				</div>
				<div class="input-box">
					<form:input type="text" path="rb_subject" placeholder="한줄평" />
				</div>
- 				<div class="input-box">
					<form:input type="range" min="1" max="10" path="rb_star" placeholder="별점" />
				<span class="star-box">
					<span class="star-red"></span>
				</span>
				<span id="star-v"></span>
				</div>
				<div class="input-box">
					<form:textarea path="rb_text" placeholder="독서소감" />
				</div>
				<div id="main-button">
					<button type ="button" id="btn-write" class="biz-orange flex-right">독서록 저장</button>
					<button id="btn-clear" type="reset" class="biz-red">새로작성</button>
					<button type="button" id="btn-list" class="biz-blue">리스트로 가기</button>
				</div>
			</form:form>
		</article>
	</section>
	<!-- modal box만들기. css로 만들어진다. 자세한건 rbook-main.css 확인 -->
	<div id="modal-box">
		<div class="modal-header">
			<span>&times;</span> <!-- X 표시 띄우기. 클릭해서 modal 없애는건 js에서 동작 -->
		</div>
		<div id="modal-content"> <!-- id는 유일. select #사용, class는 전부. select는 . 사용 -->
		</div>
	</div>
</body>
</html>