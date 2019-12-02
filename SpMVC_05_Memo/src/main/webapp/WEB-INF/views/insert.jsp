<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%
	/*
	spring-form tag lib
	
	html의 input, form 등의 입력 box용 tag를 확장하여 spring의 controller와 연동이 쉽게 해주는 라이브러리
	*/
%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<style>

	fieldset{
		width: 70%;
		margin: 20px auto;
		border: 1px solid green;
		border-radius: 10px;
	}
	
	legend{
		font-weight: bold;
		font-size: 20px;
	}
	
	input, textarea{
		display: inline-block;
		width: 90%;
		padding: 8px;
		margin: 5px;
		border-radius: 20px;
	}
	
	input:focus, textarea:focus{
		border: 2px solid blue;
		border-radius: 20px;
	}
	
</style>
</head>
<body>
	<%
		/*
		html의 form이 가진 default method는 GET인데
		form:form tag는 default method가 POST이다
		*/
	%>

<!-- modelAttribute라고 붙은 이 이하 내용이 자동으로 DTO가 되며 path가 field와 일치하는 이름이라면 변수들의 값을 세팅해서 보여준다
placeholder는 값이 있으면 보여주고 수정하고자 하면 수정할 수 있다. jsp이면서 controller와 연관된 상태가 된다 -->

	<fieldset>
		<legend>메모 작성</legend>

		<form:form modelAttribute="memoDTO">
			<%
				/*
						html tag의 inputbox와 달리 name이라는 속성을 쓰지 않고 path란 속성이 변수설정역할(세팅)을 대신 수행
						*/
			%>
			<form:input path="m_date" class="in-box" placeholder="작성일자" />
			<br />
			<form:input path="m_time" class="in-box" placeholder="작성시각" />
			<br />
			<form:input path="m_auth" class="in-box" placeholder="작성자 id" /> <!-- path랑 field는 반드시 같아야한다 -->
			<br />
			<form:input path="m_cat" class="in-box" placeholder="카테고리를 입력하세요" />
			<br />
			<form:input path="m_subject" class="in-box" placeholder="제목을 입력하세요" />
			<br />
			<form:textarea path="m_text" rows="5" cols="20"></form:textarea>
			<br />
			<button>저장</button>
		</form:form>
	</fieldset>
</body>
</html>