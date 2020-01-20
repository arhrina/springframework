<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="formm" %>

<style>
	div.in-box {
		display: flex;
		margin: 3px;
	}
	
	div.in-box label {
		display: block;
		width: 100px;
		text-align: right;
		margin: 8px;
		padding: 5px;
		font-weight: bold;
		color: blue;
	}
	
	div.in-box input {
		width: 60%;
		border: 1px solid #aaa;
		border-radius: 4px;
		padding: 5px;
		margin: 3px;
	}
	
	div.in-box input:focus {
		background-color: lightgray;
	}
</style>

<formm:form modelAttribute="emailVO" enctype="multipart/form-data"> <!-- 컨트롤러에서 보내는 attribute랑 같게 -->

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
	
</formm:form>