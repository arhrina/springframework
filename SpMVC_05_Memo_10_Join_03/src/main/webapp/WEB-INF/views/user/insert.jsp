<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />  
<%@ taglib 
	uri="http://www.springframework.org/tags/form" 
	prefix="form" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>□□□ 나의 JSP 페이지 □□□</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
$(function(){

	// js 함수는 1급함수다 : 함수 = 객체 = 변수
	// 새로운 방식, 함수 = 객체
	// let idcheck = function() {  }
	
	// 새창을 열어서 id를 검사하는 코드를 함수로 선언하기
	// 표준함수 선언 방식
	function idcheck() {
		let top = 500 
		let left = 600 
			
		// alert("Enter 입력")
		let u_id = $("#u_id").val()
		let status = "toolbar=no,"
		status += "scrollbars=yes,"
		status += "resizable=no,"
		status += "top=500,"
		status += "left=500,"
		status += "width=700,"
		status += "height=400"
			
		if(u_id == "") {
			alert("ID를 입력한 후 Enter")
			return false
		}
			
		openWin = window.open("${rootPath}/user/idcheck?u_id=" + u_id,
				"_blank",
				status)
			// openWin.moveTo(left,top)
		
		
	}
	
	$("#u_id").keypress(function(e){
		if(e.keyCode == 13) {
			// 표준, 일반적으로 함수를 호출하는 방법
			idcheck()	
		}
	})
	
	// event hander에 함수목록을 등록하는 절차
	// 이때는 함수 마치 객체처럼 등록이 되기때문에
	// 함수이름에 ()를 넣지 말아야한다.
	// 함수방식을 call back 함수 등록이다라고 한다.
	$("#id_check").click( idcheck )
	
	$("#btn-save").click(function(){
		
		let pass = $("#u_password").val()
		if(pass == "") {
			alert("비밀번호를 입력하세요")
			$("#u_password").focus()
			return false
		}
		
		let re_pass = $("#re_password").val()
		if(re_pass == "") {
			alert("비밀번호를 한번 더 입력해 주세요")
			$("#re_password").focus()
			return false
		}
		
		if(pass != re_pass) {
			alert("비밀번호와 비밀번호확인이 일치하지 않습니다\n"
					+ "다시 입력해 주세요")
			$("#u_password").val("")
			$("#re_password").val("")
			$("#u_password").focus()
			return false
		}
		
		let u_name = $("#u_name").val()
		if(u_name == "") {
			alert("이름은 반드시 입력해야 합니다")
			$("#u_name").focus()
			return false;
		}
		// $(form).submit()
		// $(window)
		$("form").submit()
	})
	
})
</script>

<style>
 fieldset {
 	width:70%;
 	margin:20px auto;
 	border:1px solid green;
 	border-radius: 10px;
 }
 
 legend {
 	font-weight: bold;
 	font-size:20px;
 }
 
 input,textarea {
 	display: inline-block;
 	width:90%;
 	padding:8px;
 	margin:5px;
 	border-radius: 20px;
 
 }
 
 input:focus,textarea:focus,button {
 	border:2px solid blue;
 	outline: none;
 }

 input:hover {
 	background-color: #ddd;
 	border:2px solid red;
 }

 .in-error {
 	display: inline-block;
 	margin-left: 20px;
 	font-size:15px;
 	font-weight:bold;
 	color:red;
 }

span#u_id_msg {
	display: none;
}

#u_id {
	width:70%;
}

</style>
</head>
<body>
<fieldset>
	<legend>
		<c:if test="${TITLE == null}">회원가입</c:if>
		<c:if test="${TITLE != null}">${TITLE}</c:if>
	</legend>
	<form:form modelAttribute="userDTO"  
			autocomplete="off"  class="user-form">
		<div class="in-box-border">
			<form:input path="u_id" 
				type="text"
				class="in-box" 
				placeholder="사용자 ID를 입력하고 Etner..."  />
			<button type="button" id="id_check">아이디검사</button>
			<br/>
			
			<form:errors path="u_id" class="in-error"/><br/>
			<span id="u_id_msg"></span>
		</div>
		
		<div class="in-box-border">		
			<form:input path="u_password" 
				type="password" 
				class="in-box" 
				placeholder="비밀번호를 입력하세요"  /><br/>
			<form:errors path="u_password" class="in-error"/>
		</div>
		
		<%
		/*
		비밀번호 확인 input box를 표준 html로 작성
		비밀번호 확인은 서버로 데이터를 전송할 필요가 없기 때문에
		또한 form:input으로 작성을 하게 되면
		DTO에 해당 필드변수를 작성해야하는 불편이 있기 때문에
		이 항목에 입력된 값은 서버로 전송하기전
		두번 입력한 비밀번호가 같은지만 검사하면 되기 때문에
		*/
		%>		
		<div class="in-box-border">
			<form:input type="password" path="re_password"
				placeholder="비밀번호를 다시한번 입력하세요" />
			<form:errors path="re_password" class="in-error"/>
		</div>

		<div class="in-box-border">
			<form:input path="u_name" 
				class="in-box" 
				placeholder="이름을 입력하세요"  /><br/>
			<form:errors path="u_name" class="in-error"/>
		</div>

		<div class="in-box-border">
			<form:input path="u_nick" 
				class="in-box" 
				placeholder="별명을 입력하세요"  /><br/>
			<form:errors path="u_nick" class="in-error"/>
		</div>
		
		<div class="in-box-border">
			<form:input path="u_tel" type="number"
				class="in-box" 
				placeholder="전화번호를 숫자만으로 입력하세요"  /><br/>
			<form:errors path="u_tel" class="in-error"/>
		</div>

		
		<button type="button" id="btn-save">저장</button>
	</form:form>
</fieldset>
<<<<<<< HEAD
=======
	<%
	/*
	html tag의 inputbox 와 달리
	name이라는 속성을 사용하지 않고 
	path라는 속성이 변수설정 역할을 수행
	*/
	%>

>>>>>>> 20c0e7a35cd5ddab13de9241ab59d489e75056de
</body>
</html>